/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.seal.apigw.cl.domain;

import java.util.Map;

/**
 *
 * @author nikos
 */
public class MngrSessionTO {

    private String sessionId;
    private Map sessionVariables;

    public MngrSessionTO(String sessionId, Map sessionVariables) {
        this.sessionId = sessionId;
        this.sessionVariables = sessionVariables;
    }

    public MngrSessionTO() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map getSessionVariables() {
        return sessionVariables;
    }

    public void setSessionVariables(Map sessionVariables) {
        this.sessionVariables = sessionVariables;
    }

}
