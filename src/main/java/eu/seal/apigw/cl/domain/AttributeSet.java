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
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Object representing a generic request/response. Used for in-application transference of the security assertions or claims containing trusted user information (attribute values); also to set the list of attributes to be requested.
 */
@ApiModel(description = "Object representing a generic request/response. Used for in-application transference of the security assertions or claims containing trusted user information (attribute values); also to set the list of attributes to be requested.")
@Validated

public class AttributeSet   {
  @JsonProperty("id")
  private String id = null;

  /**
   * Kind of set
   */
  public enum TypeEnum {
    REQUEST("Request"),
    
    RESPONSE("Response"),
    
    AUTHRESPONSE("AuthResponse");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("issuer")
  private String issuer = null;

  @JsonProperty("recipient")
  private String recipient = null;

  @JsonProperty("inResponseTo")
  private String inResponseTo = null;

  @JsonProperty("loa")
  private String loa = null;

  @JsonProperty("notBefore")
  private String notBefore = null;

  @JsonProperty("notAfter")
  private String notAfter = null;

  @JsonProperty("status")
  private AttributeSetStatus status = null;

  @JsonProperty("attributes")
  @Valid
  private List<AttributeType> attributes = null;

  @JsonProperty("properties")
  @Valid
  private Map<String, String> properties = null;

  public AttributeSet id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the set
   * @return id
  **/
  @ApiModelProperty(example = "6c0f70a8-f32b-4535-b5f6-0d596c52813a", value = "Unique identifier of the set")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AttributeSet type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Kind of set
   * @return type
  **/
  @ApiModelProperty(example = "Response", value = "Kind of set")


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public AttributeSet issuer(String issuer) {
    this.issuer = issuer;
    return this;
  }

  /**
   * ID of the External Entity which issued the set
   * @return issuer
  **/
  @ApiModelProperty(example = "https://esmo.uji.es/gw/saml/idp/metadata.xml", value = "ID of the External Entity which issued the set")


  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public AttributeSet recipient(String recipient) {
    this.recipient = recipient;
    return this;
  }

  /**
   * ID of the External Entity which the set is addressed to
   * @return recipient
  **/
  @ApiModelProperty(example = "https://esmo.uji.es/gw/saml/sp/metadata.xml", value = "ID of the External Entity which the set is addressed to")


  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public AttributeSet inResponseTo(String inResponseTo) {
    this.inResponseTo = inResponseTo;
    return this;
  }

  /**
   * In response objects, the ID of the request which the set is responding to
   * @return inResponseTo
  **/
  @ApiModelProperty(example = "aaaa70a8-f32b-4535-b5f6-0d596cbbbbb", value = "In response objects, the ID of the request which the set is responding to")


  public String getInResponseTo() {
    return inResponseTo;
  }

  public void setInResponseTo(String inResponseTo) {
    this.inResponseTo = inResponseTo;
  }

  public AttributeSet loa(String loa) {
    this.loa = loa;
    return this;
  }

  /**
   * Level of trust of the authentication related to the set, if it is a response for an eIDAS authentication request
   * @return loa
  **/
  @ApiModelProperty(example = "http://eidas.europa.eu/LoA/substantial", value = "Level of trust of the authentication related to the set, if it is a response for an eIDAS authentication request")


  public String getLoa() {
    return loa;
  }

  public void setLoa(String loa) {
    this.loa = loa;
  }

  public AttributeSet notBefore(String notBefore) {
    this.notBefore = notBefore;
    return this;
  }

  /**
   * Minimum validity date of the set
   * @return notBefore
  **/
  @ApiModelProperty(example = "2018-12-06T19:40:16Z", value = "Minimum validity date of the set")


  public String getNotBefore() {
    return notBefore;
  }

  public void setNotBefore(String notBefore) {
    this.notBefore = notBefore;
  }

  public AttributeSet notAfter(String notAfter) {
    this.notAfter = notAfter;
    return this;
  }

  /**
   * Maximum validity date of the set
   * @return notAfter
  **/
  @ApiModelProperty(example = "2018-12-06T19:45:16Z", value = "Maximum validity date of the set")


  public String getNotAfter() {
    return notAfter;
  }

  public void setNotAfter(String notAfter) {
    this.notAfter = notAfter;
  }

  public AttributeSet status(AttributeSetStatus status) {
    this.status = status;
    return this;
  }

  /**
   * To represent the tatus of the set (ok, error, etc.).
   * @return status
  **/
  @ApiModelProperty(value = "To represent the tatus of the set (ok, error, etc.).")

  @Valid

  public AttributeSetStatus getStatus() {
    return status;
  }

  public void setStatus(AttributeSetStatus status) {
    this.status = status;
  }

  public AttributeSet attributes(List<AttributeType> attributes) {
    this.attributes = attributes;
    return this;
  }

  public AttributeSet addAttributesItem(AttributeType attributesItem) {
    if (this.attributes == null) {
      this.attributes = new ArrayList<AttributeType>();
    }
    this.attributes.add(attributesItem);
    return this;
  }

  /**
   * The list of the attributes or claims related to the user, contained on the set
   * @return attributes
  **/
  @ApiModelProperty(value = "The list of the attributes or claims related to the user, contained on the set")

  @Valid

  public List<AttributeType> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<AttributeType> attributes) {
    this.attributes = attributes;
  }

  public AttributeSet properties(Map<String, String> properties) {
    this.properties = properties;
    return this;
  }

  public AttributeSet putPropertiesItem(String key, String propertiesItem) {
    if (this.properties == null) {
      this.properties = new HashMap<String, String>();
    }
    this.properties.put(key, propertiesItem);
    return this;
  }

  /**
   * Dictionary of additional fields of data related to the attributes in the set(strings only), specific for a certain entity type or protocol. List of useful metadata related to the attributes and any specific information that came with the remote SP/RP request (or was filled in with metadata stored presets for said SP/RP) and might be of need for some implementation of the local SP to issue the authentication request.
   * @return properties
  **/
  @ApiModelProperty(value = "Dictionary of additional fields of data related to the attributes in the set(strings only), specific for a certain entity type or protocol. List of useful metadata related to the attributes and any specific information that came with the remote SP/RP request (or was filled in with metadata stored presets for said SP/RP) and might be of need for some implementation of the local SP to issue the authentication request.")


  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttributeSet attributeSet = (AttributeSet) o;
    return Objects.equals(this.id, attributeSet.id) &&
        Objects.equals(this.type, attributeSet.type) &&
        Objects.equals(this.issuer, attributeSet.issuer) &&
        Objects.equals(this.recipient, attributeSet.recipient) &&
        Objects.equals(this.inResponseTo, attributeSet.inResponseTo) &&
        Objects.equals(this.loa, attributeSet.loa) &&
        Objects.equals(this.notBefore, attributeSet.notBefore) &&
        Objects.equals(this.notAfter, attributeSet.notAfter) &&
        Objects.equals(this.status, attributeSet.status) &&
        Objects.equals(this.attributes, attributeSet.attributes) &&
        Objects.equals(this.properties, attributeSet.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, issuer, recipient, inResponseTo, loa, notBefore, notAfter, status, attributes, properties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttributeSet {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    issuer: ").append(toIndentedString(issuer)).append("\n");
    sb.append("    recipient: ").append(toIndentedString(recipient)).append("\n");
    sb.append("    inResponseTo: ").append(toIndentedString(inResponseTo)).append("\n");
    sb.append("    loa: ").append(toIndentedString(loa)).append("\n");
    sb.append("    notBefore: ").append(toIndentedString(notBefore)).append("\n");
    sb.append("    notAfter: ").append(toIndentedString(notAfter)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
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

