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

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.cm_api.ConfMngrConnService;
import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.AttributeSet;
import eu.seal.apigw.cl.domain.EntityMetadata;
import eu.seal.apigw.cl.domain.EntityMetadataList;
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
	
	@Autowired
	private ConfMngrConnService confMngrConnService;
	
	private AttributeSet idpRequest;
	private EntityMetadata idpMetadata;
	
	
	@Override
	public ModuleTrigger clModuleIDLoginGet (String sessionID, String moduleID) throws Exception {
		
		String msToken = null;
		
		//moduleID is eIDAS or eduGAIN, from AUTHSOURCE
		try {
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			// Validating session
			// Checking whether this sessionID exists.
			Object objDatastore = smConn.readVariable(sessionID, "datastore");
			if (objDatastore != null) {
			
				log.info("Existing Datastore: " + objDatastore.toString());
				
				//...eduGAINmetadata or eIDASmetadata (selected the ms randomly from the AUTHSOURCE.)
				// in the msList select according the apiclass ('AUTHSOURCE' or 'AS') ;the APICALL es siemrpre authenticate aquí
				// para rellenar el idpRequest
				
				
				// If eduGAIN, depending on the claims of the edugainmetadata.json eduPerson and schac
				
				
				
				// Selecting the ID source data from the ConfManager
				//TODO
				EntityMetadata authMetadata0 = confMngrConnService.getEntityMetadata("AUTHSOURCE", moduleID); // Reading the AUTHSOURCEmetadata.json
				// TODO: if not null 
				//Select the ms (it could be several) for the moduleID. Choose one randomly.
				String authMsName = authMetadata0.getMicroservice().get(0); //TODO: randomly
				
				EntityMetadataList authMetadataList0 = confMngrConnService.getEntityMetadataSet (moduleID); // Reading the EDUGAINmetadata.json or eIDASmetadata.json
				// TODO: if not null
				EntityMetadataList authMetadataList = authMetadataList0.getMsEntities(authMsName);
				// TODO: if not null
				idpMetadata = authMetadataList.get(0); //TODO: randomly
	
				
				
				
				
				confMngrConnService.getAttributeSetByProfile(profileId) //eIDAS or eduGAIN (missing file??!! Search on shac or eduPerson.)
				// For fulfilling the claims
				
				confMngrConnService.getMicroservicesByApiClass("AUTHSOURCE");
				// Select the previous one.
				// Check the apicall is "authenticate"
				
				
				// TOASK
				idpRequest.setId( UUID.randomUUID().toString());
				idpRequest.setType(AttributeSet.TypeEnum.REQUEST);
				idpRequest.setInResponseTo("inResponseTo");
				idpRequest.setIssuer( "spRequest.getIssuer()");
				idpRequest.setRecipient( idpMetadata.getEntityId());
				idpRequest.setProperties( "spRequest.getProperties()");
				idpRequest.setLoa( "spRequest.getLoa()");
				idpRequest.setAttributes(attributes);
				idpRequest.setStatus("status");
				idpRequest.setNotAfter("notAfter");
				
				if (true) {
				
					// idpMetadata is creating with the eIDAS/eduGain info from the ConfMngr. Saving in the session.
					
					ObjectMapper objIdpMetadata = new ObjectMapper();
					smConn.updateVariable(sessionID,"idpMetadata",objIdpMetadata.writeValueAsString(idpMetadata));
					
					// idpRequest is creating. Saving in the session too.
					
					
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

