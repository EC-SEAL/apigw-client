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
			
			
			//****REVISAR CON EL DIBUJO DE ROSS: ACM
			//
			
			// Validating session
			
			// Generate token for the update of the session.
			
			// idpMetadata is created with the eIDAS/eduGain info from the ConfMngr. Saving in the session.
			
			// idpRequest is created. Saving in the session too.
			
			// Building the return moduleTrigger: access type, the endpoint of the IdPms, the token. 
			
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
			//TODO
			theStatus.setMessage(sessionID); // TO ASK: if not, what to set here?
			theStatus.setMainCode("mainCode?"); //OK or KO. TO ASK
			theStatus.setSecondaryCode("secondaryCode?"); // The exception if KO?
			moduleTrigger.setStatus (theStatus);
			
			// TO ASK:
			theAccess.setAddress("theUrl"); //TODO
			theAccess.setBinding(BindingEnum.POST); // TO ASK: from the idpMetadata?
			theAccess.setBodyContent("bodyContent?");
			theAccess.setContentType("contentType?");
			moduleTrigger.setAccess (theAccess);
			
			moduleTrigger.setPayload("theToken?"); // TO ASK
			
			
			
		
			return (moduleTrigger);
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}

}

