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
*/
package eu.seal.apigw.cl.sm_api;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.seal.apigw.cl.domain.EntityMetadata;
import eu.seal.apigw.cl.domain.MsMetadataList;
import eu.seal.apigw.cl.domain.SessionMngrResponse;
//import eu.seal.apigw.cl.domain.SessionMngrResponse.CodeEnum;
import eu.seal.apigw.cl.domain.ResponseCode;
import eu.seal.apigw.cl.cm_api.ConfMngrConnService;
import eu.seal.apigw.cl.network_api.NetworkServiceImpl;
import eu.seal.apigw.cl.params_api.KeyStoreService;
import eu.seal.apigw.cl.params_api.ParameterService;
import eu.seal.apigw.cl.domain.RequestParameters;
import eu.seal.apigw.cl.domain.UpdateDataRequest;



@Service
public class SessionManagerConnServiceImp implements SessionManagerConnService
{
	private final String hostURL;
	
	
	//TODO
	private String sender = null;
	
	private static final Logger log = LoggerFactory.getLogger(SessionManagerConnServiceImp.class);
	
	//private HttpSignatureServiceImpl httpSigService = null;
	private NetworkServiceImpl network = null;
	
	private KeyStoreService keyStoreService;
	
	private ConfMngrConnService confMngrService;
	
	private ParameterService paramServ;
	
	@Autowired
    public SessionManagerConnServiceImp (ConfMngrConnService confMngrConnService, KeyStoreService keyStoreServ, ParameterService paramServ) {
		
		this.keyStoreService = keyStoreServ;
		
		this.confMngrService = confMngrConnService;
		
		this.paramServ = paramServ;
		
//		MsMetadataList mySMs = this.confMngrService.getMicroservicesByApiClass("SM");
//		if (mySMs != null) {
//			String smHost = mySMs.get(0).getPublishedAPI().get(0).getApiEndpoint(); // The first one found
//			this.hostURL = smHost.substring(0, smHost.indexOf("/sm/"));
//		}
//		else {
//			hostURL = "http://5.79.83.118:8090";
//			log.info("HARDCODED SessionMngr hostURL! "+ hostURL);
//		}
		
		hostURL = this.paramServ.getParam("SESSION_MANAGER_URL");
		
		// The receiver will be different: Persistence, Identity, IDderivation, ...
		// ******************************
		
     
		//TODO
        //EntityMetadata myLGW = this.confMngrService.getConfiguration("LGW"); // APIGWCL or reading from an environment variable. TOASK
		EntityMetadata myLGW = null;
		String thisCL = confMngrService.getMicroservicesByApiClass("CL").get(0).getMsId(); // The unique client
		if (thisCL != null)
        	sender = thisCL;
        else {
        	sender = "CLms001";
        	log.error("HARDCODED sender! "+ sender);
		}
        
        //TODO: to comment them after testing
        System.out.println("hostURL: " + hostURL);
        System.out.println("sender: "+ sender);
        System.out.println("receiver wont be constant");
        
        
        
	}
	
	@Override
	public String startSession() throws UnrecoverableKeyException, KeyStoreException, 
										FileNotFoundException, NoSuchAlgorithmException, 
										CertificateException, InvalidKeySpecException, IOException 
	{
		String service = "/sm/startSession";
		
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
				//log.info ("startSession network just created");
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		
		SessionMngrResponse smResponse = network.sendPostFormSMResponse(hostURL, service, urlParameters, 1);
		
		return smResponse.getSessionData().getSessionId();
	}
	
	@Override
	public String startSessionDS(String sessionId) throws UnrecoverableKeyException, KeyStoreException, 
										FileNotFoundException, NoSuchAlgorithmException, 
										CertificateException, InvalidKeySpecException, IOException 
	{
		//String service = "/sm/new/startSession/old";
		String service = "/sm/new/startSession";
		
		String contentType="application/json";
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
				//log.info ("startSessionDS network just created");
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new NameValuePair("sessionId",sessionId));
		
		//SessionMngrResponse smResponse = network.sendPostFormSMResponse(hostURL, service, urlParameters, 1);
        String response = network.sendPostBody(hostURL, service, urlParameters, contentType, 1);
		
		log.info("SMresponse(startSessionDS): " + response);
		//log.info("sessionID: "+smResponse.getSessionData().getSessionId());
		
		//return smResponse.getSessionData().getSessionId();
		return response;
	}
	
	@Override
	public String generateToken(String sessionId, String receiver)
			throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException,
			CertificateException, InvalidKeySpecException, IOException
	{
		String service = "/sm/generateToken";
			
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new NameValuePair("sessionId",sessionId));
        urlParameters.add(new NameValuePair("sender", this.sender));   
        urlParameters.add(new NameValuePair("receiver", receiver)); 
        
        urlParameters.add(new NameValuePair("data", "extraData"));
        
        SessionMngrResponse smResponse = network.sendGetSMResponse(hostURL, service, urlParameters, 1);
        
        String additionalData="";
        //System.out.println("SMresponse(generateToken):" +smResponse.toString());
        if ( smResponse.getCode()==ResponseCode.NEW)
        {
	        //System.out.println( "addDAta:"+ smResponse.getAdditionalData());
	        additionalData = smResponse.getAdditionalData();
	        
	        return additionalData; // Returns token
	    }
        else return null;
        
	}
	
	@Override
	public String validateToken(String token) throws UnrecoverableKeyException, KeyStoreException, 
													 FileNotFoundException, NoSuchAlgorithmException, 
													 CertificateException, InvalidKeySpecException, IOException 
	{

		String service = "/sm/validateToken";
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		
		String sessionID = "";
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    urlParameters.add(
	    		new NameValuePair("token",token));
	    
	    SessionMngrResponse smResponse = null;
	   
	    //response = network.sendGet(hostURL, service, urlParameters);
	    smResponse = network.sendGetSMResponse(hostURL, service, urlParameters, 1);
	    
	    if ( smResponse.getCode()==ResponseCode.OK)
	    {
	    	sessionID = smResponse.getSessionData().getSessionId();
	    	//System.out.println("SessionID:"+sessionID);
	    	return sessionID;
	    }
	    else return null;
		
	}
	
	@Override
	public HashMap<String, Object> readVariables(String sessionId) throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException, CertificateException, InvalidKeySpecException, IOException
	{
		String service = "/sm/getSessionData";
		HashMap<String, Object> sessionVbles= new HashMap<String, Object>();
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    urlParameters.add(new NameValuePair("sessionId",sessionId));
	    
	    SessionMngrResponse smResponse = null;
	    try {
	    	//response = network.sendGet(hostURL, service, urlParameters);
	    	smResponse = network.sendGetSMResponse(hostURL, service, urlParameters, 1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //System.out.println("Response getSessionData:<"+smResponse.toString()+">");
	    if (smResponse.getCode()==ResponseCode.OK)
	    {
	    	sessionVbles = (HashMap<String, Object>) smResponse.getSessionData().getSessionVariables();
	    	return sessionVbles;
	    }
	    else return null;
	    
	}

	

	@Override
	public Object readVariable(String sessionId, String variableName) throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException, CertificateException, InvalidKeySpecException, IOException
	{
		String service = "/sm/getSessionData";
		HashMap<String, Object> sessionVbles = new HashMap<String, Object>();
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    urlParameters.add(new NameValuePair("sessionId",sessionId));
	    urlParameters.add(new NameValuePair("variableName",variableName));
	    
	    SessionMngrResponse smResponse = null;
	    try {
	    	//response = network.sendGet(hostURL, service, urlParameters);
	    	smResponse = network.sendGetSMResponse(hostURL, service, urlParameters, 1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    if (smResponse.getCode()==ResponseCode.OK)
	    {
	    	sessionVbles = (HashMap<String, Object>) smResponse.getSessionData().getSessionVariables();
	    	
	    	return sessionVbles.get(variableName);
	    	
	    }
	    else return null;
	    
	}
	
	@Override
	public Object readDS(String sessionId) throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException, CertificateException, InvalidKeySpecException, IOException
	{
		//String service = "/sm/new/get/old";
		String service = "/sm/new/get";
		
		String contentType="application/json";
		
		Object sessionVble = new Object();
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
//		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//	    urlParameters.add(new NameValuePair("sessionId",sessionId));
	    
	    RequestParameters requestParameters = new RequestParameters();
        requestParameters.setSessionId(sessionId);
 	    
	    SessionMngrResponse smResponse = null;
	    
	    try {
	    	log.info("Sending new/get ...");
	    	//smResponse = network.sendGetSMResponse(hostURL, service, urlParameters, 1);
	    	smResponse = network.sendPostBodySMResponse(hostURL, service, requestParameters, contentType, 1);
	    	
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    if (smResponse.getCode()==ResponseCode.OK)
	    {
	    	//sessionVbles = (HashMap<String, Object>) smResponse.getSessionData().getSessionVariables();
	    	sessionVble = smResponse.getAdditionalData();
	    	
	    	log.info("sessionVble: "+ sessionVble.toString());
	    	return sessionVble;
	    }
	    else return null;
	    
	}
	
	@Override
	public Object getDataSet(String sessionId, String dataSetId) throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException, CertificateException, InvalidKeySpecException, IOException
	{
		//String service = "/sm/new/get/old";
		
		String service = "/sm/new/get";
		
		String contentType="application/json";
		
		Object sessionVble = new Object();
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
//		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//	    urlParameters.add(new NameValuePair("sessionId",sessionId));
//	    urlParameters.add(new NameValuePair("id",dataSetId));
	    
	    RequestParameters requestParameters = new RequestParameters();
        requestParameters.setSessionId(sessionId);
        requestParameters.setId(dataSetId);
        
	    
	    SessionMngrResponse smResponse = null;
	    try {
	    	log.info("Sending new/get DataSet ...");
	    	//smResponse = network.sendGetNewSMResponse(hostURL, service, urlParameters, 1);
	    	smResponse = network.sendPostBodySMResponse(hostURL, service, requestParameters, contentType, 1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //log.info("Response new/getSessionData:<"+smResponse.toString()+">");
	    if (smResponse.getCode()==ResponseCode.OK)
	    {
	    	//sessionVbles = (HashMap<String, Object>) smResponse.getSessionData().getSessionVariables();
	    	sessionVble = smResponse.getAdditionalData();
	    	
	    	log.info("sessionVble: "+ sessionVble.toString());
	    	return sessionVble;
	    }
	    else return null;
	    
	}

	
	@Override
	public void updateVariable(String sessionId, String varName, String varValue)
			throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException,
			CertificateException, InvalidKeySpecException, IOException {
		String service = "/sm/updateSessionData";
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new NameValuePair("sessionId",sessionId));
        
        UpdateDataRequest updateDR = new UpdateDataRequest();
        updateDR.setSessionId(sessionId);
        updateDR.setVariableName(varName);
        updateDR.dataObject(varValue);
        String contentType="application/json";
		
        String response = network.sendPostBody(hostURL, service, updateDR, contentType, 1);
		
	}

	@Override
	public void deleteSession(String sessionId) throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException, CertificateException, InvalidKeySpecException, IOException		// /sm/endSession
	{
		String service = "/sm/endSession";
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    urlParameters.add(new NameValuePair("sessionId",sessionId));
	    
	    SessionMngrResponse smResponse = null;
	    try {
	    	//System.out.println("Enviando endSession");
	    	//response = network.sendGet(hostURL, service, urlParameters);
	    	smResponse = network.sendGetSMResponse(hostURL, service, urlParameters, 1);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String getSession(String varName, String varValue)
			throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException,
			CertificateException, InvalidKeySpecException, IOException
	{
		String service = "/sm/getSession";
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		List<NameValuePair> requestParams = new ArrayList();
        requestParams.add(new NameValuePair("varName", varName));
        requestParams.add(new NameValuePair("varValue", varValue));
        
        SessionMngrResponse response= null;
		try {
			response = network.sendGetSMResponse(hostURL, service, requestParams, 1);
			//System.out.println("Response getSession"+response);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (response.getSessionData() != null ? response.getSessionData().getSessionId() : null);
        
		
	}
	
}