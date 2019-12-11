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
import org.springframework.stereotype.Service;

import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess.BindingEnum;

@Service
public class ClModuleIDLoginGetServiceImp implements ClModuleIDLoginGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClModuleIDLoginGetServiceImp.class);

	
	@Override
	public ModuleTrigger clModuleIDLoginGet (String sessionID, String moduleID) throws Exception {
		
		try {
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			// Validating session
			//TODO
			if (true) {
				
				
				
				// Selecting the ID source data from the ConfManager
				//TODO
				
				// idpMetadata is created with the eIDAS/eduGain info from the ConfMngr. Saving in the session.
				//TODO
				// idpRequest is created. Saving in the session too.
				//TODO
				
				// Building the moduleTrigger to be returned: access: binding, the url of the IdPms, and the msToken. 
				
				theStatus.setMessage(Constants.AVAILABLE_IDPS_MSG); 
				theStatus.setMainCode(Constants.SUCESS_CODE); 
				theStatus.setSecondaryCode(Constants.AVAILABLE_IDPS_CODE);
								
				ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
				theAccess.setAddress("theUrl");
				theAccess.setBinding(BindingEnum.POST); // The related one
				theAccess.setBodyContent(null); // If the access method requires to transfer data on the body of the request, it will be written here
				theAccess.setContentType(null); // MIME type of the body, if any
				moduleTrigger.setAccess (theAccess);
				
				
				// Generate token for returning the session.
				//TODO
				moduleTrigger.setPayload("msToken");
		
			}
			else {
				
				if (true) {
					theStatus.setMessage(Constants.INVALID_SESSION_MSG); 
					theStatus.setMainCode(Constants.FAIL_CODE); 
					theStatus.setSecondaryCode(Constants.INVALID_SESSION_CODE);
				}
				else {
					theStatus.setMessage(Constants.NO_IDPS_MSG); 
					theStatus.setMainCode(Constants.FAIL_CODE); 
					theStatus.setSecondaryCode(Constants.NO_IDPS_CODE);
				}
				
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

