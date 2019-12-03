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
package eu.seal.apigw.cl.sm_api;


import java.io.FileNotFoundException;
import java.io.IOException;

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
import eu.seal.apigw.cl.domain.SessionMngrResponse.CodeEnum;
import eu.seal.apigw.cl.cm_api.ConfMngrConnService;
import eu.seal.apigw.cl.network_api.NetworkServiceImpl;
import eu.seal.apigw.cl.params_api.KeyStoreService;
import eu.seal.apigw.cl.params_api.ParameterService;
import eu.seal.apigw.cl.domain.UpdateDataRequest;



@Service
public class SessionManagerConnServiceImp implements SessionManagerConnService
{
	private final String hostURL;
	
	
	//TODO
	private String sender = null;
	private String receiver = null;
	
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
		
		//TODO
		MsMetadataList myACMs = this.confMngrService.getMicroservicesByApiClass("ACM");
		if (myACMs != null)
			this.receiver = myACMs.get(0).getMsId();	// The first one found
		else {
			receiver = "ACMms001";
			log.error("HARDCODED receiver! "+ receiver);
		}
     
		//TODO
        EntityMetadata myLGW = this.confMngrService.getConfiguration("LGW");
        if (myLGW != null)
        	sender = myLGW.getMicroservice().get(0);
        else {
        	sender = "GW2GWms001";
        	log.error("HARDCODED sender! "+ sender);
		}
        
//        System.out.println("hostURL: " + hostURL);
//        System.out.println("sender: "+ sender);
//        System.out.println("receiver: "+ receiver);
        
        
        
	}
	
	@Override
	public String startSession() throws UnrecoverableKeyException, KeyStoreException, 
										FileNotFoundException, NoSuchAlgorithmException, 
										CertificateException, InvalidKeySpecException, IOException 
	{
		String service = "/sm/startSession";
		
//		if (httpSigService== null)
//		{
//			createHttpSigService();
//		}
//		if (network == null)
//		{
//			network = new NetworkServiceImpl(httpSigService);
//		}
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
				//log.info ("startSession network just created");
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		
		SessionMngrResponse smResponse = network.sendPostFormSMResponse(hostURL, service, urlParameters, 1);
		
		//System.out.println("SMresponse(startSession):" +smResponse.toString());
		//System.out.println("sessionID:"+smResponse.getSessionData().getSessionId());
		
		return smResponse.getSessionData().getSessionId();
	}
	
	@Override
	public String generateToken(String sessionId)
			throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException,
			CertificateException, InvalidKeySpecException, IOException
	{
		String service = "/sm/generateToken";
		
//		if (httpSigService== null)
//		{
//			createHttpSigService();
//		}
//		if (network == null)
//		{
//			network = new NetworkServiceImpl(httpSigService);
//		}
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new NameValuePair("sessionId",sessionId));
        urlParameters.add(new NameValuePair("sender", this.sender));   
        urlParameters.add(new NameValuePair("receiver", this.receiver)); 
        
        urlParameters.add(new NameValuePair("data", "extraData"));
        
        SessionMngrResponse smResponse = network.sendGetSMResponse(hostURL, service, urlParameters, 1);
        
        String additionalData="";
        //System.out.println("SMresponse(generateToken):" +smResponse.toString());
        if ( smResponse.getCode()==CodeEnum.NEW)
        {
	        //System.out.println( "addDAta:"+ smResponse.getAdditionalData());
	        additionalData = smResponse.getAdditionalData();
	    }
        return additionalData; //Devuelve un token
	}
	
	@Override
	public String validateToken(String token) throws UnrecoverableKeyException, KeyStoreException, 
													 FileNotFoundException, NoSuchAlgorithmException, 
													 CertificateException, InvalidKeySpecException, IOException 
	{

		String service = "/sm/validateToken";
		
//		if (httpSigService== null)
//		{
//			createHttpSigService();
//		}
//		if (network == null)
//		{
//			network = new NetworkServiceImpl(httpSigService);
//		}
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		
		String sessionID = "";
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    urlParameters.add(
	    		new NameValuePair("token",token));
	    
	    SessionMngrResponse smResponse = null;
	   
	    //System.out.println("Enviando validateToken :"+token);
	    //response = network.sendGet(hostURL, service, urlParameters);
	    smResponse = network.sendGetSMResponse(hostURL, service, urlParameters, 1);
	    
	    if ( smResponse.getCode()==CodeEnum.OK)
	    {
	    	sessionID = smResponse.getSessionData().getSessionId();
	    	//System.out.println("SessionID:"+sessionID);
	    }
		// else   // Si hay error p.ej. JWT is blacklisted �q hacemos?
		//System.out.println("validateToken smResponse:"+smResponse);
		return sessionID; //devuelve un sessionId
	}
	
	@Override
	public HashMap<String, Object> readVariables(String sessionId) throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException, CertificateException, InvalidKeySpecException, IOException
	{
		String service = "/sm/getSessionData";
		HashMap<String, Object> sessionVbles= new HashMap<String, Object>();
//		if (httpSigService== null)
//		{
//			createHttpSigService();
//		}
//		if (network == null)
//		{
//			network = new NetworkServiceImpl(httpSigService);
//		}
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    urlParameters.add(new NameValuePair("sessionId",sessionId));
	    
	    SessionMngrResponse smResponse = null;
	    try {
	    	//System.out.println("Enviando getSessionData");
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
	    if (smResponse.getCode()==CodeEnum.OK)
	    {
	    	sessionVbles = (HashMap<String, Object>) smResponse.getSessionData().getSessionVariables();
	    }
	    
	    return sessionVbles;
	}

	

	@Override
	public Object readVariable(String sessionId, String variableName) throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException, CertificateException, InvalidKeySpecException, IOException
	{
		String service = "/sm/getSessionData";
		HashMap<String, Object> sessionVbles = new HashMap<String, Object>();
//		if (httpSigService== null)
//		{
//			createHttpSigService();
//		}
//		if (network == null)
//		{
//			network = new NetworkServiceImpl(httpSigService);
//		}
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    urlParameters.add(new NameValuePair("sessionId",sessionId));
	    urlParameters.add(new NameValuePair("variableName",variableName));
	    
	    SessionMngrResponse smResponse = null;
	    try {
	    	//System.out.println("Enviando getSessionData");
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
	    if (smResponse.getCode()==CodeEnum.OK)
	    {
	    	sessionVbles = (HashMap<String, Object>) smResponse.getSessionData().getSessionVariables();
	    	
	    	//System.out.println( "sessionVbles:"+sessionVbles.get("spRequest"));
//	    	//AttributeSet spRequest = (AttributeSet) sessionVbles.get("spRequest");
//	    	ObjectMapper objectMapper = new ObjectMapper();
//	    	
//	    	AttributeSet spRequest = objectMapper.readValue(sessionVbles.get("spRequest").toString(), AttributeSet.class);
//	    	System.out.println("spRequest.issuer"+spRequest.getIssuer());
	    }
	    
	    
	    return sessionVbles.get(variableName);
	}

	
	@Override
	public void updateVariable(String sessionId, String varName, String varValue)
			throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException,
			CertificateException, InvalidKeySpecException, IOException {
		String service = "/sm/updateSessionData";
//		if (httpSigService== null)
//		{
//			createHttpSigService();
//		}
//		if (network == null)
//		{
//			network = new NetworkServiceImpl(httpSigService);
//		}
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
		
//		ObjectMapper mapper = new ObjectMapper();
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new NameValuePair("sessionId",sessionId));
        
        UpdateDataRequest updateDR = new UpdateDataRequest();
        updateDR.setSessionId(sessionId);
        updateDR.setVariableName(varName);
        updateDR.dataObject(varValue);
//        String postBody = mapper.writeValueAsString(updateDR);
        String contentType="application/json";
		
        String response = network.sendPostBody(hostURL, service, updateDR, contentType, 1);
		
		
        //System.out.println("Response updateSessionData"+response);
		
	}

	@Override
	public void deleteSession(String sessionId) throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException, CertificateException, InvalidKeySpecException, IOException		// /sm/endSession
	{
		String service = "/sm/endSession";
//		if (httpSigService== null)
//		{
//			createHttpSigService();
//		}
//		if (network == null)
//		{
//			network = new NetworkServiceImpl(httpSigService);
//		}
		
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
	    //System.out.println("Response endSession :<"+smResponse.toString()+">");
		
	}

	@Override
	public String getSession(String varName, String varValue)
			throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException,
			CertificateException, InvalidKeySpecException, IOException
	{
		String service = "/sm/getSession";
//		if (httpSigService== null)
//		{
//			createHttpSigService();
//		}
//		if (network == null)
//		{
//			network = new NetworkServiceImpl(httpSigService);
//		}
		
		if (network == null)
		{
				network = new NetworkServiceImpl(keyStoreService);
		}
//		ObjectMapper mapper = new ObjectMapper();
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