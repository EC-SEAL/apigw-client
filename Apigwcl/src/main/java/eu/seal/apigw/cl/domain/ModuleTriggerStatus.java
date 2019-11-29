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
 * Status of the response
 */
@ApiModel(description = "Status of the response")
@Validated
@javax.annotation.Generated(value = "eu.seal.apigw.cl.codegen.languages.SpringCodegen", date = "2019-11-29T11:06:02.261Z")

public class ModuleTriggerStatus   {
  @JsonProperty("message")
  private String message = null;

  @JsonProperty("mainCode")
  private String mainCode = null;

  @JsonProperty("secondaryCode")
  private String secondaryCode = null;

  public ModuleTriggerStatus message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(value = "")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ModuleTriggerStatus mainCode(String mainCode) {
    this.mainCode = mainCode;
    return this;
  }

  /**
   * Get mainCode
   * @return mainCode
  **/
  @ApiModelProperty(value = "")


  public String getMainCode() {
    return mainCode;
  }

  public void setMainCode(String mainCode) {
    this.mainCode = mainCode;
  }

  public ModuleTriggerStatus secondaryCode(String secondaryCode) {
    this.secondaryCode = secondaryCode;
    return this;
  }

  /**
   * Get secondaryCode
   * @return secondaryCode
  **/
  @ApiModelProperty(value = "")


  public String getSecondaryCode() {
    return secondaryCode;
  }

  public void setSecondaryCode(String secondaryCode) {
    this.secondaryCode = secondaryCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModuleTriggerStatus moduleTriggerStatus = (ModuleTriggerStatus) o;
    return Objects.equals(this.message, moduleTriggerStatus.message) &&
        Objects.equals(this.mainCode, moduleTriggerStatus.mainCode) &&
        Objects.equals(this.secondaryCode, moduleTriggerStatus.secondaryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, mainCode, secondaryCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModuleTriggerStatus {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    mainCode: ").append(toIndentedString(mainCode)).append("\n");
    sb.append("    secondaryCode: ").append(toIndentedString(secondaryCode)).append("\n");
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

