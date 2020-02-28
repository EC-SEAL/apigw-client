/**
Copyright © 2020  Atos Spain SA. All rights reserved.
This file is part of SEAL API Gateway Client (SEAL Apigwcl).
SEAL Apigwcl is free software: you can redistribute it and/or modify it under the terms of EUPL 1.2.
THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT ANY WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT, 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
See README file for the full disclaimer information and LICENSE file for full license information in the project root.

@author Atos Research and Innovation, Atos SPAIN SA
*/
package eu.seal.apigw.cl.rest_api.services.identsource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.seal.apigw.cl.cm_api.ConfMngrConnService;
import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess.BindingEnum;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;
import eu.seal.apigw.cl.domain.MsMetadata;
import eu.seal.apigw.cl.sm_api.SessionManagerConnService;

@Service
public class ClModuleIDRetrieveGetServiceImp implements ClModuleIDRetrieveGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClModuleIDRetrieveGetServiceImp.class);
	
	@Autowired
	private SessionManagerConnService smConn;
	
	@Autowired
	private ConfMngrConnService confMngrConnService;

	
	@Override
	public ModuleTrigger clModuleIDRetrieveGet (String sessionID, String moduleID) throws Exception {
			
		try {
			// Generate token: issuer CL (got from the msMetadataList ConfMngr); receiver Uport (got from the ACCESS metadata)			
			String theModuleID = confMngrConnService.getEntityMetadata("SSI", moduleID).getMicroservice().get(0);	// The first one.
			
			MsMetadata theMs = confMngrConnService.getMicroservicesByApiClass("IS").getMs(theModuleID); // This is the Identity Source microservice
			
			String msToken =  null;
			msToken = smConn.generateToken (sessionID, theModuleID);
			
			log.info ("token generated");
			
			// Update sessionData: ssiWallet = uPort
			smConn.updateVariable(sessionID,"ssiWallet", moduleID);
			
			// Returns msToken and moduleTrigger to client
			// with details to connect to the VC module to do DID Auth.
			ModuleTrigger moduleTrigger = new ModuleTrigger();

			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			String statusMessage = Constants.ID_RETRIEVED_MSG;
			String mainCode = Constants.SUCESS_CODE;;
			String secondaryCode = Constants.ID_RETRIEVED_CODE;
			
			theStatus.setMessage(statusMessage);
			theStatus.setMainCode(mainCode); 
			theStatus.setSecondaryCode(secondaryCode); 
			moduleTrigger.setStatus (theStatus);		
			
			ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
			theAccess.setAddress(theMs.getPublishedAPI().get(0).getApiEndpoint()); // "theUrl"
			theAccess.setBinding(BindingEnum.POST); // theMs.getPublishedAPI().get(0).getApiConnectionType()
			theAccess.setBodyContent("TO ASK: bodyContent");
			theAccess.setContentType("TO ASK: contentType");
			moduleTrigger.setAccess (theAccess);
			
			moduleTrigger.setAccess (theAccess);
			moduleTrigger.setPayload(msToken); // The object to be returned.
			
			
			return moduleTrigger;
			
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}
	

}

