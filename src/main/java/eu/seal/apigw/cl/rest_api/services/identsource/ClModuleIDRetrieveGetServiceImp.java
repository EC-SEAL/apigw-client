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
			
			BindingEnum theBinding = null;
			
			
			switch (moduleID.toLowerCase()) {
			
				case "uport":
				case "sovrin":
					
					// Update sessionData: ssiWallet = uPort
					smConn.updateVariable(sessionID,"ssiWallet", moduleID);
					
					// To generate token: issuer CL (got from the msMetadataList ConfMngr); obtaining the receiver:			
					theModuleID = confMngrConnService.getEntityMetadata("SSI", moduleID).getMicroservice().get(0);	// The first one.
					
					theMs = confMngrConnService.getMicroservicesByApiClass("VC").getMs(theModuleID); // This is the VC microservice
									
					//For fulfilling theAccess (see bellow)
					thePublishedApiList = theMs.getPublishedAPI();
					
					Iterator<PublishedApiType> paIterator0 = thePublishedApiList.iterator();
					PublishedApiType auxPublishedApi0 = null;
					while (paIterator0.hasNext()) {
						
						auxPublishedApi0 = paIterator0.next();						  
						if (auxPublishedApi0.getApiCall().equals("didAuth")	) { // vc/didAuth
							thePublishedApi = auxPublishedApi0;  
							break; 
						}
					}
					
					theBinding = BindingEnum.POST_REDIRECT;

					break;
					
				case "eidas":
				case "edugain":
				
					
					// Update sessionData: apRequest, apMetadata; dataStore exists in the session already.
					
					AttributeSet myApRequest = new AttributeSet();
					myApRequest.setId( "AP_" + UUID.randomUUID().toString());
					myApRequest.setType(AttributeSet.TypeEnum.REQUEST);
					myApRequest.setIssuer(confMngrConnService.getMicroservicesByApiClass("CL").get(0).getMsId()); // The unique client
					myApRequest.setRecipient(confMngrConnService.getEntityMetadata("ATTRSOURCE", moduleID).getMicroservice().get(0)); // the first one
					myApRequest.setAttributes(null);
					
					ObjectMapper objMapper = new ObjectMapper();
					smConn.updateVariable(sessionID, "apRequest", objMapper.writeValueAsString(myApRequest));
					
					EntityMetadata myApMetadata = new EntityMetadata();
					myApMetadata.setEntityId("AP_" + UUID.randomUUID().toString());
					
					EntityMetadata auxApMetadata = confMngrConnService.getEntityMetadataSet(moduleID.toUpperCase()).getMsEntities (myApRequest.getRecipient()).get(0);
					
					//log.info("auxApMetadata: " + auxApMetadata.toString());
					
					myApMetadata.setLocation(auxApMetadata.getLocation());
					myApMetadata.setDefaultDisplayName(auxApMetadata.getDefaultDisplayName());
					myApMetadata.setDisplayNames(auxApMetadata.getDisplayNames());
					myApMetadata.setClaims(auxApMetadata.getClaims());
					myApMetadata.setEncryptResponses(auxApMetadata.isEncryptResponses());
					myApMetadata.setEndpoints(auxApMetadata.getEndpoints());
					myApMetadata.setLogo(auxApMetadata.getLogo());
					myApMetadata.setMicroservice(auxApMetadata.getMicroservice());
					myApMetadata.setOtherData(auxApMetadata.getOtherData());
					myApMetadata.setProtocol(auxApMetadata.getProtocol());
					myApMetadata.setSecurityKeys(auxApMetadata.getSecurityKeys());
					myApMetadata.setSignResponses(auxApMetadata.isSignResponses());
					myApMetadata.setSupportedEncryptionAlg(auxApMetadata.getSupportedEncryptionAlg());
					myApMetadata.setSupportedSigningAlg(auxApMetadata.getSupportedSigningAlg());
					
					log.info ("myApMetadata: " + myApMetadata.toString());
					
					ObjectMapper objMapper1 = new ObjectMapper();
					smConn.updateVariable(sessionID, "apMetadata", objMapper1.writeValueAsString(myApMetadata));
					
					// To generate token: issuer CL (got from the msMetadataList ConfMngr); obtaining the receiver:			
					theModuleID = confMngrConnService.getEntityMetadata("ATTRSOURCE", moduleID).getMicroservice().get(0);	// The first one.
					
					theMs = confMngrConnService.getMicroservicesByApiClass("IS").getMs(theModuleID); // This is the Identity Source microservice
									
					//For fulfilling theAccess (see bellow)
					thePublishedApiList = theMs.getPublishedAPI();
					
					Iterator<PublishedApiType> paIterator = thePublishedApiList.iterator();
					PublishedApiType auxPublishedApi = null;
					while (paIterator.hasNext()) {
						
						auxPublishedApi = paIterator.next();						  
						if (auxPublishedApi.getApiCall().equals("query")) { // is/query
							thePublishedApi = auxPublishedApi;
							break; 
						}
					}
					
					theBinding = BindingEnum.POST_REDIRECT;
					
					break;
				case "emrtd":
					AttributeSet myApRequest1 = new AttributeSet();
					myApRequest1.setId( "AP_" + UUID.randomUUID().toString());
					myApRequest1.setType(AttributeSet.TypeEnum.REQUEST);
					myApRequest1.setIssuer(confMngrConnService.getMicroservicesByApiClass("CL").get(0).getMsId()); // The unique client
					myApRequest1.setRecipient(confMngrConnService.getEntityMetadata("ATTRSOURCE", moduleID).getMicroservice().get(0)); // the first one
					myApRequest1.setAttributes(null);
					
					ObjectMapper objMapper2 = new ObjectMapper();
					smConn.updateVariable(sessionID, "apRequest", objMapper2.writeValueAsString(myApRequest1));
					
					EntityMetadata myApMetadata1 = new EntityMetadata();
					myApMetadata1.setEntityId("AP_" + UUID.randomUUID().toString());
					
					EntityMetadata auxApMetadata1 = confMngrConnService.getEntityMetadataSet(moduleID.toUpperCase()).getMsEntities (myApRequest1.getRecipient()).get(0);
					
					//log.info("auxApMetadata: " + auxApMetadata.toString());
					
					myApMetadata1.setLocation(auxApMetadata1.getLocation());
					myApMetadata1.setDefaultDisplayName(auxApMetadata1.getDefaultDisplayName());
					myApMetadata1.setDisplayNames(auxApMetadata1.getDisplayNames());
					myApMetadata1.setClaims(auxApMetadata1.getClaims());
					myApMetadata1.setEncryptResponses(auxApMetadata1.isEncryptResponses());
					myApMetadata1.setEndpoints(auxApMetadata1.getEndpoints());
					myApMetadata1.setLogo(auxApMetadata1.getLogo());
					myApMetadata1.setMicroservice(auxApMetadata1.getMicroservice());
					myApMetadata1.setOtherData(auxApMetadata1.getOtherData());
					myApMetadata1.setProtocol(auxApMetadata1.getProtocol());
					myApMetadata1.setSecurityKeys(auxApMetadata1.getSecurityKeys());
					myApMetadata1.setSignResponses(auxApMetadata1.isSignResponses());
					myApMetadata1.setSupportedEncryptionAlg(auxApMetadata1.getSupportedEncryptionAlg());
					myApMetadata1.setSupportedSigningAlg(auxApMetadata1.getSupportedSigningAlg());
					
					log.info ("myApMetadata1: " + myApMetadata1.toString());
					
					ObjectMapper objMapper3 = new ObjectMapper();
					smConn.updateVariable(sessionID, "apMetadata", objMapper3.writeValueAsString(myApMetadata1));
					
					// To generate token: issuer CL (got from the msMetadataList ConfMngr); obtaining the receiver:			
					theModuleID = confMngrConnService.getEntityMetadata("ATTRSOURCE", moduleID).getMicroservice().get(0);	// The first one.
					
					theMs = confMngrConnService.getMicroservicesByApiClass("IS").getMs(theModuleID); // This is the Identity Source microservice
									
					//For fulfilling theAccess (see bellow)
					thePublishedApiList = theMs.getPublishedAPI();
					
					Iterator<PublishedApiType> paIterator1 = thePublishedApiList.iterator();
					PublishedApiType auxPublishedApi1 = null;
					while (paIterator1.hasNext()) {
						
						auxPublishedApi1 = paIterator1.next();						  
						if (auxPublishedApi1.getApiCall().equals("isLocal")) { // local eMRTD app
							thePublishedApi = auxPublishedApi1;
							break; 
						}
					}
					
					theBinding = BindingEnum.POST_REDIRECT;
					break;
					
				default:
					log.info ("BE AWARE: unknown identity source module: " + moduleID);
			}
			
			
			// Returns msToken and moduleTrigger to client
			// with details to connect to the VC module to do DID Auth, or to the IS modules to do eIDAS/eduGAIN/eMRTD Auth.
			
			msToken = smConn.generateToken (sessionID, theModuleID);		
			log.info ("token generated");
			
			log.info("theMS: " + (theMs != null ? theMs.getMsId(): theMs));
			log.info("thePublishedApi: " + (thePublishedApi != null ? thePublishedApi.getApiCall() : thePublishedApi));
			
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			if (theMs != null) {
			
			 if (thePublishedApi != null ) {
				
				theStatus.setMessage(Constants.ID_RETRIEVED_MSG);
				theStatus.setMainCode(Constants.SUCESS_CODE); 
				theStatus.setSecondaryCode(Constants.ID_RETRIEVED_CODE); 
				moduleTrigger.setStatus (theStatus);		
				
				ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
				theAccess.setAddress(thePublishedApi.getApiEndpoint()); // "theUrl"
				theAccess.setBinding(theBinding); // thePublishedApi.getApiConnectionType()
				theAccess.setBodyContent("TODO: bodyContent"); // If the access method requires to transfer data on the body of the request, it will be written here
				theAccess.setContentType("TODO: contentType"); // the MIME type of the body, if any
				moduleTrigger.setAccess (theAccess);
				
				moduleTrigger.setAccess (theAccess);
				if (!moduleID.toLowerCase().contains ("emrtd"))
					moduleTrigger.setPayload(msToken);
				else 
					moduleTrigger.setPayload (null);
			 }
			 else {
				theStatus.setMessage(Constants.NO_ID_RETRIEVED);
				theStatus.setMainCode(Constants.FAIL_CODE); 
				theStatus.setSecondaryCode(Constants.NO_ID_RETRIEVED_CODE); 
				moduleTrigger.setStatus (theStatus);
				moduleTrigger.setAccess (null);
				moduleTrigger.setPayload (null);
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

