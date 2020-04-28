/**
Copyright © 2019  Atos Spain SA. All rights reserved.
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
package eu.seal.apigw.cl.rest_api.services.session;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.sm_api.SessionManagerConnService;
import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.DataStore;
import eu.seal.apigw.cl.domain.DisplayableList;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;

@Service
public class ClSessionStartGetServiceImp implements ClSessionStartGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClSessionStartGetServiceImp.class);

	@Autowired
	private SessionManagerConnService smConn;
	
	@Override
	public ModuleTrigger clSessionStartGet (String sessionID) throws Exception {
		
		try {
			DataStore datastore = new DataStore(); // This is the very first data stored in the Session.
			ModuleTrigger moduleTrigger = new ModuleTrigger();
			
			String theSessionID = "";
			String statusMessage = "";
			String mainCode = "";
			String secondaryCode = "";
			
			if (sessionID != null) {
				// To retake a session in case it already exists
				theSessionID = sessionID;
				
				// Checking whether this sessionID exists.
				Object objDatastore = smConn.readVariable(theSessionID, "dataStore");
				if (objDatastore != null) {
				
					log.info("Existing Datastore: " + objDatastore.toString());
					mainCode = Constants.SUCESS_CODE;
					statusMessage = Constants.RETAKE_SESSION_MSG;				
					secondaryCode = Constants.RETAKE_SESSION_CODE;
				}
				else { // Not a valid sessionID
					
					log.info("Invalid sessionID: " + theSessionID);
					mainCode = Constants.FAIL_CODE;
					statusMessage = Constants.INVALID_SESSION_MSG;				
					secondaryCode = Constants.INVALID_SESSION_CODE;
				}
			}
			else { 
			// Start Session: POST /sm/startSession
				theSessionID = smConn.startSession();
				
				if (theSessionID != null) {
			
					// Creating an empty datastore object
					
					// TODO
					datastore.setId("DS_" + UUID.randomUUID().toString());
					datastore.setEncryptedData(null);
					datastore.setEncryptionAlgorithm("this is the encryption algorithm");
					datastore.setSignature("this is the signature");
					datastore.setSignatureAlgorithm("this is the signature algorithm");	
					
					datastore.setClearData(null);
					
					// Saving the datastore object in the session
					ObjectMapper objDatastore = new ObjectMapper();
					smConn.updateVariable(theSessionID,"dataStore",objDatastore.writeValueAsString(datastore));
					
					// Creating an empty apigwLinkRequestList in the session
					DisplayableList apigwLinkRequestList = new DisplayableList();
					ObjectMapper objApigwLinkRequestList = new ObjectMapper();
					smConn.updateVariable(theSessionID,"apigwLinkRequestList",objApigwLinkRequestList.writeValueAsString(apigwLinkRequestList));
					
					mainCode = Constants.SUCESS_CODE;
					statusMessage = Constants.NEW_SESSION_MSG;				
					secondaryCode = Constants.NEW_SESSION_CODE;
				}
			}
			
			
			// Building the return moduleTrigger: OK, the sessionID. 
			
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			theStatus.setMessage(statusMessage);
			theStatus.setMainCode(mainCode); 
			theStatus.setSecondaryCode(secondaryCode); 
			moduleTrigger.setStatus (theStatus);
			
			// It doesn't make sense to fill any Access
//			ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
//			theAccess.setAddress("theUrl");
//			theAccess.setBinding(BindingEnum.POST); // Any
//			theAccess.setBodyContent("bodyContent");
//			theAccess.setContentType("contentType");
//			moduleTrigger.setAccess (theAccess);
			
			moduleTrigger.setAccess (null);
			moduleTrigger.setPayload(theSessionID); // The object to be returned.
		
			return (moduleTrigger);
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}

}

