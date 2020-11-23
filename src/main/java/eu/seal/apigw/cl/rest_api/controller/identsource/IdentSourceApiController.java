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

package eu.seal.apigw.cl.rest_api.controller.identsource;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.seal.apigw.cl.domain.DataSet;
import eu.seal.apigw.cl.domain.ModuleTrigger;
import eu.seal.apigw.cl.rest_api.services.callback.ClCallbackGetService;
import eu.seal.apigw.cl.rest_api.services.identsource.ClModuleIDLoadPostService;
import eu.seal.apigw.cl.rest_api.services.identsource.ClModuleIDRetrieveGetService;
import eu.seal.apigw.cl.configuration.Constants;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class IdentSourceApiController implements IdentSourceApi {

    private static final Logger log = LoggerFactory.getLogger(IdentSourceApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public IdentSourceApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    @Autowired
	private ClModuleIDLoadPostService clModuleIDLoadPostService;
    
    public ResponseEntity<ModuleTrigger> clIdentSourceModuleIDLoadPost(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "The data set to add" ,required=true )  @Valid @RequestBody DataSet dataset) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            ModuleTrigger idLoaded = new ModuleTrigger();
            	
                try {
                	idLoaded = clModuleIDLoadPostService.clModuleIDLoadPost(sessionID, moduleID, dataset);
                    return new ResponseEntity<ModuleTrigger>(idLoaded, HttpStatus.OK);
                } catch (Exception e) {
                	log.error(Constants.ERROR_ACCESSING_MODULE, e);
                    return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_FOUND);
                }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    
    @Autowired
	private ClModuleIDRetrieveGetService clModuleIDRetrieveGetService;
    
    public ResponseEntity<ModuleTrigger> clIdentSourceModuleIDRetrieveGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID) {
        
    	String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
        	
        	ModuleTrigger idRetrieved = new ModuleTrigger();
    	
            try {
            	idRetrieved = clModuleIDRetrieveGetService.clModuleIDRetrieveGet(sessionID, moduleID);
                return new ResponseEntity<ModuleTrigger>(idRetrieved, HttpStatus.OK);
            } catch (Exception e) {
            	log.error(Constants.ERROR_ACCESSING_MODULE, e);
                return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<ModuleTrigger>(HttpStatus.NOT_IMPLEMENTED);
    }

}
