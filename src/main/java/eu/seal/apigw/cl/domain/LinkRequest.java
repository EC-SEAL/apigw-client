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
package eu.seal.apigw.cl.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import eu.seal.apigw.cl.domain.DataSet;
import eu.seal.apigw.cl.domain.FileObject;
import eu.seal.apigw.cl.domain.Message;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a linking request, as well as its resolution and the resulting linking dataset
 */
@ApiModel(description = "Represents a linking request, as well as its resolution and the resulting linking dataset")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-14T08:21:59.866Z")

public class LinkRequest   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("issuer")
  private String issuer = null;

  @JsonProperty("lloa")
  private String lloa = null;

  @JsonProperty("issued")
  private String issued = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("expiration")
  private String expiration = null;
  
  @JsonProperty("uri")
  private String uri = null;

  @JsonProperty("datasetA")
  private DataSet datasetA = null;

  @JsonProperty("datasetB")
  private DataSet datasetB = null;

  @JsonProperty("evidence")
  @Valid
  private List<FileObject> evidence = null;

  @JsonProperty("conversation")
  @Valid
  private List<Message> conversation = null;

  public LinkRequest id(String id) {
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

  public LinkRequest issuer(String issuer) {
    this.issuer = issuer;
    return this;
  }

  /**
   * Name of the entity that issued the link.
   * @return issuer
  **/
  @ApiModelProperty(value = "Name of the entity that issued the link.")


  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public LinkRequest lloa(String lloa) {
    this.lloa = lloa;
    return this;
  }

  /**
   * Level of certainty that both subjects are the same person
   * @return lloa
  **/
  @ApiModelProperty(value = "Level of certainty that both subjects are the same person")


  public String getLloa() {
    return lloa;
  }

  public void setLloa(String lloa) {
    this.lloa = lloa;
  }

  public LinkRequest issued(String issued) {
    this.issued = issued;
    return this;
  }

  /**
   * Date when the link was certified (the date this data set was issued)
   * @return issued
  **/
  @ApiModelProperty(example = "2018-12-06T19:40:16Z", value = "Date when the link was certified (the date this data set was issued)")


  public String getIssued() {
    return issued;
  }

  public void setIssued(String issued) {
    this.issued = issued;
  }

  public LinkRequest type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Type of set.
   * @return type
  **/
  @ApiModelProperty(value = "Type of set.")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public LinkRequest expiration(String expiration) {
    this.expiration = expiration;
    return this;
  }

  /**
   * Maximum validity date of the link (empty means permanent)
   * @return expiration
  **/
  @ApiModelProperty(example = "2018-12-06T19:45:16Z", value = "Maximum validity date of the link (empty means permanent)")


  public String getExpiration() {
    return expiration;
  }

  public void setExpiration(String expiration) {
    this.expiration = expiration;
  }
  
  public String getUri() {
	    return uri;
  }

  public void setUri(String uri) {
	    this.uri = uri;
  }

  public LinkRequest uri(String uri) {
	    this.uri = uri;
	    return this;
  }

  public LinkRequest datasetA(DataSet datasetA) {
    this.datasetA = datasetA;
    return this;
  }

  /**
   * The dataset containing identity attributes or claims set A
   * @return datasetA
  **/
  @ApiModelProperty(value = "The dataset containing identity attributes or claims set A")

  @Valid

  public DataSet getDatasetA() {
    return datasetA;
  }

  public void setDatasetA(DataSet datasetA) {
    this.datasetA = datasetA;
  }

  public LinkRequest datasetB(DataSet datasetB) {
    this.datasetB = datasetB;
    return this;
  }

  /**
   * The dataset containing identity attributes or claims set B
   * @return datasetB
  **/
  @ApiModelProperty(value = "The dataset containing identity attributes or claims set B")

  @Valid

  public DataSet getDatasetB() {
    return datasetB;
  }

  public void setDatasetB(DataSet datasetB) {
    this.datasetB = datasetB;
  }

  public LinkRequest evidence(List<FileObject> evidence) {
    this.evidence = evidence;
    return this;
  }

  public LinkRequest addEvidenceItem(FileObject evidenceItem) {
    if (this.evidence == null) {
      this.evidence = new ArrayList<FileObject>();
    }
    this.evidence.add(evidenceItem);
    return this;
  }

  /**
   * List of additional files uploaded to the validator to check the person behind the identities
   * @return evidence
  **/
  @ApiModelProperty(value = "List of additional files uploaded to the validator to check the person behind the identities")

  @Valid

  public List<FileObject> getEvidence() {
    return evidence;
  }

  public void setEvidence(List<FileObject> evidence) {
    this.evidence = evidence;
  }

  public LinkRequest conversation(List<Message> conversation) {
    this.conversation = conversation;
    return this;
  }

  public LinkRequest addConversationItem(Message conversationItem) {
    if (this.conversation == null) {
      this.conversation = new ArrayList<Message>();
    }
    this.conversation.add(conversationItem);
    return this;
  }

  /**
   * List of messages exchanged between the requester and the validation officer
   * @return conversation
  **/
  @ApiModelProperty(value = "List of messages exchanged between the requester and the validation officer")

  @Valid

  public List<Message> getConversation() {
    return conversation;
  }

  public void setConversation(List<Message> conversation) {
    this.conversation = conversation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkRequest linkRequest = (LinkRequest) o;
    return Objects.equals(this.id, linkRequest.id) &&
        Objects.equals(this.issuer, linkRequest.issuer) &&
        Objects.equals(this.lloa, linkRequest.lloa) &&
        Objects.equals(this.issued, linkRequest.issued) &&
        Objects.equals(this.type, linkRequest.type) &&
        Objects.equals(this.expiration, linkRequest.expiration) &&
        Objects.equals(this.uri, linkRequest.uri) &&
        Objects.equals(this.datasetA, linkRequest.datasetA) &&
        Objects.equals(this.datasetB, linkRequest.datasetB) &&
        Objects.equals(this.evidence, linkRequest.evidence) &&
        Objects.equals(this.conversation, linkRequest.conversation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, issuer, lloa, issued, type, expiration, uri, datasetA, datasetB, evidence, conversation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinkRequest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    issuer: ").append(toIndentedString(issuer)).append("\n");
    sb.append("    lloa: ").append(toIndentedString(lloa)).append("\n");
    sb.append("    issued: ").append(toIndentedString(issued)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    expiration: ").append(toIndentedString(expiration)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    datasetA: ").append(toIndentedString(datasetA)).append("\n");
    sb.append("    datasetB: ").append(toIndentedString(datasetB)).append("\n");
    sb.append("    evidence: ").append(toIndentedString(evidence)).append("\n");
    sb.append("    conversation: ").append(toIndentedString(conversation)).append("\n");
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

