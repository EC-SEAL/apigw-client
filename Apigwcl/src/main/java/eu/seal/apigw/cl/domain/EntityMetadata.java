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

import eu.seal.apigw.cl.domain.EndpointType;
import eu.seal.apigw.cl.domain.SecurityKeyType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Information about the an external entity
 */
@ApiModel(description = "Information about the an external entity")
@Validated
@javax.annotation.Generated(value = "eu.seal.cm.codegen.languages.SpringCodegen", date = "2018-12-10T12:53:06.421Z")

public class EntityMetadata   {
  @JsonProperty("entityId")
  private String entityId = null;

  @JsonProperty("defaultDisplayName")
  private String defaultDisplayName = null;

  @JsonProperty("displayNames")
  @Valid
  private Map<String, String> displayNames = null;

  @JsonProperty("logo")
  private String logo = null;

  @JsonProperty("location")
  @Valid
  private List<String> location = null;

  @JsonProperty("protocol")
  private String protocol = null;

  @JsonProperty("microservice")
  @Valid
  private List<String> microservice = null;

  @JsonProperty("claims")
  @Valid
  private List<String> claims = null;

  @JsonProperty("endpoints")
  @Valid
  private List<EndpointType> endpoints = null;

  @JsonProperty("securityKeys")
  @Valid
  private List<SecurityKeyType> securityKeys = null;

  @JsonProperty("encryptResponses")
  private Boolean encryptResponses = null;

  @JsonProperty("supportedEncryptionAlg")
  @Valid
  private List<String> supportedEncryptionAlg = null;

  @JsonProperty("signResponses")
  private Boolean signResponses = null;

  @JsonProperty("supportedSigningAlg")
  @Valid
  private List<String> supportedSigningAlg = null;

  @JsonProperty("otherData")
  private Object otherData = null;

  public EntityMetadata entityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

  /**
   * Unique identifier of the entity, usually a metadata url
   * @return entityId
  **/
  @ApiModelProperty(example = "https://.../saml/idp/metadata.xml", value = "Unique identifier of the entity, usually a metadata url")


  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public EntityMetadata defaultDisplayName(String defaultDisplayName) {
    this.defaultDisplayName = defaultDisplayName;
    return this;
  }

  /**
   * Name to be displayed on UI, in the default language/encoding
   * @return defaultDisplayName
  **/
  @ApiModelProperty(example = "UJI Identity Provider", value = "Name to be displayed on UI, in the default language/encoding")


  public String getDefaultDisplayName() {
    return defaultDisplayName;
  }

  public void setDefaultDisplayName(String defaultDisplayName) {
    this.defaultDisplayName = defaultDisplayName;
  }

  public EntityMetadata displayNames(Map<String, String> displayNames) {
    this.displayNames = displayNames;
    return this;
  }

  public EntityMetadata putDisplayNamesItem(String key, String displayNamesItem) {
    if (this.displayNames == null) {
      this.displayNames = new HashMap<String, String>();
    }
    this.displayNames.put(key, displayNamesItem);
    return this;
  }

  /**
   * list of alternative display names, by language or encoding
   * @return displayNames
  **/
  @ApiModelProperty(example = "{\"ES\":\"UJI Proveedor de Identidad\",\"EN\":\"UJI Identity Provider\"}", value = "list of alternative display names, by language or encoding")


  public Map<String, String> getDisplayNames() {
    return displayNames;
  }

  public void setDisplayNames(Map<String, String> displayNames) {
    this.displayNames = displayNames;
  }

  public EntityMetadata logo(String logo) {
    this.logo = logo;
    return this;
  }

  /**
   * B64 string with an image binary to be displayed at UI
   * @return logo
  **/
  @ApiModelProperty(example = "AWDGRsFbFDEfFGTNNJKKYGFVFfDDSSSDCCC==", value = "B64 string with an image binary to be displayed at UI")


  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public EntityMetadata location(List<String> location) {
    this.location = location;
    return this;
  }

  public EntityMetadata addLocationItem(String locationItem) {
    if (this.location == null) {
      this.location = new ArrayList<String>();
    }
    this.location.add(locationItem);
    return this;
  }

  /**
   * unspecified list of information items about the physical or political location of the entity, to facilitate discovery
   * @return location
  **/
  @ApiModelProperty(value = "unspecified list of information items about the physical or political location of the entity, to facilitate discovery")


  public List<String> getLocation() {
    return location;
  }

  public void setLocation(List<String> location) {
    this.location = location;
  }

  public EntityMetadata protocol(String protocol) {
    this.protocol = protocol;
    return this;
  }

  /**
   * Which protocol does this entity support (SAML, OIDC, etc.)
   * @return protocol
  **/
  @ApiModelProperty(value = "Which protocol does this entity support (SAML, OIDC, etc.)")


  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public EntityMetadata microservice(List<String> microservice) {
    this.microservice = microservice;
    return this;
  }

  public EntityMetadata addMicroserviceItem(String microserviceItem) {
    if (this.microservice == null) {
      this.microservice = new ArrayList<String>();
    }
    this.microservice.add(microserviceItem);
    return this;
  }

  /**
   * list of identifiers of microservice able to handle this external entity
   * @return microservice
  **/
  @ApiModelProperty(value = "list of identifiers of microservice able to handle this external entity")


  public List<String> getMicroservice() {
    return microservice;
  }

  public void setMicroservice(List<String> microservice) {
    this.microservice = microservice;
  }

  public EntityMetadata claims(List<String> claims) {
    this.claims = claims;
    return this;
  }

  public EntityMetadata addClaimsItem(String claimsItem) {
    if (this.claims == null) {
      this.claims = new ArrayList<String>();
    }
    this.claims.add(claimsItem);
    return this;
  }

  /**
   * list of attributes supported/requested by default by this entity
   * @return claims
  **/
  @ApiModelProperty(example = "[\"displayName\",\"surname\",\"dateOfBirth\",\"eduPersonAffiliation\"]", value = "list of attributes supported/requested by default by this entity")


  public List<String> getClaims() {
    return claims;
  }

  public void setClaims(List<String> claims) {
    this.claims = claims;
  }

  public EntityMetadata endpoints(List<EndpointType> endpoints) {
    this.endpoints = endpoints;
    return this;
  }

  public EntityMetadata addEndpointsItem(EndpointType endpointsItem) {
    if (this.endpoints == null) {
      this.endpoints = new ArrayList<EndpointType>();
    }
    this.endpoints.add(endpointsItem);
    return this;
  }

  /**
   * List of service endpoints, where this Entity will accept requests/responses.
   * @return endpoints
  **/
  @ApiModelProperty(value = "List of service endpoints, where this Entity will accept requests/responses.")

  @Valid

  public List<EndpointType> getEndpoints() {
    return endpoints;
  }

  public void setEndpoints(List<EndpointType> endpoints) {
    this.endpoints = endpoints;
  }

  public EntityMetadata securityKeys(List<SecurityKeyType> securityKeys) {
    this.securityKeys = securityKeys;
    return this;
  }

  public EntityMetadata addSecurityKeysItem(SecurityKeyType securityKeysItem) {
    if (this.securityKeys == null) {
      this.securityKeys = new ArrayList<SecurityKeyType>();
    }
    this.securityKeys.add(securityKeysItem);
    return this;
  }

  /**
   * List of keys held by this entity
   * @return securityKeys
  **/
  @ApiModelProperty(value = "List of keys held by this entity")

  @Valid

  public List<SecurityKeyType> getSecurityKeys() {
    return securityKeys;
  }

  public void setSecurityKeys(List<SecurityKeyType> securityKeys) {
    this.securityKeys = securityKeys;
  }

  public EntityMetadata encryptResponses(Boolean encryptResponses) {
    this.encryptResponses = encryptResponses;
    return this;
  }

  /**
   * whether this entity will issue/expect encrypted responses
   * @return encryptResponses
  **/
  @ApiModelProperty(example = "false", value = "whether this entity will issue/expect encrypted responses")


  public Boolean isEncryptResponses() {
    return encryptResponses;
  }

  public void setEncryptResponses(Boolean encryptResponses) {
    this.encryptResponses = encryptResponses;
  }

  public EntityMetadata supportedEncryptionAlg(List<String> supportedEncryptionAlg) {
    this.supportedEncryptionAlg = supportedEncryptionAlg;
    return this;
  }

  public EntityMetadata addSupportedEncryptionAlgItem(String supportedEncryptionAlgItem) {
    if (this.supportedEncryptionAlg == null) {
      this.supportedEncryptionAlg = new ArrayList<String>();
    }
    this.supportedEncryptionAlg.add(supportedEncryptionAlgItem);
    return this;
  }

  /**
   * list of supported encryption algorithms
   * @return supportedEncryptionAlg
  **/
  @ApiModelProperty(example = "[\"AES256\",\"AES512\"]", value = "list of supported encryption algorithms")


  public List<String> getSupportedEncryptionAlg() {
    return supportedEncryptionAlg;
  }

  public void setSupportedEncryptionAlg(List<String> supportedEncryptionAlg) {
    this.supportedEncryptionAlg = supportedEncryptionAlg;
  }

  public EntityMetadata signResponses(Boolean signResponses) {
    this.signResponses = signResponses;
    return this;
  }

  /**
   * whether this entity will issue/expect signed responses
   * @return signResponses
  **/
  @ApiModelProperty(example = "true", value = "whether this entity will issue/expect signed responses")


  public Boolean isSignResponses() {
    return signResponses;
  }

  public void setSignResponses(Boolean signResponses) {
    this.signResponses = signResponses;
  }

  public EntityMetadata supportedSigningAlg(List<String> supportedSigningAlg) {
    this.supportedSigningAlg = supportedSigningAlg;
    return this;
  }

  public EntityMetadata addSupportedSigningAlgItem(String supportedSigningAlgItem) {
    if (this.supportedSigningAlg == null) {
      this.supportedSigningAlg = new ArrayList<String>();
    }
    this.supportedSigningAlg.add(supportedSigningAlgItem);
    return this;
  }

  /**
   * list of supported signing algorithms
   * @return supportedSigningAlg
  **/
  @ApiModelProperty(example = "[\"RSA-SHA256\"]", value = "list of supported signing algorithms")


  public List<String> getSupportedSigningAlg() {
    return supportedSigningAlg;
  }

  public void setSupportedSigningAlg(List<String> supportedSigningAlg) {
    this.supportedSigningAlg = supportedSigningAlg;
  }

  public EntityMetadata otherData(Object otherData) {
    this.otherData = otherData;
    return this;
  }

  /**
   * Dictionary of additional fields, specific for a certain entity type or protocol
   * @return otherData
  **/
  @ApiModelProperty(example = "[{\"attributeMappingToEIDAS\":{\"displayName\":\"CurrentGivenName\",\"surname\":\"CurrentFamilyName\"}}]", value = "Dictionary of additional fields, specific for a certain entity type or protocol")


  public Object getOtherData() {
    return otherData;
  }

  public void setOtherData(Object otherData) {
    this.otherData = otherData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntityMetadata entityMetadata = (EntityMetadata) o;
    return Objects.equals(this.entityId, entityMetadata.entityId) &&
        Objects.equals(this.defaultDisplayName, entityMetadata.defaultDisplayName) &&
        Objects.equals(this.displayNames, entityMetadata.displayNames) &&
        Objects.equals(this.logo, entityMetadata.logo) &&
        Objects.equals(this.location, entityMetadata.location) &&
        Objects.equals(this.protocol, entityMetadata.protocol) &&
        Objects.equals(this.microservice, entityMetadata.microservice) &&
        Objects.equals(this.claims, entityMetadata.claims) &&
        Objects.equals(this.endpoints, entityMetadata.endpoints) &&
        Objects.equals(this.securityKeys, entityMetadata.securityKeys) &&
        Objects.equals(this.encryptResponses, entityMetadata.encryptResponses) &&
        Objects.equals(this.supportedEncryptionAlg, entityMetadata.supportedEncryptionAlg) &&
        Objects.equals(this.signResponses, entityMetadata.signResponses) &&
        Objects.equals(this.supportedSigningAlg, entityMetadata.supportedSigningAlg) &&
        Objects.equals(this.otherData, entityMetadata.otherData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entityId, defaultDisplayName, displayNames, logo, location, protocol, microservice, claims, endpoints, securityKeys, encryptResponses, supportedEncryptionAlg, signResponses, supportedSigningAlg, otherData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntityMetadata {\n");
    
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    defaultDisplayName: ").append(toIndentedString(defaultDisplayName)).append("\n");
    sb.append("    displayNames: ").append(toIndentedString(displayNames)).append("\n");
    sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    protocol: ").append(toIndentedString(protocol)).append("\n");
    sb.append("    microservice: ").append(toIndentedString(microservice)).append("\n");
    sb.append("    claims: ").append(toIndentedString(claims)).append("\n");
    sb.append("    endpoints: ").append(toIndentedString(endpoints)).append("\n");
    sb.append("    securityKeys: ").append(toIndentedString(securityKeys)).append("\n");
    sb.append("    encryptResponses: ").append(toIndentedString(encryptResponses)).append("\n");
    sb.append("    supportedEncryptionAlg: ").append(toIndentedString(supportedEncryptionAlg)).append("\n");
    sb.append("    signResponses: ").append(toIndentedString(signResponses)).append("\n");
    sb.append("    supportedSigningAlg: ").append(toIndentedString(supportedSigningAlg)).append("\n");
    sb.append("    otherData: ").append(toIndentedString(otherData)).append("\n");
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

