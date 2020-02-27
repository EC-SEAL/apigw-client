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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.sm_api.SessionManagerConnService;

@Service
public class ClModuleIDRetrieveGetServiceImp implements ClModuleIDRetrieveGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClModuleIDRetrieveGetServiceImp.class);
	
	@Autowired
	private SessionManagerConnService smConn;

	
	@Override
	public ModuleTrigger clModuleIDRetrieveGet (String sessionID, String moduleID) throws Exception {
	// TODO		
		try {
			
			// Generate token: issuer CL (got from the msMetadataList ConfMngr); receiver Uport (got from the ACCESS metadata)
			
			// Update sessionData: which variable?? ssiWallet = uPort
			
			// Returns msToken and moduleTrigger to client
			// with details to connect to the VC module to do DID Auth.
			
			return null;
			
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}
	

}

