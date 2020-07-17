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
package eu.seal.apigw.cl.rest_api.services.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.seal.apigw.cl.sm_api.SessionManagerConnService;

@Service
public class ClCallbackGetServiceImp implements ClCallbackGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClCallbackGetServiceImp.class);
	
	@Autowired
	private SessionManagerConnService smConn;
	
	@Override
	public void clCallbackGet (String sessionID, String clientCallbackAddr) throws Exception {
			
		try {
			
			Object objDatastore = smConn.readDS(sessionID);
			if (objDatastore != null) 
				smConn.updateVariable(sessionID, "ClientCallbackAddr", clientCallbackAddr);
			
			else {
			// UC1.04
				
				// Initialise dataStore on empty SSI access
				smConn.startSessionDS(sessionID);
			}
			
/*						
			// Get sessionData from SM
			Object objDatastore = smConn.readDS(sessionID);
			if (objDatastore != null) {
// NOT SURE OF THIS NOW (2020.02.26)
				
				// Store the retrieved eIDAS/eduGain/eMRTD response attributes in a new dataset in the user´s dataStore in the SM,
				// i.e. retrieve datastore from SM and ...
				
				DataStore datastore = (new ObjectMapper()).readValue(objDatastore.toString(),DataStore.class);
				
				log.info("Reading dataStore...");
				log.info("## dataStore: " + datastore.toString() );
				
				DataSet myDataset = datastore.getClearData() != null ? datastore.getClearData().get(0) : null;
				
				// ... update SM with the new retrieved eIDAS/eduGain/eMRTD identity attributes
				
				
				if (myDataset != null) {
					// Get from the datastore read before.
					
					ObjectMapper objMapper = new ObjectMapper();
					AttributeSet authenticationSet = new AttributeSet ();
					authenticationSet.setId(UUID.randomUUID().toString());
					//authenticationSet.setType(AttributeSet.TypeEnum(myDataset.getType()));
					authenticationSet.setType(AttributeSet.TypeEnum.AUTHRESPONSE);
					authenticationSet.setIssuer(myDataset.getIssuerId());
					authenticationSet.setRecipient(confMngrConnService.getMicroservicesByApiClass("CL").get(0).getMsId()); // The unique client
					authenticationSet.setLoa(myDataset.getLoa());
					authenticationSet.setNotAfter(myDataset.getExpiration());
					authenticationSet.setNotBefore(null);			
					authenticationSet.setAttributes(myDataset.getAttributes());
					
					smConn.updateVariable(sessionID, "authenticationSet", objMapper.writeValueAsString(authenticationSet));
				}
				else {
					smConn.updateVariable(sessionID, "authenticationSet", null);
				}
				
			}
			else {
			// UC1.04
				
				// Initialise dataStore on empty SSI access
				smConn.startSessionDS(sessionID);
			}
		*/
						
		}
		catch (Exception e) {
			log.info("Exception: ", e);
			throw new Exception (e);
		}
	}
	

}

