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

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import eu.seal.apigw.cl.domain.ModuleTrigger;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Api(value = "cl", description = "the cl API")
public interface IdentLnkApi {


    @ApiOperation(value = "Request to cancel a reconciliation request on a specific method module.", nickname = "clIdentLinkingModuleIDRequestIdCancelGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/linking/{moduleID}/{requestId}/cancel",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdCancelGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId);


    @ApiOperation(value = "Upload a supporting file to a specific method module.", nickname = "clIdentLinkingModuleIDRequestIdFilesUploadPost", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/linking/{moduleID}/{requestId}/files/upload",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdFilesUploadPost(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId,@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file);


    @ApiOperation(value = "Receive messages from a validation officer on a specific method module (on the response payload as a conversation object).", nickname = "clIdentLinkingModuleIDRequestIdMessagesReceiveGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/linking/{moduleID}/{requestId}/messages/receive",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdMessagesReceiveGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId);


    @ApiOperation(value = "Send a message to a validation officer on a specific method module.", nickname = "clIdentLinkingModuleIDRequestIdMessagesSendPost", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/linking/{moduleID}/{requestId}/messages/send",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdMessagesSendPost(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId,@ApiParam(value = "", required=true) @RequestParam(value="message", required=true)  String message);


    @ApiOperation(value = "Fetch the result of a reconciliation request to a specific method module.", nickname = "clIdentLinkingModuleIDRequestIdResultGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/linking/{moduleID}/{requestId}/result",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdResultGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId);


    @ApiOperation(value = "Request the status of a reconciliation request to a specific method module.", nickname = "clIdentLinkingModuleIDRequestIdStatusGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/linking/{moduleID}/{requestId}/status",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestIdStatusGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "",required=true) @PathVariable("requestId") String requestId);


    @ApiOperation(value = "Request two data sets to be reconciled through a specific method module.", nickname = "clIdentLinkingModuleIDRequestPost", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/linking/{moduleID}/request",
        produces = { "application/json" }, 
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<ModuleTrigger> clIdentLinkingModuleIDRequestPost(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "", required=true) @RequestParam(value="datasetIDa", required=true)  String datasetIDa,@ApiParam(value = "", required=true) @RequestParam(value="datasetIDb", required=true)  String datasetIDb);


}
