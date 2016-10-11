package com.recipe.roulette.app.model.reddit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by varsovski on 27-Jul-15.
 */
public class TokenResponse {

    private String access_token;
    private String token_type;
    private String expires_in;
    private String scope;
    private long tokenTime;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    private String error;
    private String error_description;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The accessToken
     */
    public String getAccessToken() {
        return access_token;
    }

    /**
     *
     * @param accessToken
     * The access_token
     */
    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    /**
     *
     * @return
     * The tokenType
     */
    public String getTokenType() {
        return token_type;
    }

    /**
     *
     * @param tokenType
     * The token_type
     */
    public void setTokenType(String tokenType) {
        this.token_type = tokenType;
    }

    /**
     *
     * @return
     * The expiresIn
     */
    public String getExpiresIn() {
        return expires_in;
    }

    /**
     *
     * @param expiresIn
     * The expires_in
     */
    public void setExpiresIn(String expiresIn) {
        this.expires_in = expiresIn;
    }

    /**
     *
     * @return
     * The scope
     */
    public String getScope() {
        return scope;
    }

    /**
     *
     * @param scope
     * The scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public long getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(long tokenTime) {
        this.tokenTime = tokenTime;
    }
}
