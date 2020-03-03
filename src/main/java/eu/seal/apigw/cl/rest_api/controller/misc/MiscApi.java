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

import org.springframework.core.io.Resource;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import eu.seal.apigw.cl.domain.DataSet;
import eu.seal.apigw.cl.domain.DataStore;
import eu.seal.apigw.cl.domain.DisplayableList;
import eu.seal.apigw.cl.domain.ModuleTrigger;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Api(value = "cl", description = "the cl API")
public interface MiscApi {



    @ApiOperation(value = "Set in Session the Return page in the client when invoking modules. This URL under the control of the client will allow it to retake control of the flow. ** Client must expect a msToken on the callback url **", nickname = "clCallbackGet", notes = "_", tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Callback registered"),
        @ApiResponse(code = 404, message = "Error registering callback") })
    @RequestMapping(value = "/cl/callback",
    	method = RequestMethod.GET)
    ResponseEntity<Void> clCallbackGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@NotNull @ApiParam(value = "the actual callback url the modules will call when returning control to the client", required = true) @Valid @RequestParam(value = "ClientCallbackAddr", required = true) String clientCallbackAddr);


    @ApiOperation(value = "Generate a derived identity through a specific method module.", nickname = "clIdentDerivationModuleIDGenerateGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/derivation/{moduleID}/generate",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clIdentDerivationModuleIDGenerateGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID);


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


    @ApiOperation(value = "Delete a retrieved or derived identity already on the session store through the specific method module used to obtain it.", nickname = "clIdentMgrDatasetIDDeleteGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/mgr/{datasetID}/delete",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clIdentMgrDatasetIDDeleteGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("datasetID") String datasetID);


    @ApiOperation(value = "Update a retrieved or derived identity already on the session store through the specific method module used to obtain it.", nickname = "clIdentMgrDatasetIDRefreshGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/mgr/{datasetID}/refresh",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clIdentMgrDatasetIDRefreshGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("datasetID") String datasetID);


    @ApiOperation(value = "Revoke a retrieved or derived identity already on the session store through the specific method module used to obtain it.", nickname = "clIdentMgrDatasetIDRevokeGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/mgr/{datasetID}/revoke",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clIdentMgrDatasetIDRevokeGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("datasetID") String datasetID);


    @ApiOperation(value = "Get the list of user identity data sets currently    loaded or fetched in session.", nickname = "clIdentMgrListGet", notes = "_", response = DataStore.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Incoming list", response = DataStore.class),
        @ApiResponse(code = 404, message = "Collection not found") })
    @RequestMapping(value = "/cl/ident/mgr/list",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<DataStore> clIdentMgrListGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID);

    
    @ApiOperation(value = "Get a collection of elements to be displayed on the selector widget.", nickname = "clListCollectionGet", notes = "_", response = DisplayableList.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Incoming list", response = DisplayableList.class),
        @ApiResponse(code = 404, message = "Collection not found") })
    @RequestMapping(value = "/cl/list/{collection}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<DisplayableList> clListCollectionGet(@ApiParam(value = "",required=true) @PathVariable("collection") String collection);


    @ApiOperation(value = "Generate a verifiable claim through a specific method module.", nickname = "clVcIssuingModuleIDGenerateGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/vc/issuing/{moduleID}/generate",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clVcIssuingModuleIDGenerateGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID);

}
