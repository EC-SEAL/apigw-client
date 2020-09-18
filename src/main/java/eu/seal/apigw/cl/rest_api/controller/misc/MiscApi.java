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

    
    @ApiOperation(value = "Now the callback address is on the client domain, the client will need some endpoint to validate the msToken the module will send along when calling the callback, to secure the app flow. The client will send the msToken to the api gw for validation through this call.", nickname = "clTokenValidateGet", notes = "_", tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "msToken is legitimate"),
        @ApiResponse(code = 403, message = "msToken is invalid"),
        @ApiResponse(code = 404, message = "Error validating token") })
    @RequestMapping(value = "/cl/token/validate",
        method = RequestMethod.GET)
    ResponseEntity<Void> clTokenValidateGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@NotNull @ApiParam(value = "B64 string with the received msToken", required = true) @Valid @RequestParam(value = "msToken", required = true) String msToken);


    @ApiOperation(value = "Generate a derived identity through a specific method module.", nickname = "clIdentDerivationModuleIDGenerateGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/derivation/{moduleID}/generate",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clIdentDerivationModuleIDGenerateGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID);


    
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
    @RequestMapping(value = "/cl/vc/issuing/{SSIId}/generate",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clVcIssuingModuleIDGenerateGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("SSIId") String SSIId);

}
