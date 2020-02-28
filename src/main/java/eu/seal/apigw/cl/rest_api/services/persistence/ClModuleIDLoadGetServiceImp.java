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
package eu.seal.apigw.cl.rest_api.services.persistence;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.seal.apigw.cl.cm_api.ConfMngrConnService;
import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess.BindingEnum;
import eu.seal.apigw.cl.sm_api.SessionManagerConnService;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;
import eu.seal.apigw.cl.domain.MsMetadata;


@Service
public class ClModuleIDLoadGetServiceImp implements ClModuleIDLoadGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClModuleIDLoadGetServiceImp.class);
	
	@Autowired
	private ConfMngrConnService confMngrConnService;
	
	@Autowired
	private SessionManagerConnService smConn;
	
	@Override
	public ModuleTrigger clModuleIDLoadGet (String sessionID, String moduleID) throws Exception {
		
		log.info("moduleID: " + moduleID);
		try {
			//moduleID was previously stored in settings as "localMobile", "googleDrive"
			
			String theModuleID = confMngrConnService.getEntityMetadata("PERSISTENCE", moduleID).getMicroservice().get(0); // The first one.
			
			MsMetadata theMs = confMngrConnService.getMicroservicesByApiClass("PER").getMs(theModuleID); // This is the Identity Source microservice
			log.info("theMS: " + theMs.getMsId());
			
			String thePayload = null;
			BindingEnum theBinding = null;
			switch (moduleID.toLowerCase()) {
			
				case "localmobile":
					thePayload = sessionID;
					
					theBinding = BindingEnum.GET;
					break;
					
				case "googledrive":
					String msToken =  null;
					
					msToken = smConn.generateToken (sessionID, theModuleID);
					thePayload = msToken;
					
					log.info ("token generated");
					
					theBinding = BindingEnum.POST;
					
					// Update sessionData: PDS = googleDrive
					smConn.updateVariable(sessionID,"PDS", moduleID);
					break;
					
				case "onedrive":
				case "remotemobile":
				case "browser":
					log.info ("BE AWARE: undefined persistence module: " + moduleID);
					break;
				
				default:
					log.info ("BE AWARE: unknown persistence module: " + moduleID);
			}
			
			// Returns moduleTrigger to client
			// it returns the address of the API to call .... /per/load

			ModuleTrigger moduleTrigger = new ModuleTrigger();

			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			String statusMessage = Constants.PERSISTENCE_LOADED_MSG;
			String mainCode = Constants.SUCESS_CODE;;
			String secondaryCode = Constants.PERSISTENCE_LOADED_CODE;
			
			theStatus.setMessage(statusMessage);
			theStatus.setMainCode(mainCode); 
			theStatus.setSecondaryCode(secondaryCode); 
			moduleTrigger.setStatus (theStatus);		
			
			ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
			theAccess.setAddress(theMs.getPublishedAPI().get(0).getApiEndpoint()); // "theUrl"
			theAccess.setBinding(theBinding); // theMs.getPublishedAPI().get(0).getApiConnectionType()
			theAccess.setBodyContent("TO ASK: bodyContent");
			theAccess.setContentType("TO ASK: contentType");
			moduleTrigger.setAccess (theAccess);
			
			moduleTrigger.setAccess (theAccess);
			moduleTrigger.setPayload(thePayload);
			
			
			return moduleTrigger;
			
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}
	

}

