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

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets apiClassEnum
 */
public enum ApiClassEnum {
  
  SM("SM"), // Session Manager
  
  CM("CM"), // Metadata - Configuration Manager
  
  CL("CL"), // API Gateway Client
  
  SPCL("SPCL"), // API Gateway Service Provider
  
  RM("RM"), // Request Manager
  
  SP("SP"), // SP Service
  
  AS("AS"), // Authentication Source
  
  IS("IS"), // Identity Source
  
  PER("PER"), // Persistence
  
  IDBOOT("IDBOOT"), // IDBootstrapping
  
  LINK("LINK"), // IDLinking
  
  LINKAPP ("LINKAPP"), // API Gateway Link
  
  REVOKED("REVOKED"); // Revocation
  
  private String value;

  ApiClassEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ApiClassEnum fromValue(String text) {
    for (ApiClassEnum b : ApiClassEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

