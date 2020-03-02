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


import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.cm_api.ConfMngrConnService;
import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.AttributeSet;
import eu.seal.apigw.cl.domain.EntityMetadata;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess.BindingEnum;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;
import eu.seal.apigw.cl.domain.MsMetadata;
import eu.seal.apigw.cl.domain.PublishedApiType;
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
		
		log.info("moduleID: " + moduleID);
			
		// UC1.04
		// UC3.02
		
		try {
			String theModuleID = null;
			MsMetadata theMs = null;
			String msToken =  null;
			PublishedApiType thePublishedApi = null;
			List<PublishedApiType> thePublishedApiList = null;
			
			
			switch (moduleID.toLowerCase()) {
			
				case "uport":
					
					// Update sessionData: ssiWallet = uPort
					smConn.updateVariable(sessionID,"ssiWallet", moduleID);
					
					// To generate token: issuer CL (got from the msMetadataList ConfMngr); obtaining the receiver:			
					theModuleID = confMngrConnService.getEntityMetadata("SSIWALLET", moduleID).getMicroservice().get(0);	// The first one.
					
					theMs = confMngrConnService.getMicroservicesByApiClass("VC").getMs(theModuleID); // This is the VC microservice
									
					//For fulfilling theAccess (see bellow)
					thePublishedApiList = theMs.getPublishedAPI();
					
					Iterator<PublishedApiType> paIterator0 = thePublishedApiList.iterator();
					while (paIterator0.hasNext()) {
						
						thePublishedApi = paIterator0.next();						  
						if (thePublishedApi.getApiCall().equals("didAuth")	) // vc/didAuth
							  break; 						  	  
					}

					break;
					
				case "eidas":
				case "edugain":
				case "emrtd":
					
					// Update sessionData: apRequest, apMetadata; dataStore exists in the session already.
					
					AttributeSet myApRequest = new AttributeSet();
					myApRequest.setId( "AP_" + UUID.randomUUID().toString());
					myApRequest.setType(AttributeSet.TypeEnum.REQUEST);
					myApRequest.setIssuer(confMngrConnService.getMicroservicesByApiClass("CL").get(0).getMsId()); // The unique client
					myApRequest.setRecipient(confMngrConnService.getEntityMetadata("AUTHSOURCE", moduleID).getMicroservice().get(0)); // the first one
					myApRequest.setAttributes(null);
					
					ObjectMapper objMapper = new ObjectMapper();
					smConn.updateVariable(sessionID, "apRequest", objMapper.writeValueAsString(myApRequest));
					
					EntityMetadata myApMetadata = new EntityMetadata();
					myApMetadata.setEntityId("AP_" + UUID.randomUUID().toString());
					//TODO
					myApMetadata.setLocation(null);
					myApMetadata.setDefaultDisplayName("TODOdefaultDisplayName");
					myApMetadata.setDisplayNames(null);
					myApMetadata.setClaims(null);
					myApMetadata.setEncryptResponses(false);
					myApMetadata.setEndpoints(null);
					myApMetadata.setLogo(null);
					myApMetadata.setMicroservice(null);
					myApMetadata.setOtherData(null);
					myApMetadata.setProtocol("TODOprotocol");
					myApMetadata.setSecurityKeys(null);
					myApMetadata.setSignResponses(false);
					myApMetadata.setSupportedEncryptionAlg(null);
					myApMetadata.setSupportedSigningAlg(null);
					
					ObjectMapper objMapper1 = new ObjectMapper();
					smConn.updateVariable(sessionID, "apMetadata", objMapper1.writeValueAsString(myApMetadata));
					
					// To generate token: issuer CL (got from the msMetadataList ConfMngr); obtaining the receiver:			
					theModuleID = confMngrConnService.getEntityMetadata("ATTRSOURCE", moduleID).getMicroservice().get(0);	// The first one.
					
					theMs = confMngrConnService.getMicroservicesByApiClass("IS").getMs(theModuleID); // This is the Identity Source microservice
									
					//For fulfilling theAccess (see bellow)
					thePublishedApiList = theMs.getPublishedAPI();
					
					Iterator<PublishedApiType> paIterator = thePublishedApiList.iterator();
					while (paIterator.hasNext()) {
						
						thePublishedApi = paIterator.next();						  
						if (thePublishedApi.getApiCall().equals("query")	) // is/query
							  break; 						  	  
					}
					
					break;
					
				default:
					log.info ("BE AWARE: unknown identity source module: " + moduleID);
			}
			
			
			// Returns msToken and moduleTrigger to client
			// with details to connect to the VC module to do DID Auth, or to the IS modules to do eIDAS/eduGAIN/eMRTD Auth.
			
			msToken = smConn.generateToken (sessionID, theModuleID);		
			log.info ("token generated");
			
			log.info("theMS: " + theMs.getMsId());
			log.info("thePublishedApi: " + thePublishedApi.getApiCall());
			
			
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
			theAccess.setAddress(thePublishedApi.getApiEndpoint()); // "theUrl"
			theAccess.setBinding(BindingEnum.POST); // thePublishedApi.getApiConnectionType()
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

