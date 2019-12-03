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
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.validation.annotation.Validated;

import eu.seal.apigw.cl.domain.EntityMetadata;



/**
 * EntityMetadataList
 */
@Validated
@javax.annotation.Generated(value = "eu.seal.cm.codegen.languages.SpringCodegen", date = "2018-12-10T12:53:06.421Z")

public class EntityMetadataList extends ArrayList<EntityMetadata>  {

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntityMetadataList {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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
  
//Get the entity with a given entityId
 public EntityMetadata getEntityById (String entityId) {
	  
	 EntityMetadata theEntity = null;
	  
	 EntityMetadata anEntity;
	 Iterator<EntityMetadata> entityMetadataIterator = this.iterator();
	 while (entityMetadataIterator.hasNext()) {
		 anEntity = entityMetadataIterator.next();
		  if (anEntity.getEntityId().equals(entityId)) {
				  theEntity = anEntity;
			  	  break;
		  }	  
	  }
	 
	  return theEntity;
 }
}

