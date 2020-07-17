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

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.cm_api.ConfMngrConnService;
import eu.seal.apigw.cl.domain.AttributeSet;
import eu.seal.apigw.cl.domain.DataSet;
import eu.seal.apigw.cl.domain.DataStore;
import eu.seal.apigw.cl.sm_api.SessionManagerConnService;

@Service
public class ClCallbackGetServiceImp implements ClCallbackGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClCallbackGetServiceImp.class);
	
	@Autowired
	private SessionManagerConnService smConn;
	
	@Autowired
	private ConfMngrConnService confMngrConnService;
	
	@Override
	public void clCallbackGet (String sessionID, String clientCallbackAddr) throws Exception {
			
		try {
			
			// TODO: validate the clientCallbackAddr
			// Try to build an URL object: parsing the string to get the server, port, protocol, path...
			// If exception, returns error
			// If not, it is a valid clientCallbackAddr
			
			
			// Get sessionData from SM
			Object objDatastore = smConn.readDS(sessionID, "dataStore");
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
				
				/* Old SM
				DataStore datastore = new DataStore(); // This is the very first data stored in the Session.
				
				// TODO
				datastore.setId(UUID.randomUUID().toString());
				datastore.setEncryptedData(null);
				datastore.setEncryptionAlgorithm("this is the encryption algorithm");
				datastore.setSignature("this is the signature");
				datastore.setSignatureAlgorithm("this is the signature algorithm");	
				
				datastore.setClearData(null);
				
				// Saving the datastore object in the session
				
				
				try
				{
					ObjectMapper objDatastore0 = new ObjectMapper();
					smConn.updateVariable(sessionID, "dataStore", objDatastore0.writeValueAsString(datastore));
					
				}
				catch (Exception ex)
				{
					String errorMsg= "Exception calling SM (updateVariables)  \n";
					errorMsg += "Exception message:" + ex.getMessage() + "\n";
					log.info(errorMsg);
					throw new Exception (ex);
				}
				*/
			}
			smConn.updateVariable(sessionID, "ClientCallbackAddr", clientCallbackAddr);
			
		}
		catch (Exception e) {
			log.info("Exception: ", e);
			throw new Exception (e);
		}
	}
	

}

