/**
Copyright © 2019  Atos Spain SA. All rights reserved.
This file is part of SEAL Configuration Manager (SEAL ConfMngr).
SEAL ConfMngr is free software: you can redistribute it and/or modify it under the terms of EUPL 1.2.
THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT ANY WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT, 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
See README file for the full disclaimer information and LICENSE file for full license information in the project root.
*/
package eu.seal.apigw.cl.domain;

import java.util.Objects;
import java.util.Optional;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import eu.seal.apigw.cl.domain.MsMetadata;


/**
 * MsMetadataList
 */
@Validated

public class MsMetadataList extends ArrayList<MsMetadata>  {
	
	private static final Logger LOG = LoggerFactory
            .getLogger(MsMetadataList.class);

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MsMetadataList {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  
  // Get the list of ms with a given apiClass
  public MsMetadataList getClassMs (String apiClass) {
	  
	  MsMetadataList classMs = new MsMetadataList();
	  
	  MsMetadata ms;
	  Iterator<MsMetadata> msMetadataIterator = this.iterator();
	  while (msMetadataIterator.hasNext()) {
		  ms = msMetadataIterator.next();
		  if (!ms.getPublishedAPI().isEmpty()) {
			  if (ms.isClassProvided (apiClass))
				  classMs.add (ms);
		  }	  
	  }
	 
	  return classMs;
  }
  
//Get the list of ms with a given apiClass
 public MsMetadataList getApicallMs (String apiCall) {
	  
	  MsMetadataList callMs = new MsMetadataList();
	  
	  MsMetadata ms;
	  Iterator<MsMetadata> msMetadataIterator = this.iterator();
	  while (msMetadataIterator.hasNext()) {
		  ms = msMetadataIterator.next();
		  if (!ms.getPublishedAPI().isEmpty()) {
			  if (ms.isCallProvided(apiCall))
				  callMs.add (ms);
		  }	  
	  }
	 
	  return callMs;
 }
 
//Get the ms with a given name
public MsMetadata getMs (String msId) {
	  
	  MsMetadata ms = null;
	  Iterator<MsMetadata> msMetadataIterator = this.iterator();
	  while (msMetadataIterator.hasNext()) {
		  ms = msMetadataIterator.next();
		  
		  if (ms.getMsId().equals(msId))
			  break; 
		  	  
	  }
	 
	  return ms;
}
 
  
  
  
  // Nikos's 
  public Optional<PublicKey> getPublicKeyFromFingerPrint(String rsaFingerPrint) throws InvalidKeyException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
      
	  MsMetadataList config = this; //getConfigurationJSON();

      if (config != null) {
          MsMetadata msMatch = null;
          for (MsMetadata msConfig : config) {
        	  try {
                  byte[] encodedBytes = getPublicKey(msConfig.getRsaPublicKeyBinary()).getEncoded();
//                  System.out.println(
//                          "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCi7jZVwQFxQ2SY4lxjr05IexolQJJobwYzrvE5pk7AcQpG46kuJBzD8ziiqFFCGSNZ07cLWys+b5JmJ6kU44lKLVeGbEpgaO0OTBDLMk2fi5U83T8dezgWgaPFiy/N3sHPpcW2Y3ZePo0UbM7MLzv14TR+jxTOyrmwWwGwDJsz+wIDAQAB"
//                          .equals(msConfig.getRsaPublicKeyBinary()));
//                  
//                  System.out.println(msConfig.getRsaPublicKeyBinary());
                  if ( DigestUtils.sha256Hex(encodedBytes).equals(rsaFingerPrint)) {
                	  msMatch = msConfig;
                	  break;
                  }
              } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                  LOG.error("error parsing msconfig public keys ");
                  LOG.error(e.getMessage());
                  
                  	break;
              }
        	  
          }
          
          if (msMatch != null) {
             return Optional.of(getPublicKey(msMatch.getRsaPublicKeyBinary()));
        	 
          }
      }
      
      return Optional.empty();
  }
  
  //Nikos's 
  public RSAPublicKey getPublicKey(String keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
      byte[] publicBytes = Base64.getDecoder().decode(keyBytes);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return (RSAPublicKey) keyFactory.generatePublic(keySpec);

  }

}

