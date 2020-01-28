/**
Copyright © 2019  Atos Spain SA. All rights reserved.
This file is part of SEAL API Gateway Client (SEAL Apigwcl).
SEAL Apigwcl is free software: you can redistribute it and/or modify it under the terms of EUPL 1.2.
THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT ANY WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT, 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
See README file for the full disclaimer information and LICENSE file for full license information in the project root.

@author Atos Research and Innovation, Atos SPAIN SA
*/

package eu.seal.apigw.cl.rest_api.controller.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.domain.DataSet;
import eu.seal.apigw.cl.domain.DataStore;
import eu.seal.apigw.cl.domain.DisplayableList;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.rest_api.services.callback.ClCallbackGetService;
import eu.seal.apigw.cl.rest_api.services.lists.ClListCollectionGetService;
import eu.seal.apigw.cl.configuration.Constants;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class MiscApiController implements MiscApi {

    private static final Logger log = LoggerFactory.getLogger(MiscApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public MiscApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    @Autowired
	private ClCallbackGetService clCallbackGetService;
    
    public ResponseEntity<Void> clCallbackGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID, Model model) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
        	
        	try {
            	clCallbackGetService.clCallbackGet (sessionID, model);
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
            catch (Exception e) {
	        	log.error(Constants.ERROR_RETURNING, e);
	    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentDerivationModuleIDGenerateGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdCancelGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdFilesUploadPost(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId,@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdMessagesReceiveGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdMessagesSendPost(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId,@ApiParam(value = "", required=true) @RequestParam(value="message", required=true)  String message) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdResultGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdStatusGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestPost(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "", required=true) @RequestParam(value="datasetIDa", required=true)  String datasetIDa,@ApiParam(value = "", required=true) @RequestParam(value="datasetIDb", required=true)  String datasetIDb) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentMgrDatasetIDDeleteGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("datasetID") String datasetID) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentMgrDatasetIDRefreshGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("datasetID") String datasetID) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentMgrDatasetIDRevokeGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("datasetID") String datasetID) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<DataStore> clIdentMgrListGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<DataStore>(objectMapper.readValue("{  \"signature\" : \"signature\",  \"encryptedData\" : \"encryptedData\",  \"encryptionAlgorithm\" : \"encryptionAlgorithm\",  \"signatureAlgorithm\" : \"signatureAlgorithm\",  \"clearData\" : [ {    \"issuerId\" : \"issuerId\",    \"expiration\" : \"2018-12-06T19:45:16Z\",    \"attributes\" : [ {      \"values\" : [ \"JOHN\" ],      \"name\" : \"http://eidas.europa.eu/attributes/naturalperson/CurrentGivenName\",      \"language\" : \"ES_es\",      \"encoding\" : \"plain\",      \"friendlyName\" : \"CurrentGivenName\",      \"isMandatory\" : true    }, {      \"values\" : [ \"JOHN\" ],      \"name\" : \"http://eidas.europa.eu/attributes/naturalperson/CurrentGivenName\",      \"language\" : \"ES_es\",      \"encoding\" : \"plain\",      \"friendlyName\" : \"CurrentGivenName\",      \"isMandatory\" : true    } ],    \"id\" : \"6c0f70a8-f32b-4535-b5f6-0d596c52813a\",    \"type\" : \"type\",    \"issued\" : \"2018-12-06T19:40:16Z\",    \"subjectId\" : \"subjectId\",    \"properties\" : {      \"key\" : \"properties\"    },    \"loa\" : \"loa\"  }, {    \"issuerId\" : \"issuerId\",    \"expiration\" : \"2018-12-06T19:45:16Z\",    \"attributes\" : [ {      \"values\" : [ \"JOHN\" ],      \"name\" : \"http://eidas.europa.eu/attributes/naturalperson/CurrentGivenName\",      \"language\" : \"ES_es\",      \"encoding\" : \"plain\",      \"friendlyName\" : \"CurrentGivenName\",      \"isMandatory\" : true    }, {      \"values\" : [ \"JOHN\" ],      \"name\" : \"http://eidas.europa.eu/attributes/naturalperson/CurrentGivenName\",      \"language\" : \"ES_es\",      \"encoding\" : \"plain\",      \"friendlyName\" : \"CurrentGivenName\",      \"isMandatory\" : true    } ],    \"id\" : \"6c0f70a8-f32b-4535-b5f6-0d596c52813a\",    \"type\" : \"type\",    \"issued\" : \"2018-12-06T19:40:16Z\",    \"subjectId\" : \"subjectId\",    \"properties\" : {      \"key\" : \"properties\"    },    \"loa\" : \"loa\"  } ]}", DataStore.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<DataStore>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<DataStore>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    public ResponseEntity<ModuleTrigger> clIdentSourceModuleIDLoadPost(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "The data set to add" ,required=true )  @Valid @RequestBody DataSet dataset) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ModuleTrigger> clIdentSourceModuleIDRetrieveGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Autowired
	private ClListCollectionGetService clListCollectionGetService;
    
    public ResponseEntity<DisplayableList> clListCollectionGet(@ApiParam(value = "",required=true) @PathVariable("collection") String collection) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
        	
        	DisplayableList displayableList = new DisplayableList();
            try {
            	displayableList = clListCollectionGetService.clListCollectionGet (collection);
                return new ResponseEntity<DisplayableList>(displayableList, HttpStatus.OK);
            }
            catch (Exception e) {
	        	log.error(Constants.COLLECTION_NOT_FOUND, e);
	    		return new ResponseEntity<DisplayableList>(HttpStatus.NOT_FOUND);
            }
            
        }

        return new ResponseEntity<DisplayableList>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<ModuleTrigger> clVcIssuingModuleIDGenerateGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ModuleTrigger>(objectMapper.readValue("{  \"access\" : {    \"address\" : \"address\",    \"binding\" : \"HTTP-POST-REDIRECT\",    \"bodyContent\" : \"bodyContent\",    \"contentType\" : \"contentType\"  },  \"payload\" : \"{}\",  \"status\" : {    \"mainCode\" : \"mainCode\",    \"secondaryCode\" : \"secondaryCode\",    \"message\" : \"message\"  }}", ModuleTrigger.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

}
