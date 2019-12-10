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

package eu.seal.apigw.cl.rest_api.controller.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.configuration.Constants;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.rest_api.services.session.ClSessionStartGetService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
public class SessionApiController implements SessionApi {

    private static final Logger log = LoggerFactory.getLogger(SessionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SessionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }



    public ResponseEntity<Void> clSessionEndGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Autowired
	private ClSessionStartGetService clSessionStartGetService;
    
    public ResponseEntity<ModuleTrigger> clSessionStartGet(@ApiParam(value = "") @Valid @RequestParam(value = "sessionID", required = false) String sessionID) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
        	ModuleTrigger session = new ModuleTrigger();
            try {
            	session = clSessionStartGetService.clSessionStartGet(sessionID);
            	return new ResponseEntity<ModuleTrigger>(session, HttpStatus.OK);
            }
            catch (Exception e) {
	        	log.error(Constants.COLLECTION_NOT_FOUND, e);
	    		return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

 

}
