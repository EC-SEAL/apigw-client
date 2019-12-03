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

import eu.seal.apigw.cl.domain.ApiClassEnum;
import eu.seal.apigw.cl.domain.ApiConnectionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;


/**
 * Endpoint descriptor, where requests can be made
 */
@ApiModel(description = "Endpoint descriptor, where requests can be made")
@Validated
@javax.annotation.Generated(value = "eu.seal.cm.codegen.languages.SpringCodegen", date = "2018-12-10T12:53:06.421Z")

public class PublishedApiType   {
  @JsonProperty("apiClass")
  private ApiClassEnum apiClass = null;

  @JsonProperty("apiCall")
  private String apiCall = null;

  @JsonProperty("apiConnectionType")
  private ApiConnectionType apiConnectionType = null;

  @JsonProperty("apiEndpoint")
  private String apiEndpoint = null;

  public PublishedApiType apiClass(ApiClassEnum apiClass) {
    this.apiClass = apiClass;
    return this;
  }

  /**
   * Get apiClass
   * @return apiClass
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ApiClassEnum getApiClass() {
    return apiClass;
  }

  public void setApiClass(ApiClassEnum apiClass) {
    this.apiClass = apiClass;
  }

  public PublishedApiType apiCall(String apiCall) {
    this.apiCall = apiCall;
    return this;
  }

  /**
   * Get apiCall
   * @return apiCall
  **/
  @ApiModelProperty(value = "")

  @Valid

  public String getApiCall() {
    return apiCall;
  }

  public void setApiCall(String apiCall) {
    this.apiCall = apiCall;
  }

  public PublishedApiType apiConnectionType(ApiConnectionType apiConnectionType) {
    this.apiConnectionType = apiConnectionType;
    return this;
  }

  /**
   * Get apiConnectionType
   * @return apiConnectionType
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ApiConnectionType getApiConnectionType() {
    return apiConnectionType;
  }

  public void setApiConnectionType(ApiConnectionType apiConnectionType) {
    this.apiConnectionType = apiConnectionType;
  }

  public PublishedApiType apiEndpoint(String apiEndpoint) {
    this.apiEndpoint = apiEndpoint;
    return this;
  }

  /**
   * Get apiEndpoint
   * @return apiEndpoint
  **/
  @ApiModelProperty(value = "")


  public String getApiEndpoint() {
    return apiEndpoint;
  }

  public void setApiEndpoint(String apiEndpoint) {
    this.apiEndpoint = apiEndpoint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PublishedApiType publishedApiType = (PublishedApiType) o;
    return Objects.equals(this.apiClass, publishedApiType.apiClass) &&
        Objects.equals(this.apiCall, publishedApiType.apiCall) &&
        Objects.equals(this.apiConnectionType, publishedApiType.apiConnectionType) &&
        Objects.equals(this.apiEndpoint, publishedApiType.apiEndpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiClass, apiCall, apiConnectionType, apiEndpoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PublishedApiType {\n");
    
    sb.append("    apiClass: ").append(toIndentedString(apiClass)).append("\n");
    sb.append("    apiCall: ").append(toIndentedString(apiCall)).append("\n");
    sb.append("    apiConnectionType: ").append(toIndentedString(apiConnectionType)).append("\n");
    sb.append("    apiEndpoint: ").append(toIndentedString(apiEndpoint)).append("\n");
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
}

