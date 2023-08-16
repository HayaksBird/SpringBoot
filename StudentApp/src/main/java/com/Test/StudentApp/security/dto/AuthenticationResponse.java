package com.Test.StudentApp.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class will contain the JWT thrown back to the user after
 * a successful authentication.
 */
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;


    //CONSTRUCTORS
    public AuthenticationResponse() {}


    public AuthenticationResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }


    public AuthenticationResponse refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
