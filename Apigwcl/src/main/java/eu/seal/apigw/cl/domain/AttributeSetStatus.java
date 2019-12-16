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
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * AttributeSetStatus
 */
@Validated

public class AttributeSetStatus   {
  /**
   * main standard status code from a closed list.
   */
  public enum CodeEnum {
    OK("OK"),
    
    ERROR("ERROR");

    private String value;

    CodeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CodeEnum fromValue(String text) {
      for (CodeEnum b : CodeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("code")
  private CodeEnum code = null;

  @JsonProperty("subcode")
  private String subcode = null;

  @JsonProperty("message")
  private String message = null;

  public AttributeSetStatus code(CodeEnum code) {
    this.code = code;
    return this;
  }

  /**
   * main standard status code from a closed list.
   * @return code
  **/
  @ApiModelProperty(value = "main standard status code from a closed list.")


  public CodeEnum getCode() {
    return code;
  }

  public void setCode(CodeEnum code) {
    this.code = code;
  }

  public AttributeSetStatus subcode(String subcode) {
    this.subcode = subcode;
    return this;
  }

  /**
   * free text field to represent status codes, open for specific applications or fluxes.
   * @return subcode
  **/
  @ApiModelProperty(value = "free text field to represent status codes, open for specific applications or fluxes.")


  public String getSubcode() {
    return subcode;
  }

  public void setSubcode(String subcode) {
    this.subcode = subcode;
  }

  public AttributeSetStatus message(String message) {
    this.message = message;
    return this;
  }

  /**
   * a free text string to provide human-readable status-error information
   * @return message
  **/
  @ApiModelProperty(value = "a free text string to provide human-readable status-error information")


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
    AttributeSetStatus attributeSetStatus = (AttributeSetStatus) o;
    return Objects.equals(this.code, attributeSetStatus.code) &&
        Objects.equals(this.subcode, attributeSetStatus.subcode) &&
        Objects.equals(this.message, attributeSetStatus.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, subcode, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttributeSetStatus {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    subcode: ").append(toIndentedString(subcode)).append("\n");
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

