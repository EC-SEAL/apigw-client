package eu.seal.apigw.cl.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Contents of an attribute item.
 */
@ApiModel(description = "Contents of an attribute item.")
@Validated

public class AttributeType   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("friendlyName")
  private String friendlyName = null;

  @JsonProperty("encoding")
  private String encoding = null;

  @JsonProperty("language")
  private String language = null;

  @JsonProperty("isMandatory")
  private Boolean isMandatory = null;

  @JsonProperty("values")
  @Valid
  private List<String> values = null;

  public AttributeType name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Unique class identifier of the attribute
   * @return name
  **/
  @ApiModelProperty(example = "http://eidas.europa.eu/attributes/naturalperson/CurrentGivenName", value = "Unique class identifier of the attribute")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AttributeType friendlyName(String friendlyName) {
    this.friendlyName = friendlyName;
    return this;
  }

  /**
   * Class identifier of the attribute (short version, might have collisions)
   * @return friendlyName
  **/
  @ApiModelProperty(example = "CurrentGivenName", value = "Class identifier of the attribute (short version, might have collisions)")


  public String getFriendlyName() {
    return friendlyName;
  }

  public void setFriendlyName(String friendlyName) {
    this.friendlyName = friendlyName;
  }

  public AttributeType encoding(String encoding) {
    this.encoding = encoding;
    return this;
  }

  /**
   * String identifying the encoding method:\"UTF-8\" , \"BASE64\", etc.
   * @return encoding
  **/
  @ApiModelProperty(example = "plain", value = "String identifying the encoding method:\"UTF-8\" , \"BASE64\", etc.")


  public String getEncoding() {
    return encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public AttributeType language(String language) {
    this.language = language;
    return this;
  }

  /**
   * String identifying the language code of the value contents
   * @return language
  **/
  @ApiModelProperty(example = "ES_es", value = "String identifying the language code of the value contents")


  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public AttributeType isMandatory(Boolean isMandatory) {
    this.isMandatory = isMandatory;
    return this;
  }

  /**
   * To mark the mandatoriness of the attribute.
   * @return isMandatory
  **/
  @ApiModelProperty(example = "true", value = "To mark the mandatoriness of the attribute.")


  public Boolean isIsMandatory() {
    return isMandatory;
  }

  public void setIsMandatory(Boolean isMandatory) {
    this.isMandatory = isMandatory;
  }

  public AttributeType values(List<String> values) {
    this.values = values;
    return this;
  }

  public AttributeType addValuesItem(String valuesItem) {
    if (this.values == null) {
      this.values = new ArrayList<String>();
    }
    this.values.add(valuesItem);
    return this;
  }

  /**
   * List of values for the attribute
   * @return values
  **/
  @ApiModelProperty(example = "[\"JOHN\"]", value = "List of values for the attribute")


  public List<String> getValues() {
    return values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttributeType attributeType = (AttributeType) o;
    return Objects.equals(this.name, attributeType.name) &&
        Objects.equals(this.friendlyName, attributeType.friendlyName) &&
        Objects.equals(this.encoding, attributeType.encoding) &&
        Objects.equals(this.language, attributeType.language) &&
        Objects.equals(this.isMandatory, attributeType.isMandatory) &&
        Objects.equals(this.values, attributeType.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, friendlyName, encoding, language, isMandatory, values);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttributeType {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    friendlyName: ").append(toIndentedString(friendlyName)).append("\n");
    sb.append("    encoding: ").append(toIndentedString(encoding)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    isMandatory: ").append(toIndentedString(isMandatory)).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
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

