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
package eu.seal.apigw.cl.rest_api.services.identlnk;


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
import eu.seal.apigw.cl.domain.DataSet;
import eu.seal.apigw.cl.domain.DisplayableList;
import eu.seal.apigw.cl.domain.LinkRequest;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess.BindingEnum;
import eu.seal.apigw.cl.sm_api.SessionManagerConnService;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;
import eu.seal.apigw.cl.domain.MsMetadata;
import eu.seal.apigw.cl.domain.PublishedApiType;


@Service
public class ClModuleIDRequestResultGetServiceImp implements ClModuleIDRequestResultGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClModuleIDRequestResultGetServiceImp.class);
	
	@Autowired
	private ConfMngrConnService confMngrConnService;
	
	@Autowired
	private SessionManagerConnService smConn;
	
	@Override
	public ModuleTrigger clModuleIDRequestResultGet (String sessionID, String moduleID, String requestId) throws Exception {
		
		log.info("moduleID: " + moduleID);
		String theModuleID = null;
		MsMetadata theMs = null;
		
		//TODO: the usage of moduleID is pending for Paco's approval: email 17.4.2020, UC7.02 update by Ross
		
		// UC7.02: returns the uri related to the requestId to be found in the apigwLinkRequestList session variable
		try {
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();		
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			theModuleID = confMngrConnService.getEntityMetadata("LINKING", moduleID).getMicroservice().get(0); // The first one.
			log.info("theModuleID: " + theModuleID);
			
			theMs = confMngrConnService.getMicroservicesByApiClass("LINK").getMs(theModuleID); // This is the Link microservice
			if (theMs != null) {
				log.info("theMS: " + theMs.getMsId());
				
				PublishedApiType thePublishedApi = null;
				//For fulfilling theAccess (see bellow)
				List<PublishedApiType> thePublishedApiList = theMs.getPublishedAPI();
				
				Iterator<PublishedApiType> paIterator = thePublishedApiList.iterator();
				PublishedApiType auxPublishedApi = null;
				while (paIterator.hasNext()) {
					
					auxPublishedApi = paIterator.next();
					  
					if (auxPublishedApi.getApiCall().equals("request-submit")	) {// /link/request/submit
						
						thePublishedApi = auxPublishedApi;
						break; 
					}
					  	  
				}
				log.info("thePublishedApi: " + (thePublishedApi != null ? thePublishedApi.getApiCall() : thePublishedApi));
				
				String thePayload = null;
				BindingEnum theBinding = null;

				// Returns moduleTrigger to client
				// it returns the address of the API to call the related linking microservice
				if (thePublishedApi != null ) {
					
					String statusMessage = Constants.LINKING_RESULT_MSG;
					String mainCode = Constants.SUCESS_CODE;;
					String secondaryCode = Constants.LINKING_RESULT_CODE;
					
					theStatus.setMessage(statusMessage);
					theStatus.setMainCode(mainCode); 
					theStatus.setSecondaryCode(secondaryCode); 
					moduleTrigger.setStatus (theStatus);		
					
					ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
//					theAccess.setAddress(thePublishedApi.getApiEndpoint()); // "theUrl"
//					theAccess.setBinding(theBinding); // thePublishedApi.getApiConnectionType()
					
					Object objApigwLinkRequestList = smConn.readVariable(sessionID, "apigwLinkRequestList");
					if (objApigwLinkRequestList != null) {
						log.info("apigwLinkRequestList: " + objApigwLinkRequestList.toString());
						DisplayableList apigwLinkRequestList = (new ObjectMapper()).readValue(objApigwLinkRequestList.toString(),DisplayableList.class);
						if (apigwLinkRequestList.size() != 0) {
						
							//TODO
							// Search for the Ms related to the requestId
							
							
							// Ask CM for the below data:
//							theAccess.setAddress(); // "theUrl"
//							theAccess.setBinding(); // thePublishedApi.getApiConnectionType()
							;
						}
						else
							//TODO
							;
					}
					
					
					theAccess.setContentType("TO ASK: contentType");
					theAccess.setBodyContent("TO ASK: bodyContent");
					moduleTrigger.setAccess (theAccess);
					
					moduleTrigger.setAccess (theAccess);
				}
				else {
					theStatus.setMessage(Constants.NO_LINKING_RESULT_MSG);
					theStatus.setMainCode(Constants.FAIL_CODE); 
					theStatus.setSecondaryCode(Constants.NO_LINKING_RESULT_CODE); 
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

