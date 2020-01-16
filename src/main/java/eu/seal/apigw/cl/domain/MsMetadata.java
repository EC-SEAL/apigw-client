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
import com.fasterxml.jackson.annotation.JsonProperty;

import eu.seal.apigw.cl.domain.PublishedApiType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;


/**
 * Specification of a given microservice.
 */
@ApiModel(description = "Specification of a given microservice.")
@Validated

public class MsMetadata   {
  @JsonProperty("msId")
  private String msId = null;

  @JsonProperty("authorisedMicroservices")
  @Valid
  private List<String> authorisedMicroservices = null;

  @JsonProperty("rsaPublicKeyBinary")
  private String rsaPublicKeyBinary = null;

  @JsonProperty("publishedAPI")
  @Valid
  private List<PublishedApiType> publishedAPI = null;

  public MsMetadata msId(String msId) {
    this.msId = msId;
    return this;
  }

  /**
   * A unique readable name for a microservice.
   * @return msId
  **/
  @ApiModelProperty(value = "A unique readable name for a microservice.")


  public String getMsId() {
    return msId;
  }

  public void setMsId(String msId) {
    this.msId = msId;
  }

  public MsMetadata authorisedMicroservices(List<String> authorisedMicroservices) {
    this.authorisedMicroservices = authorisedMicroservices;
    return this;
  }

  public MsMetadata addAuthorisedMicroservicesItem(String authorisedMicroservicesItem) {
    if (this.authorisedMicroservices == null) {
      this.authorisedMicroservices = new ArrayList<String>();
    }
    this.authorisedMicroservices.add(authorisedMicroservicesItem);
    return this;
  }

  /**
   * List of ms identifiers that will be authorised to contact this microservice (will be used by the SM when validating a token).
   * @return authorisedMicroservices
  **/
  @ApiModelProperty(value = "List of ms identifiers that will be authorised to contact this microservice (will be used by the SM when validating a token).")


  public List<String> getAuthorisedMicroservices() {
    return authorisedMicroservices;
  }

  public void setAuthorisedMicroservices(List<String> authorisedMicroservices) {
    this.authorisedMicroservices = authorisedMicroservices;
  }

  public MsMetadata rsaPublicKeyBinary(String rsaPublicKeyBinary) {
    this.rsaPublicKeyBinary = rsaPublicKeyBinary;
    return this;
  }

  /**
   * Public key of the microservice in B64. The ms will keep their own private key.
   * @return rsaPublicKeyBinary
  **/
  @ApiModelProperty(value = "Public key of the microservice in B64. The ms will keep their own private key.")


  public String getRsaPublicKeyBinary() {
    return rsaPublicKeyBinary;
  }

  public void setRsaPublicKeyBinary(String rsaPublicKeyBinary) {
    this.rsaPublicKeyBinary = rsaPublicKeyBinary;
  }

  public MsMetadata publishedAPI(List<PublishedApiType> publishedAPI) {
    this.publishedAPI = publishedAPI;
    return this;
  }

  public MsMetadata addPublishedAPIItem(PublishedApiType publishedAPIItem) {
    if (this.publishedAPI == null) {
      this.publishedAPI = new ArrayList<PublishedApiType>();
    }
    this.publishedAPI.add(publishedAPIItem);
    return this;
  }

  /**
   * List of the interfaces api endpoints implememnted by this ms (this will determine which types does the ms belong to)
   * @return publishedAPI
  **/
  @ApiModelProperty(value = "List of the interfaces api endpoints implememnted by this ms (this will determine which types does the ms belong to)")

  @Valid

  public List<PublishedApiType> getPublishedAPI() {
    return publishedAPI;
  }

  public void setPublishedAPI(List<PublishedApiType> publishedAPI) {
    this.publishedAPI = publishedAPI;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MsMetadata msMetadata = (MsMetadata) o;
    return Objects.equals(this.msId, msMetadata.msId) &&
        Objects.equals(this.authorisedMicroservices, msMetadata.authorisedMicroservices) &&
        Objects.equals(this.rsaPublicKeyBinary, msMetadata.rsaPublicKeyBinary) &&
        Objects.equals(this.publishedAPI, msMetadata.publishedAPI);
  }

  @Override
  public int hashCode() {
    return Objects.hash(msId, authorisedMicroservices, rsaPublicKeyBinary, publishedAPI);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MsMetadata {\n");
    
    sb.append("    msId: ").append(toIndentedString(msId)).append("\n");
    sb.append("    authorisedMicroservices: ").append(toIndentedString(authorisedMicroservices)).append("\n");
    sb.append("    rsaPublicKeyBinary: ").append(toIndentedString(rsaPublicKeyBinary)).append("\n");
    sb.append("    publishedAPI: ").append(toIndentedString(publishedAPI)).append("\n");
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
  
  
  // Whether the apiClass is within the publishedAPI.
  public Boolean isClassProvided (String apiClass) {
	  
	  Iterator<PublishedApiType> publishedApiTypeIterator = this.getPublishedAPI().iterator();
	  while (publishedApiTypeIterator.hasNext() ) {
		  if (publishedApiTypeIterator.next().getApiClass().toString().equals(apiClass))
			  return true;
	  }
	  
	  return false;
  }
  
  // Whether the apiCall is within the publishedAPI.
  public Boolean isCallProvided (String apiCall) {
	  
	  Iterator<PublishedApiType> publishedApiTypeIterator = this.getPublishedAPI().iterator();
	  while (publishedApiTypeIterator.hasNext() ) {
		  if (publishedApiTypeIterator.next().getApiCall().equals(apiCall))
			  return true;
	  }
	  
	  return false;
  }
}

