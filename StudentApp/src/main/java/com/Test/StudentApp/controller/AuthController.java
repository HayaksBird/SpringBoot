package com.Test.StudentApp.controller;

import com.Test.StudentApp.entity.User;
import com.Test.StudentApp.security.dto.AuthenticationRequest;
import com.Test.StudentApp.security.dto.AuthenticationResponse;
import com.Test.StudentApp.security.service.IAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller deals with authentication requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IAuthenticationService authenticationService;


    //CONSTRUCTORS
    public AuthController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    /**
     * Logo
     */
    @GetMapping("")
    public ResponseEntity<String> registerLogo() {
        return new ResponseEntity<>("Authorization", HttpStatus.OK);
    }


    /**
     * Allows to register users by the JSON object they have provided.
     * Returns a JWT.
     */
    @PostMapping ("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) {
        return new ResponseEntity<>(authenticationService.register(user), HttpStatus.CREATED);
    }


    /**
     * Allows to authenticate users by the JSON object they have provided.
     * Returns a JWT.
     */
    @PostMapping ("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }
}
