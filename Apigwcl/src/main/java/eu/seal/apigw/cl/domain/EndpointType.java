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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * Endpoint descriptor, where requests can be made or responses sent.
 */
@ApiModel(description = "Endpoint descriptor, where requests can be made or responses sent.")
@Validated
@javax.annotation.Generated(value = "eu.seal.cm.codegen.languages.SpringCodegen", date = "2018-12-10T12:53:06.421Z")

public class EndpointType   {
  @JsonProperty("type")
  private String type = null;

  @JsonProperty("method")
  private String method = null;

  @JsonProperty("url")
  private String url = null;

  public EndpointType type(String type) {
    this.type = type;
    return this;
  }

  /**
   * String identifying the kind of endpoint (depends on each protocol)
   * @return type
  **/
  @ApiModelProperty(example = "SSOService", value = "String identifying the kind of endpoint (depends on each protocol)")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public EndpointType method(String method) {
    this.method = method;
    return this;
  }

  /**
   * String identifying the method to access the endpoint (depends on each protocol, i.e. HTTP-POST).
   * @return method
  **/
  @ApiModelProperty(example = "HTTP-POST", value = "String identifying the method to access the endpoint (depends on each protocol, i.e. HTTP-POST).")


  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public EndpointType url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Access url of the endpoint
   * @return url
  **/
  @ApiModelProperty(example = "https://../saml/idp/SSOService.php", value = "Access url of the endpoint")


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EndpointType endpointType = (EndpointType) o;
    return Objects.equals(this.type, endpointType.type) &&
        Objects.equals(this.method, endpointType.method) &&
        Objects.equals(this.url, endpointType.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, method, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EndpointType {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    method: ").append(toIndentedString(method)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

