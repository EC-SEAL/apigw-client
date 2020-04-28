package eu.seal.apigw.cl.domain;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Represents a clear, encrypted and/or signed data set.
 */
@ApiModel(description = "Represents a clear, encrypted and/or signed data set.")
@Validated

public class DataStore   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("encryptedData")
  private String encryptedData = null;

  @JsonProperty("signature")
  private String signature = null;

  @JsonProperty("signatureAlgorithm")
  private String signatureAlgorithm = null;

  @JsonProperty("encryptionAlgorithm")
  private String encryptionAlgorithm = null;

  @JsonProperty("clearData")
  @Valid
  private List<DataSet> clearData = null;

  public DataStore id(String id) {
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

  public DataStore encryptedData(String encryptedData) {
    this.encryptedData = encryptedData;
    return this;
  }

  /**
   * If the data store is encrypted, this will be set. B64 string
   * @return encryptedData
  **/
  @ApiModelProperty(value = "If the data store is encrypted, this will be set. B64 string")


  public String getEncryptedData() {
    return encryptedData;
  }

  public void setEncryptedData(String encryptedData) {
    this.encryptedData = encryptedData;
  }

  public DataStore signature(String signature) {
    this.signature = signature;
    return this;
  }

  /**
   * If the data store is signed, signature goes here. B64 string. Sign always the decrypted dataset.
   * @return signature
  **/
  @ApiModelProperty(value = "If the data store is signed, signature goes here. B64 string. Sign always the decrypted dataset.")


  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public DataStore signatureAlgorithm(String signatureAlgorithm) {
    this.signatureAlgorithm = signatureAlgorithm;
    return this;
  }

  /**
   * Descriptor of the signature algorithm used.
   * @return signatureAlgorithm
  **/
  @ApiModelProperty(value = "Descriptor of the signature algorithm used.")


  public String getSignatureAlgorithm() {
    return signatureAlgorithm;
  }

  public void setSignatureAlgorithm(String signatureAlgorithm) {
    this.signatureAlgorithm = signatureAlgorithm;
  }

  public DataStore encryptionAlgorithm(String encryptionAlgorithm) {
    this.encryptionAlgorithm = encryptionAlgorithm;
    return this;
  }

  /**
   * Descriptor of the encryption algorithm used.
   * @return encryptionAlgorithm
  **/
  @ApiModelProperty(value = "Descriptor of the encryption algorithm used.")


  public String getEncryptionAlgorithm() {
    return encryptionAlgorithm;
  }

  public void setEncryptionAlgorithm(String encryptionAlgorithm) {
    this.encryptionAlgorithm = encryptionAlgorithm;
  }

  public DataStore clearData(List<DataSet> clearData) {
    this.clearData = clearData;
    return this;
  }

  public DataStore addClearDataItem(DataSet clearDataItem) {
    if (this.clearData == null) {
      this.clearData = new ArrayList<DataSet>();
    }
    this.clearData.add(clearDataItem);
    return this;
  }

  /**
   * If the data store is in cleartext, this will be set
   * @return clearData
  **/
  @ApiModelProperty(value = "If the data store is in cleartext, this will be set")

  @Valid

  public List<DataSet> getClearData() {
    return clearData;
  }

  public void setClearData(List<DataSet> clearData) {
    this.clearData = clearData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataStore dataStore = (DataStore) o;
    return Objects.equals(this.id, dataStore.id) &&
        Objects.equals(this.encryptedData, dataStore.encryptedData) &&
        Objects.equals(this.signature, dataStore.signature) &&
        Objects.equals(this.signatureAlgorithm, dataStore.signatureAlgorithm) &&
        Objects.equals(this.encryptionAlgorithm, dataStore.encryptionAlgorithm) &&
        Objects.equals(this.clearData, dataStore.clearData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, encryptedData, signature, signatureAlgorithm, encryptionAlgorithm, clearData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataStore {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    encryptedData: ").append(toIndentedString(encryptedData)).append("\n");
    sb.append("    signature: ").append(toIndentedString(signature)).append("\n");
    sb.append("    signatureAlgorithm: ").append(toIndentedString(signatureAlgorithm)).append("\n");
    sb.append("    encryptionAlgorithm: ").append(toIndentedString(encryptionAlgorithm)).append("\n");
    sb.append("    clearData: ").append(toIndentedString(clearData)).append("\n");
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