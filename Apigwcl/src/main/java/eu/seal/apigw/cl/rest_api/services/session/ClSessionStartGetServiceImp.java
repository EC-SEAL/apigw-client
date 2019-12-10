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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess.BindingEnum;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;

@Service
public class ClSessionStartGetServiceImp implements ClSessionStartGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClSessionStartGetServiceImp.class);

	
	@Override
	public ModuleTrigger clSessionStartGet (String sessionID) throws Exception {
		
		try {
			ModuleTrigger moduleTrigger = new ModuleTrigger();
			
			// TODO
			// TO CONFIRM: sessionID is not required.			
			String theSessionID = "";
			if (sessionID != null) {
				// WHAT TO DO??
				theSessionID = sessionID;
			}
			else { //TODO	
			// Start session invoking SM
				;
				
			}
			
			// Creating the datastore object
			//TODO
			
			// Saving the datastore object in the session
			//TODO
			
			// Building the return moduleTrigger: OK, the sessionID. 
			
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
			//TODO
			theStatus.setMessage(theSessionID); // TO ASK: Here in theStatus or in theAccess?? I'd say HERE...
			theStatus.setMainCode("mainCode?"); //OK or KO. TO ASK: perhaps it is saying a sessionID is being returned...
			theStatus.setSecondaryCode("secondaryCode?"); // The exception if KO?
			moduleTrigger.setStatus (theStatus);
			
			// TO ASK:
			// It doesn't make sense to fill anything
			theAccess.setAddress("theUrl");
			theAccess.setBinding(BindingEnum.POST); // Any
			theAccess.setBodyContent("bodyContent");
			theAccess.setContentType("contentType");
			moduleTrigger.setAccess (theAccess);
			
			moduleTrigger.setPayload(null); // TO ASK
		
			return (moduleTrigger);
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}

}

