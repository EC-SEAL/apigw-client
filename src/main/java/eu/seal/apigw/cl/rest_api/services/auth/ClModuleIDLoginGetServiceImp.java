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

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.cm_api.ConfMngrConnService;
import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.AttributeSet;
import eu.seal.apigw.cl.domain.AttributeType;
import eu.seal.apigw.cl.domain.AttributeTypeList;
import eu.seal.apigw.cl.domain.EntityMetadata;
import eu.seal.apigw.cl.domain.EntityMetadataList;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.domain.ModuleTriggerAccess;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;
import eu.seal.apigw.cl.domain.MsMetadata;
import eu.seal.apigw.cl.domain.MsMetadataList;
import eu.seal.apigw.cl.domain.PublishedApiType;
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
		
		
		
		// UC1.01: DEPRECATED
		
		//moduleID is eIDAS or eduGAIN, from AUTHSOURCE
		try {
			
			ModuleTrigger moduleTrigger = new ModuleTrigger();
			ModuleTriggerStatus theStatus = new ModuleTriggerStatus();
			
			// Validating session
			// Checking whether this sessionID exists.
			
			Object objDatastore = smConn.readVariable(sessionID, "datastore");
			if (objDatastore != null) {
				MsMetadata theAuthMs = null;
			
				//***log.info("Existing Datastore: " + objDatastore.toString());				
				
				// Selecting the ID source data from the ConfManager
				EntityMetadata authMetadata0 = confMngrConnService.getEntityMetadata("AUTHSOURCE", moduleID); // Reading the AUTHSOURCEmetadata.json
				 
				PublishedApiType thePublishedApi = null;
				boolean found = false;
				if (authMetadata0 != null) {
					found = true;
					//Select the ms (it could be several) for the moduleID. Choose one randomly.
					String authMsName = authMetadata0.getMicroservice().get(0); //TODO: randomly
					
					// Note the upper-case name of the metadata.json
					EntityMetadataList authMetadataList0 = confMngrConnService.getEntityMetadataSet (moduleID.toUpperCase()); // Reading the EDUGAINmetadata.json or eIDASmetadata.json
					if ((authMetadataList0 != null) && !authMetadataList0.isEmpty()) {
						
						EntityMetadataList authMetadataList = authMetadataList0.getMsEntities(authMsName);
						
						if ((authMetadataList != null) && !authMetadataList.isEmpty()) {
							idpMetadata = authMetadataList.get(0); //TODO: randomly
							
							List<String> theClaims = idpMetadata.getClaims();
							AttributeTypeList attributes = new AttributeTypeList();
							AttributeTypeList attTypeList = null;
							// For fulfilling the claims
							// Select the friendly names from the XXXmetadata.json: theClaims
							// Complete the attribute from the attribute profiles.
							switch (moduleID) {
							
								case "eIDAS":
									// read the friendly names from the EIDASmetadata.json
									attTypeList = confMngrConnService.getAttributeSetByProfile("eIDAS");
									for ( String aClaim : theClaims)
									{
										
										Optional<AttributeType> foundAtt=null;
										foundAtt = attTypeList.stream().filter(a ->a.getFriendlyName().equals(aClaim) ).findAny();						
													
										if (foundAtt !=null && foundAtt.isPresent())
										{
											attributes.add( foundAtt.get());
											log.info ("FoundAtt:" + foundAtt.get());
											
										}
										else
										{
											log.info ("### NOT found: " + aClaim);
										}
									}
									break;
								case "eduGAIN":
									// read the friendly names from the EDUGAINmetadata.json
									//Search on schac or eduPerson.)
									attTypeList = confMngrConnService.getAttributeSetByProfile("eduPerson");
									AttributeTypeList attTypeList2 = confMngrConnService.getAttributeSetByProfile("schac");
									for ( String aClaim : theClaims)
									{
										Optional<AttributeType> foundAtt=null;
										foundAtt = attTypeList.stream().filter(a ->a.getFriendlyName().equals(aClaim) ).findAny();
																			
										if (foundAtt !=null && foundAtt.isPresent())
										{
											attributes.add( foundAtt.get());
											log.info ("FoundAtt in EDUPERSON:" + foundAtt.get());										
										}
										else
										{ // Searching in SCHAC
											Optional<AttributeType> foundAtt2=null;
											foundAtt2 = attTypeList2.stream().filter(a ->a.getFriendlyName().equals(aClaim) ).findAny();
																					
											if (foundAtt2 !=null && foundAtt2.isPresent())
											{
												attributes.add( foundAtt2.get());
												log.info ("FoundAtt in SHAC:" + foundAtt2.get());											
											}
											else
											{ // Searching in SCHAC
												log.info ("### NOT found: " + aClaim);
											}
										}
									}
									break;
								default:
									log.info ("****BE AWARE unknown moduleID: " + moduleID);							
								} 
							
							MsMetadataList authMs0 = confMngrConnService.getMicroservicesByApiClass("AS"); // Reading the msMetadataList.json
							// Select the previous one.
							theAuthMs = authMs0.getMs(authMsName);
							if (theAuthMs == null) {
								log.error("Not found in msMetadataList.json: " + authMsName);
								throw new Exception ("ERROR: check the msMetadataList.json");
							}
							//For fulfilling theAccess (see bellow)
							List<PublishedApiType> thePublishedApiList = theAuthMs.getPublishedAPI();
							
							Iterator<PublishedApiType> paIterator = thePublishedApiList.iterator();
							PublishedApiType auxPublishedApi = null;
							while (paIterator.hasNext()) {
								
								auxPublishedApi = paIterator.next();
								  
								if (auxPublishedApi.getApiClass().toString().equals("AS") &&
									auxPublishedApi.getApiCall().equals("authenticate")	) {
									
									thePublishedApi = auxPublishedApi;
									break; 
								}							  	  
							}
							
							// TODO: ASK!!
							idpRequest = new AttributeSet();
							idpRequest.setId( UUID.randomUUID().toString());
							idpRequest.setType(AttributeSet.TypeEnum.REQUEST);
							idpRequest.setInResponseTo("inResponseTo"); //?
							idpRequest.setIssuer( "spRequest.getIssuer()");//?
							idpRequest.setRecipient( idpMetadata.getEntityId());
							//idpRequest.setProperties( "spRequest.getProperties()"); //?
							idpRequest.setLoa( "spRequest.getLoa()"); //?
							idpRequest.setAttributes(attributes);
							//idpRequest.setStatus("status"); //?
							idpRequest.setNotAfter("notAfter"); //?
						
						} else {
							found = false;
							log.info("Not found in " + moduleID.toUpperCase() + "metadata.json: " + authMsName);
						}
					
					} else {
						found = false;
						log.info("Not found the " + moduleID.toUpperCase() + "metadata.json" );
					
					}
				
				}
				else log.info("Not found in AUTHSOURCEmetadata.json: " + moduleID);
				
				
				if (found) {
					// Building the moduleTrigger to be returned: access: binding, the url of the IdPms, and the msToken. 
					
					if (thePublishedApi != null) {
						String msToken = null;
						
						log.info("Saving the session.");
						
						// idpMetadata is creating with the eIDAS/eduGain info from the ConfMngr. Saving in the session.				
						ObjectMapper objIdpMetadata = new ObjectMapper();
						smConn.updateVariable(sessionID,"idpMetadata",objIdpMetadata.writeValueAsString(idpMetadata));
						
						// idpRequest is creating. Saving in the session too.				
						ObjectMapper objIdpRequest = new ObjectMapper();
						smConn.updateVariable(sessionID,"idpRequest",objIdpRequest.writeValueAsString(idpRequest));
						
						// Generate token for returning the session.
						msToken = smConn.generateToken(sessionID, theAuthMs.getMsId()); // Create msToken: GET /sm/generateToken
					
						theStatus.setMessage(Constants.AVAILABLE_IDPS_MSG); 
						theStatus.setMainCode(Constants.SUCESS_CODE); 
						theStatus.setSecondaryCode(Constants.AVAILABLE_IDPS_CODE);
										
						ModuleTriggerAccess theAccess = new ModuleTriggerAccess();
						theAccess.setAddress(thePublishedApi.getApiEndpoint()); 
						// TODO: how to translate ApiConnectionType into BindingEnum
						theAccess.setBinding(BindingEnum.POST); // thePublishedApi.getApiConnectionType()
						theAccess.setBodyContent(null); // If the access method requires to transfer data on the body of the request, it will be written here
						theAccess.setContentType(null); // MIME type of the body, if any
						moduleTrigger.setAccess (theAccess);
						
						moduleTrigger.setPayload(msToken);
						}
					else {
						theStatus.setMessage(Constants.NO_IDPS_MSG); 
						theStatus.setMainCode(Constants.FAIL_CODE); 
						theStatus.setSecondaryCode(Constants.NO_IDPS_CODE);
						moduleTrigger.setAccess (null);
						moduleTrigger.setPayload (null);
					}
				}
				else {
				// Not a valid moduleID
					
					log.info("Invalid moduleID: " + moduleID);
					
					theStatus.setMessage(Constants.INVALID_MODULE_ID_MSG); 
					theStatus.setMainCode(Constants.FAIL_CODE); 
					theStatus.setSecondaryCode(Constants.INVALID_MODULE_ID_CODE);
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

