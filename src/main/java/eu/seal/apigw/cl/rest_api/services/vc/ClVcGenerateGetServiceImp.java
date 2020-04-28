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
package eu.seal.apigw.cl.rest_api.services.vc;


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
public class ClVcGenerateGetServiceImp implements ClVcGenerateGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClVcGenerateGetServiceImp.class);
	
	@Autowired
	private SessionManagerConnService smConn;
	
	@Autowired
	private ConfMngrConnService confMngrConnService;

	
	@Override
	public ModuleTrigger clVcGenerateGet (String sessionID, String moduleID) throws Exception {
		
		log.info("moduleID: " + moduleID);
			
		// UC5.01
		
		
		try {
			String theModuleID = null;
			MsMetadata theMs = null;
			String msToken =  null;
			PublishedApiType thePublishedApi = null;
			List<PublishedApiType> thePublishedApiList = null;
			
			
			switch (moduleID.toLowerCase()) {
			
				case "uport":
					
					// Update sessionData: derivation = UUID
					smConn.updateVariable(sessionID,"VCDefinition", moduleID.toUpperCase());
					
					// To generate token: issuer CL (got from the msMetadataList ConfMngr); obtaining the receiver:			
					theModuleID = confMngrConnService.getEntityMetadata("VCDEFINITIONS", moduleID).getMicroservice().get(0);	// The first one.
					
					theMs = confMngrConnService.getMicroservicesByApiClass("VC").getMs(theModuleID); // This is the IDBOOT microservice
									
					//For fulfilling theAccess (see bellow)
					thePublishedApiList = theMs.getPublishedAPI();
					
					Iterator<PublishedApiType> paIterator0 = thePublishedApiList.iterator();
					PublishedApiType auxPublishedApi0 = null;
					while (paIterator0.hasNext()) {
						
						auxPublishedApi0 = paIterator0.next();						  
						if (auxPublishedApi0.getApiCall().equals("issue")	) { // vc/issue
							thePublishedApi = auxPublishedApi0;  
							break; 
						}
					}

					break;
					
				default:
					log.info ("BE AWARE: to be defined: " + moduleID);
			}
			
			
			// Returns msToken and moduleTrigger to client
			
			
			log.info("theMS: " + (theMs != null ? theMs.getMsId(): theMs));
			log.info("thePublishedApi: " + (thePublishedApi != null ? thePublishedApi.getApiCall() : thePublishedApi));
			
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			if (theMs != null) {
			
			 if (thePublishedApi != null ) {
				 
				msToken = smConn.generateToken (sessionID, theModuleID);		
				log.info ("token generated");
				
				theStatus.setMessage(Constants.VC_ISSUED_MSG);
				theStatus.setMainCode(Constants.SUCESS_CODE); 
				theStatus.setSecondaryCode(Constants.VC_ISSUED_CODE); 
				moduleTrigger.setStatus (theStatus);		
				
				ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
				theAccess.setAddress(thePublishedApi.getApiEndpoint()); // "theUrl"
				theAccess.setBinding(BindingEnum.POST); // thePublishedApi.getApiConnectionType()
				theAccess.setBodyContent("TODO: bodyContent"); // If the access method requires to transfer data on the body of the request, it will be written here
				theAccess.setContentType("TODO: contentType"); // the MIME type of the body, if any
				moduleTrigger.setAccess (theAccess);
				
				moduleTrigger.setAccess (theAccess);
				moduleTrigger.setPayload(msToken);
			 }
			 else {
				theStatus.setMessage(Constants.NO_VC_ISSUED);
				theStatus.setMainCode(Constants.FAIL_CODE); 
				theStatus.setSecondaryCode(Constants.NO_VC_ISSUED_CODE); 
				moduleTrigger.setStatus (theStatus);
				moduleTrigger.setAccess (null);
				moduleTrigger.setPayload(null);
			 }
			
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

