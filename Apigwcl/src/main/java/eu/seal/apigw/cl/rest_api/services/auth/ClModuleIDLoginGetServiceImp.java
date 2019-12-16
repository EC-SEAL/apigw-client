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
package eu.seal.apigw.cl.rest_api.services.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.AttributeSet;
import eu.seal.apigw.cl.domain.EntityMetadata;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess.BindingEnum;
import eu.seal.apigw.cl.sm_api.SessionManagerConnService;

@Service
public class ClModuleIDLoginGetServiceImp implements ClModuleIDLoginGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClModuleIDLoginGetServiceImp.class);

	@Autowired
	private SessionManagerConnService smConn;
	
	private AttributeSet idpRequest;
	private EntityMetadata idpMetadata;
	
	
	@Override
	public ModuleTrigger clModuleIDLoginGet (String sessionID, String moduleID) throws Exception {
		
		String msToken = null;
		
		try {
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			// Validating session
			// Checking whether this sessionID exists.
			Object objDatastore = smConn.readVariable(sessionID, "datastore");
			if (objDatastore != null) {
			
				log.info("Existing Datastore: " + objDatastore.toString());
				
				// Selecting the ID source data from the ConfManager
				//TODO
				
				if (true) {
				
					// idpMetadata is creating with the eIDAS/eduGain info from the ConfMngr. Saving in the session.
					idpMetadata = new EntityMetadata();
					// TODO
					//...
					
					ObjectMapper objIdpMetadata = new ObjectMapper();
					smConn.updateVariable(sessionID,"idpMetadata",objIdpMetadata.writeValueAsString(idpMetadata));
					
					// idpRequest is creating. Saving in the session too.
					idpRequest = new AttributeSet();
					//TODO
					//...
					
					ObjectMapper objIdpRequest = new ObjectMapper();
					smConn.updateVariable(sessionID,"idpRequest",objIdpRequest.writeValueAsString(idpRequest));
					
					// Building the moduleTrigger to be returned: access: binding, the url of the IdPms, and the msToken. 
					
					theStatus.setMessage(Constants.AVAILABLE_IDPS_MSG); 
					theStatus.setMainCode(Constants.SUCESS_CODE); 
					theStatus.setSecondaryCode(Constants.AVAILABLE_IDPS_CODE);
									
					ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
					theAccess.setAddress("theUrl"); //TODO: setTarget, instead.
					theAccess.setBinding(BindingEnum.POST); // The related one
					theAccess.setBodyContent(null); // If the access method requires to transfer data on the body of the request, it will be written here
					theAccess.setContentType(null); // MIME type of the body, if any
					moduleTrigger.setAccess (theAccess);
					
					
					// Generate token for returning the session.
					msToken = smConn.generateToken(sessionID); // Create msToken: GET /sm/generateToken
					moduleTrigger.setPayload(msToken);
				}
				else {
				// Not a valid moduleID
					
					theStatus.setMessage(Constants.NO_IDPS_MSG); 
					theStatus.setMainCode(Constants.FAIL_CODE); 
					theStatus.setSecondaryCode(Constants.NO_IDPS_CODE);
					moduleTrigger.setAccess (null);
					moduleTrigger.setPayload (null);
				}
					
		
			}
			else {
				
			// Not a valid sessionID
				
				log.info("Invalid sessionID: " + sessionID);
				theStatus.setMessage(Constants.INVALID_SESSION_MSG); 
				theStatus.setMainCode(Constants.FAIL_CODE); 
				theStatus.setSecondaryCode(Constants.INVALID_SESSION_CODE);
				
				moduleTrigger.setAccess (null);
				moduleTrigger.setPayload (null);
			}
				
			
			moduleTrigger.setStatus (theStatus);
			
			return (moduleTrigger);
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}

}

