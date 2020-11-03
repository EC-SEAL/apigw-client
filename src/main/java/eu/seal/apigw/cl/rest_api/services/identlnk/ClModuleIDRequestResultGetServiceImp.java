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

//import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import eu.seal.apigw.cl.cm_api.ConfMngrConnService;
import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.DisplayableList;
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
		
//		log.info("moduleID: " + moduleID);

		MsMetadata theMs = null;
		String theModuleID = null;
		
		//TODO: the usage of moduleID is removed (Paco's approval: email 20.4.2020). The interfaces.yml and UC7.02 to be updated
		//
		
		// UC7.02: returns the uri related to the requestId to be found in the apigwLinkRequestList session variable
		try {
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();		
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			Object objApigwLinkRequestList = smConn.readVariable(sessionID, "apigwLinkRequestList");
			if (objApigwLinkRequestList != null) {
				log.info("apigwLinkRequestList: " + objApigwLinkRequestList.toString());
				DisplayableList apigwLinkRequestList = (new ObjectMapper()).readValue(objApigwLinkRequestList.toString(),DisplayableList.class);
				if (apigwLinkRequestList.size() != 0) {
					String thePayload = null;
					
					theModuleID = confMngrConnService.getEntityMetadata("LINKING", moduleID).getMicroservice().get(0); // The first one.
					log.info("theModuleID: " + theModuleID);
					thePayload = smConn.generateToken (sessionID, theModuleID);
				
					// Search for the Ms related to the requestId
					String myMs = null;
					//NameValuePair ms = null;
					Iterator<Object> msIterator = apigwLinkRequestList.iterator();
					while (msIterator.hasNext()) {
						//ms = (NameValuePair) msIterator.next();	
						//ms = (new ObjectMapper()).readValue(msIterator.next().toString(),NameValuePair.class); //no quotes at all
//						if (ms.getName().equals(requestId)) {
//							myMs = ms.getValue();
//							break; 
//						}
						
						JsonObject myJSONms = new JsonParser().parse(msIterator.next().toString()).getAsJsonObject();						
						log.info("myJSONms: " + myJSONms.toString());
						
						if (myJSONms.get("name").toString().equals("\"" + requestId + "\"")) {
							myMs = myJSONms.get("value").toString();
							myMs = myMs.replaceAll("^\"|\"$", ""); // Without quotes
							break;
						}				  	  
					}
					
					
					PublishedApiType thePublishedApi = null;
					//For fulfilling theAccess (see bellow)
					if (myMs != null) {
						// Ask CM for data
						theMs = confMngrConnService.getMicroservicesByApiClass("LINK").getMs(myMs); // This is the Link microservice
						if (theMs != null) {
							log.info("theMS: " + theMs.getMsId());
														
							List<PublishedApiType> thePublishedApiList = theMs.getPublishedAPI();
							
							Iterator<PublishedApiType> paIterator = thePublishedApiList.iterator();
							PublishedApiType auxPublishedApi = null;
							while (paIterator.hasNext()) {
								
								auxPublishedApi = paIterator.next();								  
								if (auxPublishedApi.getApiCall().equals("result-get")	) {// /link/{requestId}/result/get ---> It's front channel									
									thePublishedApi = auxPublishedApi;
									break; 
								}								  	  
							}
							log.info("thePublishedApi: " + (thePublishedApi != null ? thePublishedApi.getApiCall() : thePublishedApi));	
							
							//thePayload = smConn.generateToken (sessionID, myMs);
						}
					}
	
					// Returns moduleTrigger to client
					// it returns the address of the API to call the related linking microservice
					if (thePublishedApi != null ) {
						BindingEnum theBinding = null;
						
						theStatus.setMessage(Constants.LINKING_RESULT_MSG);
						theStatus.setMainCode(Constants.SUCESS_CODE); 
						theStatus.setSecondaryCode(Constants.LINKING_RESULT_CODE); 
						moduleTrigger.setStatus (theStatus);		
						
						ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
						theAccess.setAddress(thePublishedApi.getApiEndpoint()); // "theUrl"
						theBinding = BindingEnum.POST_REDIRECT;
						theAccess.setBinding(theBinding); // thePublishedApi.getApiConnectionType()
						
						theAccess.setContentType("TO ASK: contentType");
						theAccess.setBodyContent("TO ASK: bodyContent");
						moduleTrigger.setAccess (theAccess);
					}
					else {
						theStatus.setMessage(Constants.INVALID_REQUEST_ID_MSG); 
						theStatus.setMainCode(Constants.FAIL_CODE); 
						theStatus.setSecondaryCode(Constants.INVALID_REQUEST_ID_CODE);
						moduleTrigger.setStatus (theStatus);
						moduleTrigger.setAccess (null);
					}
					moduleTrigger.setPayload(thePayload);				
				}
				else {
					theStatus.setMessage(Constants.NO_LINKING_RESULT_MSG);
					theStatus.setMainCode(Constants.FAIL_CODE); 
					theStatus.setSecondaryCode(Constants.NO_LINKING_RESULT_CODE);
					moduleTrigger.setStatus (theStatus);
					moduleTrigger.setAccess (null);
					moduleTrigger.setPayload (null);
				}		
			}
			
			return moduleTrigger;
			
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}
	

}

