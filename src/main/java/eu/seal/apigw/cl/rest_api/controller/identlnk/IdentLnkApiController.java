/**
Copyright © 2020  Atos Spain SA. All rights reserved.
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

package eu.seal.apigw.cl.rest_api.controller.identlnk;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.rest_api.services.identlnk.ClModuleIDRequestPostService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class IdentLnkApiController implements IdentLnkApi {

    private static final Logger log = LoggerFactory.getLogger(IdentLnkApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public IdentLnkApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
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

    @Autowired
	private ClModuleIDRequestPostService clModuleIDRequestPostService;

    public ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestPost(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "", required=true) @RequestParam(value="datasetIDa", required=true)  String datasetIDa,@ApiParam(value = "", required=true) @RequestParam(value="datasetIDb", required=true)  String datasetIDb) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	ModuleTrigger lnkRetrieved = new ModuleTrigger();
          	
	          	lnkRetrieved = clModuleIDRequestPostService.clModuleIDRequestPost(sessionID, moduleID, datasetIDa, datasetIDb);
	          	return new ResponseEntity<ModuleTrigger>(lnkRetrieved, HttpStatus.OK);
	          	
          } catch (Exception e) {
          	log.error(Constants.ERROR_ACCESSING_MODULE, e);
              return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_FOUND);
          }
      }

      return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }


}
