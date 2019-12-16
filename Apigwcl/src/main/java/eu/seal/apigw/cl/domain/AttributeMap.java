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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represent relatioonships between attributes in two attribute profiles. i.e. eduPerson.givenName &lt;-&gt; eIDAS
 */
@ApiModel(description = "Represent relatioonships between attributes in two attribute profiles. i.e. eduPerson.givenName <-> eIDAS")
@Validated

public class AttributeMap   {
  @JsonProperty("keyProfile")
  private String keyProfile = null;

  @JsonProperty("valueProfile")
  private String valueProfile = null;

  @JsonProperty("mappings")
  @Valid
  private Map<String, List<String>> mappings = null;

  public AttributeMap keyProfile(String keyProfile) {
    this.keyProfile = keyProfile;
    return this;
  }

  /**
   * Name of the attribute profile specified in the keys
   * @return keyProfile
  **/
  @ApiModelProperty(example = "eIDAS", value = "Name of the attribute profile specified in the keys")


  public String getKeyProfile() {
    return keyProfile;
  }

  public void setKeyProfile(String keyProfile) {
    this.keyProfile = keyProfile;
  }

  public AttributeMap valueProfile(String valueProfile) {
    this.valueProfile = valueProfile;
    return this;
  }

  /**
   * Name of the attribute profile specified in the values
   * @return valueProfile
  **/
  @ApiModelProperty(example = "eduPerson", value = "Name of the attribute profile specified in the values")


  public String getValueProfile() {
    return valueProfile;
  }

  public void setValueProfile(String valueProfile) {
    this.valueProfile = valueProfile;
  }

  public AttributeMap mappings(Map<String, List<String>> mappings) {
    this.mappings = mappings;
    return this;
  }

  public AttributeMap putMappingsItem(String key, List<String> mappingsItem) {
    if (this.mappings == null) {
      this.mappings = new HashMap<String, List<String>>();
    }
    this.mappings.put(key, mappingsItem);
    return this;
  }

  /**
   * Get mappings
   * @return mappings
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Map<String, List<String>> getMappings() {
    return mappings;
  }

  public void setMappings(Map<String, List<String>> mappings) {
    this.mappings = mappings;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttributeMap attributeMap = (AttributeMap) o;
    return Objects.equals(this.keyProfile, attributeMap.keyProfile) &&
        Objects.equals(this.valueProfile, attributeMap.valueProfile) &&
        Objects.equals(this.mappings, attributeMap.mappings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyProfile, valueProfile, mappings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttributeMap {\n");
    
    sb.append("    keyProfile: ").append(toIndentedString(keyProfile)).append("\n");
    sb.append("    valueProfile: ").append(toIndentedString(valueProfile)).append("\n");
    sb.append("    mappings: ").append(toIndentedString(mappings)).append("\n");
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

