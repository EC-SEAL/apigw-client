/**
Copyright © 2019  Atos Spain SA. All rights reserved.
This file is part of ESMO GW2GW.
ESMO GW2GW is free software: you can redistribute it and/or modify it under the terms of EUPL 1.2.
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
import java.util.HashMap;


public interface SessionManagerConnService
{
	public String startSession() throws UnrecoverableKeyException, KeyStoreException, 
										FileNotFoundException, NoSuchAlgorithmException, 
										CertificateException, InvalidKeySpecException, IOException;
	
	public String generateToken(String sessionId) throws UnrecoverableKeyException, KeyStoreException, 
	 													 FileNotFoundException, NoSuchAlgorithmException, 
	 													 CertificateException, InvalidKeySpecException, IOException ;
	
	public String validateToken(String token) throws UnrecoverableKeyException, KeyStoreException, 
													 FileNotFoundException, NoSuchAlgorithmException, 
													 CertificateException, InvalidKeySpecException, IOException ;
	
	public Object readVariable( String sessionId, String variableName) throws UnrecoverableKeyException, KeyStoreException, 
																			  FileNotFoundException, NoSuchAlgorithmException, 
																			  CertificateException, InvalidKeySpecException, IOException ;
	

	public void deleteSession(String sessionId) throws UnrecoverableKeyException, KeyStoreException, 
													   FileNotFoundException, NoSuchAlgorithmException, 
													   CertificateException, InvalidKeySpecException, IOException;  
	
	public String getSession(String varName, String varValue) throws UnrecoverableKeyException, KeyStoreException, FileNotFoundException, NoSuchAlgorithmException, CertificateException, InvalidKeySpecException, IOException;

	public HashMap<String, Object> readVariables(String sessionId) throws UnrecoverableKeyException, KeyStoreException,
																		  FileNotFoundException, NoSuchAlgorithmException, 
																		  CertificateException, InvalidKeySpecException, IOException;

	public void updateVariable(String sessionId, String varName, String varValue)throws UnrecoverableKeyException, KeyStoreException,
	  																  FileNotFoundException, NoSuchAlgorithmException, 
	  																  CertificateException, InvalidKeySpecException, IOException;

	
}
