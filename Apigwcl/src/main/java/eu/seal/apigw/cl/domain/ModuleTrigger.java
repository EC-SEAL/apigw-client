package eu.seal.apigw.cl.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import eu.seal.apigw.cl.domain.ModuleTriggerAccess;
import eu.seal.apigw.cl.domain.ModuleTriggerStatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Response object of the API GW. Returns data, status and commands how to deal the loading of a specific module
 */
@ApiModel(description = "Response object of the API GW. Returns data, status and commands how to deal the loading of a specific module")
@Validated
@javax.annotation.Generated(value = "eu.seal.apigw.cl.codegen.languages.SpringCodegen", date = "2019-11-29T11:06:02.261Z")

public class ModuleTrigger   {
  @JsonProperty("payload")
  private Object payload = null;

  @JsonProperty("status")
  private ModuleTriggerStatus status = null;

  @JsonProperty("access")
  private ModuleTriggerAccess access = null;

  public ModuleTrigger payload(Object payload) {
    this.payload = payload;
    return this;
  }

  /**
   * Response data. Any object format
   * @return payload
  **/
  @ApiModelProperty(value = "Response data. Any object format")


  public Object getPayload() {
    return payload;
  }

  public void setPayload(Object payload) {
    this.payload = payload;
  }

  public ModuleTrigger status(ModuleTriggerStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ModuleTriggerStatus getStatus() {
    return status;
  }

  public void setStatus(ModuleTriggerStatus status) {
    this.status = status;
  }

  public ModuleTrigger access(ModuleTriggerAccess access) {
    this.access = access;
    return this;
  }

  /**
   * Get access
   * @return access
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ModuleTriggerAccess getAccess() {
    return access;
  }

  public void setAccess(ModuleTriggerAccess access) {
    this.access = access;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModuleTrigger moduleTrigger = (ModuleTrigger) o;
    return Objects.equals(this.payload, moduleTrigger.payload) &&
        Objects.equals(this.status, moduleTrigger.status) &&
        Objects.equals(this.access, moduleTrigger.access);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payload, status, access);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModuleTrigger {\n");
    
    sb.append("    payload: ").append(toIndentedString(payload)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    access: ").append(toIndentedString(access)).append("\n");
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

