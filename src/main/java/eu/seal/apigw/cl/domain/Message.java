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
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Message
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-14T08:21:59.866Z")

public class Message   {
  @JsonProperty("timestamp")
  private Integer timestamp = null;

  @JsonProperty("sender")
  private String sender = null;

  @JsonProperty("senderType")
  private String senderType = null;

  @JsonProperty("recipient")
  private String recipient = null;

  @JsonProperty("recipientType")
  private String recipientType = null;

  @JsonProperty("message")
  private String message = null;

  public Message timestamp(Integer timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * date and time when the message was sent
   * @return timestamp
  **/
  @ApiModelProperty(value = "date and time when the message was sent")


  public Integer getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }

  public Message sender(String sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Identifier of the user who sent the message
   * @return sender
  **/
  @ApiModelProperty(value = "Identifier of the user who sent the message")


  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public Message senderType(String senderType) {
    this.senderType = senderType;
    return this;
  }

  /**
   * Identifier of the user category who sent the message
   * @return senderType
  **/
  @ApiModelProperty(value = "Identifier of the user category who sent the message")


  public String getSenderType() {
    return senderType;
  }

  public void setSenderType(String senderType) {
    this.senderType = senderType;
  }

  public Message recipient(String recipient) {
    this.recipient = recipient;
    return this;
  }

  /**
   * Identifier of the user whom the message is addressed to
   * @return recipient
  **/
  @ApiModelProperty(value = "Identifier of the user whom the message is addressed to")


  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public Message recipientType(String recipientType) {
    this.recipientType = recipientType;
    return this;
  }

  /**
   * Identifier of the user category whom the message is addressed to
   * @return recipientType
  **/
  @ApiModelProperty(value = "Identifier of the user category whom the message is addressed to")


  public String getRecipientType() {
    return recipientType;
  }

  public void setRecipientType(String recipientType) {
    this.recipientType = recipientType;
  }

  public Message message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Content of the message
   * @return message
  **/
  @ApiModelProperty(value = "Content of the message")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Message message = (Message) o;
    return Objects.equals(this.timestamp, message.timestamp) &&
        Objects.equals(this.sender, message.sender) &&
        Objects.equals(this.senderType, message.senderType) &&
        Objects.equals(this.recipient, message.recipient) &&
        Objects.equals(this.recipientType, message.recipientType) &&
        Objects.equals(this.message, message.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, sender, senderType, recipient, recipientType, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Message {\n");
    
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    senderType: ").append(toIndentedString(senderType)).append("\n");
    sb.append("    recipient: ").append(toIndentedString(recipient)).append("\n");
    sb.append("    recipientType: ").append(toIndentedString(recipientType)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

