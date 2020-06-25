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
import eu.seal.apigw.cl.sm_api.SessionManagerConnService;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;
import eu.seal.apigw.cl.domain.MsMetadata;
import eu.seal.apigw.cl.domain.PublishedApiType;


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
		String theModuleID = null;
		MsMetadata theMs = null;
		
		
		// UC1.02, UC1.03, UC1.05, UC1.06
		try {
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();		
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			theModuleID = confMngrConnService.getEntityMetadata("PERSISTENCE", moduleID).getMicroservice().get(0); // The first one.
			log.info("theModuleID: " + theModuleID);
			
			theMs = confMngrConnService.getMicroservicesByApiClass("PER").getMs(theModuleID); // This is the Persistence microservice
			if (theMs != null) {
				log.info("theMS: " + theMs.getMsId());
				
				PublishedApiType thePublishedApi = null;
				//For fulfilling theAccess (see bellow)
				List<PublishedApiType> thePublishedApiList = theMs.getPublishedAPI();
				
				Iterator<PublishedApiType> paIterator = thePublishedApiList.iterator();
				PublishedApiType auxPublishedApi = null;
				while (paIterator.hasNext()) {
					
					auxPublishedApi = paIterator.next();
					  
					if (auxPublishedApi.getApiCall().equals("load")	) {// per/load
						
						thePublishedApi = auxPublishedApi;
						break; 
					}
					  	  
				}
				log.info("thePublishedApi: " + (thePublishedApi != null ? thePublishedApi.getApiCall() : thePublishedApi));
				
				String thePayload = null;
				BindingEnum theBinding = null;
/*				switch (moduleID.toLowerCase()) {
				
					case "mobile":
						thePayload = sessionID;
						
						theBinding = BindingEnum.GET;
						
						// Update sessionData: PDS = mobile
						smConn.updateVariable(sessionID,"PDS", moduleID);
						break;
						
					case "onedrive":
					case "googledrive":
					case "browser":
					*/
						String msToken =  null;
						
						msToken = smConn.generateToken (sessionID, theModuleID);
						thePayload = msToken;
						
						log.info ("token generated");
						
						theBinding = BindingEnum.POST;
						
						smConn.updateVariable(sessionID,"PDS", moduleID);
/*						break;
						
					default:
						log.info ("BE AWARE: unknown persistence module: " + moduleID);
				}
*/				
						
				// Returns moduleTrigger to client
				// it returns the address of the API to call .... /per/load
	
				
				
				if (thePublishedApi != null ) {
					
					theStatus.setMessage(Constants.PERSISTENCE_LOADED_MSG);
					theStatus.setMainCode(Constants.SUCESS_CODE); 
					theStatus.setSecondaryCode(Constants.PERSISTENCE_LOADED_CODE); 
					moduleTrigger.setStatus (theStatus);		
					
					ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
					theAccess.setAddress(thePublishedApi.getApiEndpoint()); // "theUrl"
					theAccess.setBinding(theBinding); // thePublishedApi.getApiConnectionType()
					
					Object objDatastore = smConn.readVariable(sessionID, "dataStore");
					if (objDatastore != null) {
						theAccess.setBodyContent(objDatastore.toString());
						log.info("dataStore: " + objDatastore.toString());
					}
					else
						theAccess.setBodyContent(null);
					
					theAccess.setContentType("TO ASK: contentType");
					moduleTrigger.setAccess (theAccess);
					
					moduleTrigger.setAccess (theAccess);
				}
				else {
					theStatus.setMessage(Constants.NO_PERSISTENCE_LOADED_MSG);
					theStatus.setMainCode(Constants.FAIL_CODE); 
					theStatus.setSecondaryCode(Constants.NO_PERSISTENCE_LOADED_CODE); 
					moduleTrigger.setStatus (theStatus);
					moduleTrigger.setAccess (null);
				}
				moduleTrigger.setPayload(thePayload);
			}
			else {
				theStatus.setMessage(Constants.INVALID_MODULE_ID_MSG); 
				theStatus.setMainCode(Constants.FAIL_CODE); 
				theStatus.setSecondaryCode(Constants.INVALID_MODULE_ID_CODE);
				moduleTrigger.setStatus (theStatus);
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

