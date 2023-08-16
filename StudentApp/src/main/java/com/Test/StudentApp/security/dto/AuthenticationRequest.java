package com.Test.StudentApp.security.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class encapsulates the data from the request body of
 * an authentication request.
 */
public class AuthenticationRequest {
    private final String username, password;


    //CONSTRUCTORS
    @JsonCreator
    public AuthenticationRequest(@JsonProperty("username")String username,
                                 @JsonProperty("password")String password) {

        this.username = username;
        this.password = password;
    }


    //Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
