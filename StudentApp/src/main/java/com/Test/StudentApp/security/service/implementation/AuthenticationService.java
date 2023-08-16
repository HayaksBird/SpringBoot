package com.Test.StudentApp.security.service.implementation;

import com.Test.StudentApp.entity.User;
import com.Test.StudentApp.security.dto.AuthenticationRequest;
import com.Test.StudentApp.security.dto.AuthenticationResponse;
import com.Test.StudentApp.security.service.IAuthenticationService;
import com.Test.StudentApp.security.service.IJwtService;
import com.Test.StudentApp.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The implementation class of the authentication service.
 */
@Service
public class AuthenticationService implements IAuthenticationService {
    private final IUserService userService;
    private final IJwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    //CONSTRUCTORS
    @Autowired
    public AuthenticationService(IUserService userService,
                                 IJwtService jwtService,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtService = jwtService;   //jwt service is used here only for token creation purposes.
    }


    /**
     * This method registers a new user.
     * After the user is registered it creates and returns a JWT.
     */
    @Override
    public AuthenticationResponse register(User user) {
        String roles;
        String jwt;
        Map<String, Object> extraClaims =  new HashMap<>();

        extraClaims.put("affiliation", "school");
        roles = user.getAuthorities().toString();
        extraClaims.put("roles", roles.substring(1, roles.length() - 1) .split(", "));

        user.setPassword(passwordEncoder.encode(user.getPassword()));   //encode in bcrypt.
        userService.addUser(user);
        jwt = jwtService.generateToken(extraClaims, User::getUsername, user);

        return new AuthenticationResponse().accessToken(jwt);
    }


    /**
     * This method authenticates an already existing user.
     * After the successful authentication it generates and returns a JWT.
     */
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String jwt, roles;
        Authentication authentication;
        Map<String, Object> extraClaims =  new HashMap<>();

        /*
        The authentication manager with the use of the authentication provider object will
        make sure that the provided credentials are correct, and it will return the Authentication object.
         */
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        roles = authentication.getAuthorities().toString();

        extraClaims.put("affiliation", "school");
        extraClaims.put("roles", roles.substring(1, roles.length() - 1) .split(", "));
        jwt = jwtService.generateToken(extraClaims,
                AuthenticationRequest::getUsername,
                request);


        return new AuthenticationResponse().accessToken(jwt);
    }
}
