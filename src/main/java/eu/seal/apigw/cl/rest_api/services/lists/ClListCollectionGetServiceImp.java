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
package eu.seal.apigw.cl.rest_api.services.lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.seal.apigw.cl.cm_api.ConfMngrConnService;
import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.DisplayableList;
import eu.seal.apigw.cl.domain.EntityMetadata;
import eu.seal.apigw.cl.domain.EntityMetadataList;

@Service
public class ClListCollectionGetServiceImp implements ClListCollectionGetService{

	@Autowired
	private ConfMngrConnService confMngrConnService;
	
	private static final Logger log = LoggerFactory.getLogger(ClListCollectionGetServiceImp.class);

	
	@Override
	public DisplayableList clListCollectionGet (String collection) throws Exception {
		// TO BE AWARE of the fileName expected!!
		try {
			
			// Depending on the kind of the collection, 
			// the class of the list to be returned will be one or another.
			// TODO: to define those classes inheriting from the DisplayableList class. Are you sure, PACO??
			
			DisplayableList displayableList = null;
			
			//MsMetadataList myXXX = confMngrConnService.getMicroservicesByApiClass(collection);
			EntityMetadataList myList = confMngrConnService.getEntityMetadataSet(collection);
			if (myList != null) {
				displayableList = new DisplayableList();
			
				for (EntityMetadata em: myList) {
					// Add the necessary fields to the node to be added to the displayable list.
					//TODO: which fields for authenticationIDPs
					// moduleIDP, method, url
					
					displayableList.add(em.getEntityId());
					//em.getEndpoints();
				}
			}
			else {
				log.error("Exception: ", Constants.COLLECTION_NOT_FOUND);
				throw new Exception (Constants.COLLECTION_NOT_FOUND);
			}
			
		
			return (displayableList);
		}
		catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception (e);
		}
	}

}

