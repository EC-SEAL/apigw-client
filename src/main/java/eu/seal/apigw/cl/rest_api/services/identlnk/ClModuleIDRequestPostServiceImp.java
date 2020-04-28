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

import org.apache.commons.httpclient.NameValuePair;
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
public class ClModuleIDRequestPostServiceImp implements ClModuleIDRequestPostService{

	
	private static final Logger log = LoggerFactory.getLogger(ClModuleIDRequestPostServiceImp.class);
	
	@Autowired
	private ConfMngrConnService confMngrConnService;
	
	@Autowired
	private SessionManagerConnService smConn;
	
	@Override
	public ModuleTrigger clModuleIDRequestPost (String sessionID, String moduleID, String datasetIDa, String datasetIDb) throws Exception {
		
		log.info("moduleID: " + moduleID);
		String theModuleID = null;
		MsMetadata theMs = null;
		
		// UC7.01, 7.02: "linking", "linkRequest", "apigwLinkRequestList" session variables to be updated. 
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
						String msToken =  null;
						
						msToken = smConn.generateToken (sessionID, theModuleID);
						thePayload = msToken;
						
						log.info ("token generated");
						
						theBinding = BindingEnum.POST;
						
						// Update sessionData: linking and linkRequest
						smConn.updateVariable(sessionID,"linking", moduleID);
						
						LinkRequest myLinkRequest = new LinkRequest ();
						myLinkRequest.setId("LINK_" + UUID.randomUUID().toString());
						DataSet datasetA = new DataSet();
						datasetA.setId(datasetIDa);
						datasetA.setAttributes(null);
						datasetA.setCategories(null);
						datasetA.setExpiration(null);
						datasetA.setIssued(null);
						datasetA.setIssuerId(null);
						datasetA.setLoa(null);
						datasetA.setProperties(null);
						datasetA.setSubjectId(null);
						datasetA.setType(null);
						
						myLinkRequest.setDatasetA(datasetA);
						
						DataSet datasetB = new DataSet();
						datasetB.setId(datasetIDb);
						datasetB.setAttributes(null);
						datasetB.setCategories(null);
						datasetB.setExpiration(null);
						datasetB.setIssued(null);
						datasetB.setIssuerId(null);
						datasetB.setLoa(null);
						datasetB.setProperties(null);
						datasetB.setSubjectId(null);
						datasetB.setType(null);
											
						myLinkRequest.setDatasetB(datasetB);
						
						myLinkRequest.setConversation(null);
						myLinkRequest.setEvidence(null);
						myLinkRequest.setExpiration(null);
						myLinkRequest.setIssued(null);
						myLinkRequest.setIssuer(null);
						myLinkRequest.setLloa(null);
						myLinkRequest.setType(null);
						
						ObjectMapper objMapper = new ObjectMapper();						
						smConn.updateVariable(sessionID,"linkRequest", objMapper.writeValueAsString(myLinkRequest));
				
				Object objApigwLinkRequestList = smConn.readVariable(sessionID, "apigwLinkRequestList");				
				DisplayableList myApigwLinkRequestList = (new ObjectMapper()).readValue(objApigwLinkRequestList.toString(),DisplayableList.class);
				NameValuePair myPair = new NameValuePair(myLinkRequest.getId(), theMs.getMsId());				
				myApigwLinkRequestList.add(myPair);
				
				log.info("apigwLinkRequestList: " + myApigwLinkRequestList.toString());
				
				ObjectMapper objMapper2 = new ObjectMapper();						
				smConn.updateVariable(sessionID,"apigwLinkRequestList", objMapper2.writeValueAsString(myApigwLinkRequestList));
				
				// Returns moduleTrigger to client
				// it returns the address of the API to call .... /link/request/submit
				if (thePublishedApi != null ) {
					
					theStatus.setMessage(Constants.LINKING_REQUESTED_MSG);
					theStatus.setMainCode(Constants.SUCESS_CODE); 
					theStatus.setSecondaryCode(Constants.LINKING_REQUESTED_CODE); 
					moduleTrigger.setStatus (theStatus);		
					
					ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
					theAccess.setAddress(thePublishedApi.getApiEndpoint()); // "theUrl"
					theAccess.setBinding(theBinding); // thePublishedApi.getApiConnectionType()
					
					Object objLinkRequest = smConn.readVariable(sessionID, "linkRequest");
					theAccess.setBodyContent(objLinkRequest.toString());  // Set above.
					log.info("linkRequest: " + objLinkRequest.toString());
					
					
					theAccess.setContentType("TO ASK: contentType");
					moduleTrigger.setAccess (theAccess);
					
					moduleTrigger.setAccess (theAccess);
				}
				else {
					theStatus.setMessage(Constants.NO_LINKING_REQUESTED_MSG);
					theStatus.setMainCode(Constants.FAIL_CODE); 
					theStatus.setSecondaryCode(Constants.NO_LINKING_REQUESTED_CODE); 
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

