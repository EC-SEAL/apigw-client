/**
Copyright © 2019  UAegean. All rights reserved.
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

package eu.seal.apigw.cl.params_api;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.security.Key;
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.UnrecoverableKeyException;
//import java.security.cert.Certificate;
//import java.security.cert.CertificateException;
//import javax.crypto.spec.SecretKeySpec;
//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
////import com.nimbusds.jose.JWSAlgorithm;
//
//import io.jsonwebtoken.SignatureAlgorithm;
////import org.apache.commons.codec.digest.DigestUtils;

import io.jsonwebtoken.SignatureAlgorithm;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
//import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
//import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author UAegean
 */
@Service
public class KeyStoreServiceImpl implements KeyStoreService {

    private final String certPath;
    private final String keyPass;
    private final String storePass;
    //private final String jwtKeyAlias;
    private final String httpSigKeyAlias;
    //private final String jweKeyAlias;
    
    private final String httpSigAttempts;

    private KeyStore keystore;

    private ParameterService paramServ;
    
    private final static Logger LOG = LoggerFactory.getLogger(KeyStoreServiceImpl.class);


    @Autowired
    public KeyStoreServiceImpl(ParameterService paramServ) throws KeyStoreException, FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        this.paramServ = paramServ;
        certPath = this.paramServ.getParam("KEYSTORE_PATH");
        keyPass = this.paramServ.getParam("KEY_PASS");
        storePass = this.paramServ.getParam("STORE_PASS");
        //jwtKeyAlias = this.paramServ.getParam("JWT_CERT_ALIAS");
        httpSigKeyAlias = this.paramServ.getParam("HTTPSIG_CERT_ALIAS");
        //jweKeyAlias = this.paramServ.getParam("JWE_CERT_ALIAS");
        
        httpSigAttempts = this.paramServ.getParam("HTTPSIG_ATTEMPTS") == null ? "10": this.paramServ.getParam("HTTPSIG_ATTEMPTS");
        
        LOG.info ("certPath: " + certPath);
//        LOG.info ("jwtKeyAlias: " + jwtKeyAlias);
        LOG.info ("httpSigKeyAlias: " + httpSigKeyAlias);
//        LOG.info ("jweKeyAlias: " + jweKeyAlias);

        keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        if (!org.springframework.util.StringUtils.isEmpty(paramServ.getParam("ASYNC_SIGNATURE")) && Boolean.parseBoolean(paramServ.getParam("ASYNC_SIGNATURE"))) {
            
        	//LOG.info ("ASYNC_SIGNATURE: true");
        	File jwtCertFile = new File(certPath);
            InputStream certIS = new FileInputStream(jwtCertFile);
            keystore.load(certIS, storePass.toCharArray());
            //LOG.info ("keystore: loaded");
        } else {
            //init an empty keystore otherwise an exception is thrown
            keystore.load(null, null);
        }

    }
    
    public int getNumAttempts() {
    	return Integer.parseInt(httpSigAttempts);
    }
    
    public Key getHttpSigningKey() throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, UnsupportedEncodingException {
        //"httpsigkey"
        //return keystore.getKey(keyAlias, "keypassword".toCharArray());
        String asyncSignature = paramServ.getParam("ASYNC_SIGNATURE");
        if (!org.springframework.util.StringUtils.isEmpty(asyncSignature) && Boolean.valueOf(asyncSignature)) {
        	//LOG.info ("GettingHttpSigningKey....");
        	return keystore.getKey(httpSigKeyAlias, keyPass.toCharArray());
        }
        String secretKey = paramServ.getParam("SIGNING_SECRET");
        return new SecretKeySpec(secretKey.getBytes("UTF-8"), 0, secretKey.length(), "HmacSHA256");
    }
 /*   
    public Key getJWTSigningKey() throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, UnsupportedEncodingException {
        //"jwtKeyAlias"
        //return keystore.getKey(keyAlias, "keypassword".toCharArray());
        String asyncSignature = paramServ.getParam("ASYNC_SIGNATURE");
        if (!org.springframework.util.StringUtils.isEmpty(asyncSignature) && Boolean.valueOf(asyncSignature)) {
        	//LOG.info ("GettingJWTKey....");
        	return keystore.getKey(jwtKeyAlias, keyPass.toCharArray());
        }
        String secretKey = paramServ.getParam("SIGNING_SECRET");
        return new SecretKeySpec(secretKey.getBytes("UTF-8"), 0, secretKey.length(), "HmacSHA256");
    }

    public Key getJWEKey() throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, UnsupportedEncodingException {
        //"jweKeyAlias"
        //return keystore.getKey(keyAlias, "keypassword".toCharArray());
        String asyncSignature = paramServ.getParam("ASYNC_SIGNATURE");
        if (!org.springframework.util.StringUtils.isEmpty(asyncSignature) && Boolean.valueOf(asyncSignature)) {
            return keystore.getKey(jweKeyAlias, keyPass.toCharArray());
        }
        String secretKey = paramServ.getParam("SIGNING_SECRET");
        return new SecretKeySpec(secretKey.getBytes("UTF-8"), 0, secretKey.length(), "HmacSHA256");
    }

    public Key getJWTPublicKey() throws KeyStoreException, UnsupportedEncodingException {
        //"jwtkey"
        String asyncSignature = paramServ.getParam("ASYNC_SIGNATURE");
        if (!org.springframework.util.StringUtils.isEmpty(asyncSignature) && Boolean.valueOf(asyncSignature)) {
            Certificate cert = keystore.getCertificate(jwtKeyAlias);
            return cert.getPublicKey();
        }
        String secretKey = paramServ.getParam("SIGNING_SECRET");
        return new SecretKeySpec(secretKey.getBytes("UTF-8"), 0, secretKey.length(), "HmacSHA256");
    }
*/
    public Key getHttpSigPublicKey() throws KeyStoreException, UnsupportedEncodingException {
        //"httpSignaturesAlias"
        Certificate cert = keystore.getCertificate(httpSigKeyAlias);
        return cert.getPublicKey();

    }

    public KeyStore getKeystore() {
        return keystore;
    }

    public void setKeystore(KeyStore keystore) {
        this.keystore = keystore;
    }

    public ParameterService getParamServ() {
        return paramServ;
    }

    public void setParamServ(ParameterService paramServ) {
        this.paramServ = paramServ;
    }

//    @Override
//    public JWSAlgorithm getAlgorithm() {
//        if (!org.springframework.util.StringUtils.isEmpty(paramServ.getParam("ASYNC_SIGNATURE")) && Boolean.parseBoolean(paramServ.getParam("ASYNC_SIGNATURE"))) {
//            return JWSAlgorithm.RS256;
//        }
//        return JWSAlgorithm.HS256;
//    }
    
    @Override
    public SignatureAlgorithm getAlgorithm() {
        if (!org.springframework.util.StringUtils.isEmpty(paramServ.getParam("ASYNC_SIGNATURE")) && Boolean.parseBoolean(paramServ.getParam("ASYNC_SIGNATURE"))) {
            return SignatureAlgorithm.RS256;
        }
        return SignatureAlgorithm.HS256;
    }

}
