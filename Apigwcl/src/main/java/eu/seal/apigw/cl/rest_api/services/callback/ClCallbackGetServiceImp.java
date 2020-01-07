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
package eu.seal.apigw.cl.rest_api.services.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.domain.AttributeSet.TypeEnum;
import eu.seal.apigw.cl.domain.DataStore;
import eu.seal.apigw.cl.sm_api.SessionManagerConnService;

@Service
public class ClCallbackGetServiceImp implements ClCallbackGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClCallbackGetServiceImp.class);
	
	@Autowired
	private SessionManagerConnService smConn;

	
	@Override
	public void clCallbackGet (String sessionID) throws Exception {
		
		try {
			
			// Get sessionData from SM
			Object objDatastore = smConn.readVariable(sessionID, "datastore");
			if (objDatastore != null) {
				
				DataStore datastore = (new ObjectMapper()).readValue(objDatastore.toString(),DataStore.class);
				log.info("Reading datastore...");
				log.info("## datastore: " + datastore.toString() );
			
			// Show the datastore (eIDAS/eduGain/passport data) in a Web Screen
			//TODO
				
			}
			else {
				log.info("Invalid sessionID: " + sessionID);
				throw new Exception ("Invalid sessionID");
				
			}
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}

}

