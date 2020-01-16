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
import org.springframework.validation.annotation.Validated;


/**
 * Descriptor of a key or certificate.
 */
@ApiModel(description = "Descriptor of a key or certificate.")
@Validated

public class SecurityKeyType   {
  @JsonProperty("keyType")
  private String keyType = null;

  /**
   * To which use is this key intended.
   */
  public enum UsageEnum {
    signing("signing"),
    
    encryption("encryption");

    private String value;

    UsageEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static UsageEnum fromValue(String text) {
      for (UsageEnum b : UsageEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("usage")
  private UsageEnum usage = null;

  @JsonProperty("key")
  private String key = null;

  public SecurityKeyType keyType(String keyType) {
    this.keyType = keyType;
    return this;
  }

  /**
   * String identifying the kind of key
   * @return keyType
  **/
  @ApiModelProperty(example = "RSAPublicKey", value = "String identifying the kind of key")


  public String getKeyType() {
    return keyType;
  }

  public void setKeyType(String keyType) {
    this.keyType = keyType;
  }

  public SecurityKeyType usage(UsageEnum usage) {
    this.usage = usage;
    return this;
  }

  /**
   * To which use is this key intended.
   * @return usage
  **/
  @ApiModelProperty(example = "signing", value = "To which use is this key intended.")


  public UsageEnum getUsage() {
    return usage;
  }

  public void setUsage(UsageEnum usage) {
    this.usage = usage;
  }

  public SecurityKeyType key(String key) {
    this.key = key;
    return this;
  }

  /**
   * B64 string representing the key binary
   * @return key
  **/
  @ApiModelProperty(example = "MDAACaFgw...xFgy=", value = "B64 string representing the key binary")


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SecurityKeyType securityKeyType = (SecurityKeyType) o;
    return Objects.equals(this.keyType, securityKeyType.keyType) &&
        Objects.equals(this.usage, securityKeyType.usage) &&
        Objects.equals(this.key, securityKeyType.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyType, usage, key);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SecurityKeyType {\n");
    
    sb.append("    keyType: ").append(toIndentedString(keyType)).append("\n");
    sb.append("    usage: ").append(toIndentedString(usage)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
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

