/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.seal.apigw.cl.domain;


/**
 *
 * @author ATOS
 */
public class SignedDataSet {

    private String dataSetSerialised ;
    private String signature;

    public SignedDataSet(String dataSetSerialised,String signature) {
        this.dataSetSerialised = dataSetSerialised;
        this.signature = signature;
    }

    public SignedDataSet() {
    }

    
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDataSetSerialised() {
        return dataSetSerialised;
    }

    public void setDataSetSerialised(String dataSetSerialised) {
        this.dataSetSerialised = dataSetSerialised;
    }

    
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class SignedDataSet {\n");
      
      sb.append("    dataSetSerialised: ").append(toIndentedString(dataSetSerialised)).append("\n");
      sb.append("    signature: ").append(toIndentedString(signature)).append("\n");
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
