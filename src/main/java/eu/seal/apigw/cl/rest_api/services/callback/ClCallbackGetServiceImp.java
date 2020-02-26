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
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.domain.AttributeSet;
import eu.seal.apigw.cl.domain.AttributeType;
import eu.seal.apigw.cl.domain.DataStore;
import eu.seal.apigw.cl.sm_api.SessionManagerConnService;

@Service
public class ClCallbackGetServiceImp implements ClCallbackGetService{

	
	private static final Logger log = LoggerFactory.getLogger(ClCallbackGetServiceImp.class);
	
	@Autowired
	private SessionManagerConnService smConn;

	
	@Override
	public void clCallbackGet (String sessionID, String clientCallbackAddr) throws Exception {
			
		try {
			
			// Get sessionData from SM
			Object objDatastore = smConn.readVariable(sessionID, "dataStore");
			if (objDatastore != null) {
// NOT SURE OF THIS NOW (2020.02.26)
				
				// Store the retrieved eIDAS/eduGain/eMRTD response attributes in a new dataset in the user´s dataStore in the SM,
				// i.e. retrieve datastore from SM and ...
				
				DataStore datastore = (new ObjectMapper()).readValue(objDatastore.toString(),DataStore.class);
				
				log.info("Reading dataStore...");
				log.info("## dataStore: " + datastore.toString() );
				
				// TODO
				// ... update SM with the new retrieved eIDAS/eduGain/eMRTD identity attributes
				ObjectMapper objMapper = new ObjectMapper();
				AttributeSet authenticationSet = new AttributeSet ();
				authenticationSet.setId(UUID.randomUUID().toString());
				authenticationSet.setType(AttributeSet.TypeEnum.AUTHRESPONSE);
				
				// TODO: to get from the datastore read before.
				authenticationSet.setIssuer( "the issuer");
				authenticationSet.setRecipient("the recipient");
				authenticationSet.setLoa("http://eidas.europa.eu/LoA/substantial");
				authenticationSet.setNotAfter("2020-12-06T19:45:16Z");
				authenticationSet.setNotBefore("2020-12-06T19:40:16Z");
				
				List<AttributeType> myAuthAttributes =  new ArrayList<AttributeType>();
				AttributeType anAuthAttribute = new AttributeType ();
				anAuthAttribute.setEncoding("plain");
				anAuthAttribute.setFriendlyName("CurrentGivenName");
				anAuthAttribute.setName("http://eidas.europa.eu/attributes/naturalperson/CurrentGivenName");
				anAuthAttribute.setLanguage(null);
				anAuthAttribute.setMandatory(true);
				anAuthAttribute.addValuesItem("JOHN JOHN");
				myAuthAttributes.add(0, anAuthAttribute);
//				
//				AttributeType anAuthAttribute2 = new AttributeType ();
//				anAuthAttribute2.setEncoding("plain");
//				anAuthAttribute2.setFriendlyName("PersonIdentifier");
//				anAuthAttribute2.setName("http://eidas.europa.eu/attributes/naturalperson/PersonIdentifier");
//				anAuthAttribute2.setLanguage(null);
//				anAuthAttribute2.setIsMandatory(true);
//				anAuthAttribute2.addValuesItem("ES/NO/1234ABCD");
//				myAuthAttributes.add(1, anAuthAttribute2);
//				
				authenticationSet.setAttributes(myAuthAttributes);
				
				try
				{
					smConn.updateVariable(sessionID, "authenticationSet", objMapper.writeValueAsString(authenticationSet));
				}
				catch (Exception ex)
				{
					String errorMsg= "Exception calling SM (updateVariable authenticationSet)  \n";
					errorMsg += "Exception message:" + ex.getMessage() + "\n";
					log.error(errorMsg);
				}
				
			
			}
			else {
			// UC1.04
				
				// Initialise dataStore on empty SSI access
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
					smConn.updateVariable(sessionID, "clientCallbackAddr", clientCallbackAddr);
				}
				catch (Exception ex)
				{
					String errorMsg= "Exception calling SM (updateVariables)  \n";
					errorMsg += "Exception message:" + ex.getMessage() + "\n";
					log.error(errorMsg);
				}
				
			}
			
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}
	

}

