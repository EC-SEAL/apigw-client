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

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eu.seal.apigw.cl.domain.DataSet;
import eu.seal.apigw.cl.domain.ModuleTrigger;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Api(value = "cl", description = "the cl API")
public interface IdentSourceApi {


    @ApiOperation(value = "Fetch user identity data on the session store with a specific identity method module.", nickname = "clIdentSourceModuleIDRetrieveGet", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/source/{moduleID}/retrieve",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ModuleTrigger> clIdentSourceModuleIDRetrieveGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID);

    @ApiOperation(value = "Load an identity dataset retrieved on a client-side module. Notice that in this case the client must be trusted, so be careful when integrating sources with this api.", nickname = "clIdentSourceModuleIDLoadPost", notes = "_", response = ModuleTrigger.class, tags={ "APIGatewayClient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Module access trigger", response = ModuleTrigger.class),
        @ApiResponse(code = 404, message = "Error accessing module") })
    @RequestMapping(value = "/cl/ident/source/{moduleID}/load",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = {RequestMethod.POST, RequestMethod.GET})
    ResponseEntity<ModuleTrigger> clIdentSourceModuleIDLoadPost(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "sessionID", required = true) String sessionID,@ApiParam(value = "",required=true) @PathVariable("moduleID") String moduleID,@ApiParam(value = "The data set to add" ,required=true )  @Valid @RequestBody DataSet dataset);


}
