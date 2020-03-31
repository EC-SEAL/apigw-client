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
package eu.seal.apigw.cl.rest_api.services.derivation;


import java.util.Iterator;
import java.util.List;
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
import eu.seal.apigw.cl.domain.PublishedApiType;
import eu.seal.apigw.cl.sm_api.SessionManagerConnService;

@Service
public class ClModuleIDGenerateGetServiceImp implements ClModuleIDGenerateGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClModuleIDGenerateGetServiceImp.class);
	
	@Autowired
	private SessionManagerConnService smConn;
	
	@Autowired
	private ConfMngrConnService confMngrConnService;

	
	@Override
	public ModuleTrigger clModuleIDGenerateGet (String sessionID, String moduleID) throws Exception {
		
		log.info("moduleID: " + moduleID);
			
		// UC6.01
		
		
		try {
			String theModuleID = null;
			MsMetadata theMs = null;
			String msToken =  null;
			PublishedApiType thePublishedApi = null;
			List<PublishedApiType> thePublishedApiList = null;
			
			
			switch (moduleID.toLowerCase()) {
			
				case "uuid":
					
					// Update sessionData: derivation = UUID
					smConn.updateVariable(sessionID,"derivation", moduleID.toUpperCase());
					
					// To generate token: issuer CL (got from the msMetadataList ConfMngr); obtaining the receiver:			
					theModuleID = confMngrConnService.getEntityMetadata("DERIVATION", moduleID).getMicroservice().get(0);	// The first one.
					
					theMs = confMngrConnService.getMicroservicesByApiClass("IDBOOT").getMs(theModuleID); // This is the IDBOOT microservice
									
					//For fulfilling theAccess (see bellow)
					thePublishedApiList = theMs.getPublishedAPI();
					
					Iterator<PublishedApiType> paIterator0 = thePublishedApiList.iterator();
					PublishedApiType auxPublishedApi0 = null;
					while (paIterator0.hasNext()) {
						
						auxPublishedApi0 = paIterator0.next();						  
						if (auxPublishedApi0.getApiCall().equals("generate")	) { // idboot/generate
							thePublishedApi = auxPublishedApi0;  
							break; 
						}
					}

					break;
					
				default:
					log.info ("BE AWARE: to be defined: " + moduleID);
			}
			
			
			// Returns msToken and moduleTrigger to client
			
			msToken = smConn.generateToken (sessionID, theModuleID);		
			log.info ("token generated");
			
			log.info("theMS: " + (theMs != null ? theMs.getMsId(): theMs));
			log.info("thePublishedApi: " + (thePublishedApi != null ? thePublishedApi.getApiCall() : thePublishedApi));
			
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			if (theMs != null) {
			
			 if (thePublishedApi != null ) {
				String statusMessage = Constants.ID_DERIVED_MSG;
				String mainCode = Constants.SUCESS_CODE;;
				String secondaryCode = Constants.ID_DERIVED_CODE;
				
				theStatus.setMessage(statusMessage);
				theStatus.setMainCode(mainCode); 
				theStatus.setSecondaryCode(secondaryCode); 
				moduleTrigger.setStatus (theStatus);		
				
				ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
				theAccess.setAddress(thePublishedApi.getApiEndpoint()); // "theUrl"
				theAccess.setBinding(BindingEnum.POST); // thePublishedApi.getApiConnectionType()
				theAccess.setBodyContent("TODO: bodyContent"); // If the access method requires to transfer data on the body of the request, it will be written here
				theAccess.setContentType("TODO: contentType"); // the MIME type of the body, if any
				moduleTrigger.setAccess (theAccess);
				
				moduleTrigger.setAccess (theAccess);
			 }
			 else {
				theStatus.setMessage(Constants.NO_ID_DERIVED);
				theStatus.setMainCode(Constants.FAIL_CODE); 
				theStatus.setSecondaryCode(Constants.NO_ID_DERIVED_CODE); 
				moduleTrigger.setStatus (theStatus);
				moduleTrigger.setAccess (null);
			 }
			
			 moduleTrigger.setPayload(msToken); // The object to be returned.
			}
			else {
				theStatus.setMessage(Constants.INVALID_MODULE_ID_MSG); 
				theStatus.setMainCode(Constants.FAIL_CODE); 
				theStatus.setSecondaryCode(Constants.INVALID_MODULE_ID_CODE);
				moduleTrigger.setAccess (null);
				moduleTrigger.setPayload (null);
			}
						
			return moduleTrigger;
			
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}
	

}

