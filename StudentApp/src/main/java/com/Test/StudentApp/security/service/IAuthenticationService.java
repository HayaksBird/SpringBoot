package com.Test.StudentApp.security.service;

import com.Test.StudentApp.entity.User;
import com.Test.StudentApp.security.dto.AuthenticationRequest;
import com.Test.StudentApp.security.dto.AuthenticationResponse;

/**
 * The class implementing this interface is responsible for both registering
 * and authenticating a user. In both cases it returns a JWT to the user.
 */
public interface IAuthenticationService {
    /**
     * This method registers a new user.
     * After the user is registered it creates and returns a JWT.
     */
    AuthenticationResponse register(User user);

    /**
     * This method authenticates an already existing user.
     * After the successful authentication it generates and returns a JWT.
     */
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
