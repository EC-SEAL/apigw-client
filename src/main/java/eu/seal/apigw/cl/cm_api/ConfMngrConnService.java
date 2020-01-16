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
package eu.seal.apigw.cl.cm_api;

import java.util.List;

import eu.seal.apigw.cl.domain.AttributeTypeList;
import eu.seal.apigw.cl.domain.EntityMetadata;
import eu.seal.apigw.cl.domain.EntityMetadataList;
import eu.seal.apigw.cl.domain.MsMetadataList;


//TODO
// Which services are going to be used

public interface ConfMngrConnService 
{
	public List<String> getAttributeProfiles ();
	public AttributeTypeList getAttributeSetByProfile(String profileId);
	//getMappingList (String profileId);
	
	public List<String> getExternalEntities (); // returns available **collections**
	public EntityMetadataList getEntityMetadataSet (String collectionId);
	public EntityMetadata getEntityMetadata (String collectionId, String entityId);
	
	public MsMetadataList getAllMicroservices ();
	public MsMetadataList getMicroservicesByApiClass (String apiClasses); // input like "SP, IDP, AP, GW, ACM, SM, CM"
	
	public List<String> getInternalConfs (); // returns available internal configurations
	public EntityMetadata getConfiguration (String confId);
	
	
	
}

