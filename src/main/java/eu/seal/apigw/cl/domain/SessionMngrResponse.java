/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.seal.apigw.cl.domain;

import eu.seal.apigw.cl.domain.ResponseCode;
import eu.seal.apigw.cl.domain.MngrSessionTO;

/**
 *
 * @author nikos
 */
public class SessionMngrResponse {

    private ResponseCode code;
    private MngrSessionTO sessionData;
    private String additionalData ;
    private String error;

    public SessionMngrResponse(ResponseCode code, MngrSessionTO sessionData, String extraData,String error) {
        this.code = code;
        this.sessionData = sessionData;
        this.additionalData = extraData;
        this.error = error;
    }

    public SessionMngrResponse() {
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public MngrSessionTO getSessionData() {
        return sessionData;
    }

    public void setSessionData(MngrSessionTO sessionData) {
        this.sessionData = sessionData;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    
    
    
}
