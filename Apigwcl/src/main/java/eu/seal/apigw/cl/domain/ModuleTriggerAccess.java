package eu.seal.apigw.cl.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * If the response commands the client to access elsewhere, this field will tell where and how
 */
@ApiModel(description = "If the response commands the client to access elsewhere, this field will tell where and how")
@Validated

public class ModuleTriggerAccess   {
  /**
   * Mechanism to access the delegated resource
   */
  public enum BindingEnum {
    POST_REDIRECT("HTTP-POST-REDIRECT"),
    
    GET_REDIRECT("HTTP-GET-REDIRECT"),
    
    GET("HTTP-GET"),
    
    POST("HTTP-POST"),
    
    GET_SIG("HTTP-GET-SIG"),
    
    POST_SIG("HTTP-POST-SIG");

    private String value;

    BindingEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static BindingEnum fromValue(String text) {
      for (BindingEnum b : BindingEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("binding")
  private BindingEnum binding = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("contentType")
  private String contentType = null;

  @JsonProperty("bodyContent")
  private String bodyContent = null;

  public ModuleTriggerAccess binding(BindingEnum binding) {
    this.binding = binding;
    return this;
  }

  /**
   * Mechanism to access the delegated resource
   * @return binding
  **/
  @ApiModelProperty(value = "Mechanism to access the delegated resource")


  public BindingEnum getBinding() {
    return binding;
  }

  public void setBinding(BindingEnum binding) {
    this.binding = binding;
  }

  public ModuleTriggerAccess address(String address) {
    this.address = address;
    return this;
  }

  /**
   * the URL of the resource
   * @return address
  **/
  @ApiModelProperty(value = "the URL of the resource")


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public ModuleTriggerAccess contentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  /**
   * the MIME type of the body, if any
   * @return contentType
  **/
  @ApiModelProperty(value = "the MIME type of the body, if any")


  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public ModuleTriggerAccess bodyContent(String bodyContent) {
    this.bodyContent = bodyContent;
    return this;
  }

  /**
   * If the access method requires to transfer data on the body of the request, it will be written here
   * @return bodyContent
  **/
  @ApiModelProperty(value = "If the access method requires to transfer data on the body of the request, it will be written here")


  public String getBodyContent() {
    return bodyContent;
  }

  public void setBodyContent(String bodyContent) {
    this.bodyContent = bodyContent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModuleTriggerAccess moduleTriggerAccess = (ModuleTriggerAccess) o;
    return Objects.equals(this.binding, moduleTriggerAccess.binding) &&
        Objects.equals(this.address, moduleTriggerAccess.address) &&
        Objects.equals(this.contentType, moduleTriggerAccess.contentType) &&
        Objects.equals(this.bodyContent, moduleTriggerAccess.bodyContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(binding, address, contentType, bodyContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModuleTriggerAccess {\n");
    
    sb.append("    binding: ").append(toIndentedString(binding)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    contentType: ").append(toIndentedString(contentType)).append("\n");
    sb.append("    bodyContent: ").append(toIndentedString(bodyContent)).append("\n");
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

