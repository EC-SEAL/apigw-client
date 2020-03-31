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
*/
package eu.seal.apigw.cl.configuration;


public class Constants {
	private Constants() {}
	
	// 404 in misc controllers
	public final static String COLLECTION_NOT_FOUND= "Collection not found"; 	// cl/list
	public final static String ERROR_RETURNING = "Error returning to client"; 	// cl/callback
	
	// 404 in auth, identsource/retrieve, identmngr/list, persistence/load&store controllers, derivation
	public final static String ERROR_ACCESSING_MODULE= "Error accessing module";
	
	// 404 in session controllers
	public final static String SESSION_START_ERROR = "Session start error";
		
	public final static String INVALID_MODULE_ID_MSG = "Invalid module id";
	public final static String INVALID_MODULE_ID_CODE = "invalid_module_id_code";
	
	public final static String NEW_SESSION_MSG = "New session just created";
	public final static String NEW_SESSION_CODE = "new_session_code";
	public final static String RETAKE_SESSION_MSG = "Retaken the session";
	public final static String RETAKE_SESSION_CODE = "retake_session_code";
	public final static String INVALID_SESSION_MSG = "Invalid session";
	public final static String INVALID_SESSION_CODE = "invalid_session_code";
	
	public final static String AVAILABLE_IDPS_MSG = "Available IdPs";
	public final static String AVAILABLE_IDPS_CODE = "available_idps_code";
	public final static String NO_IDPS_MSG = "No IdPs";
	public final static String NO_IDPS_CODE = "no_idps_code";
	
	public final static String ID_RETRIEVED_MSG = "Identity just retrieved";
	public final static String ID_RETRIEVED_CODE = "id_retrieved_code";
	public final static String NO_ID_RETRIEVED = "No identity retrieved";
	public final static String NO_ID_RETRIEVED_CODE = "no_id_retrieved_code";
	
	public final static String ID_DERIVED_MSG = "Identity just derived";
	public final static String ID_DERIVED_CODE = "id_derived_code";
	public final static String NO_ID_DERIVED = "No identity derived";
	public final static String NO_ID_DERIVED_CODE = "no_id_derived_code";
	
	public final static String PERSISTENCE_LOADED_MSG = "Persistence module loaded";
	public final static String PERSISTENCE_LOADED_CODE = "persistence_loaded_code";
	public final static String NO_PERSISTENCE_LOADED_MSG = "No persistence module loaded";
	public final static String NO_PERSISTENCE_LOADED_CODE = "no_persistence_loaded_code";
	
	public final static String PERSISTENCE_STORED_MSG = "Persistence module stored";
	public final static String PERSISTENCE_STORED_CODE = "persistence_stored_code";
	public final static String NO_PERSISTENCE_STORED_MSG = "No persistence module stored";
	public final static String NO_PERSISTENCE_STORED_CODE = "no_persistence_stored_code";
	
	public final static String SUCESS_CODE = "OK";
	public final static String FAIL_CODE = "KO";
	
	
	
	
//	public final static String MS_NOT_FOUND = "Microservices not found";
//	public final static String PROFILES_NOT_FOUND = "Profile files not found.";
//	public final static String ATTRIBUTES_NOT_FOUND = "Attributes not found";
//	public final static String JSON_SYNTAX_ERROR = "Json Syntax error";
//	public final static String FILE_ERROR = "File error";
//	public final static String ENTITY_NOT_FOUND = "External entity not found";
//	public final static String ENTITIES_NOT_FOUND = "External entities not found";
//	public final static String ENTITY_FILES_NOT_FOUND = "External entity files not found";
//	public final static String INTERNAL_CONF_FILES_NOT_FOUND = "Internal configuration files not found";
//	public final static String INTERNAL_CONF_NOT_FOUND = "Internal configuration not found";
//    
//	public final static String EWP_REGISTRY_NOT_AVAILABLE = "EWP Registry not available";
//    public final static String EWP_MANIFEST_NOT_FOUND = "EWP Manifest file not found";
//    public final static String EWP_NO_SEAL_HOSTS = "No SEAL hosts found in the registry";
//    
//    public final static String SEAL_GW_DSAREQUEST = "dsaRequest"; 
//	public final static String SEAL_GW_DSARESPONSE = "dsaResponse";
}
