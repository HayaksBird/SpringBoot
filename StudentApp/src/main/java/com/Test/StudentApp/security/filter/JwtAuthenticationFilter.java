package com.Test.StudentApp.security.filter;

import com.Test.StudentApp.entity.User;
import com.Test.StudentApp.security.service.IJwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This class is a JWT filter.
 *
 * It is responsible for authenticating the user by making sure
 * that the JWT is valid. If the JWT is indeed valid, then it updates the
 * SecurityContextHolder and calls the next filter in the chain.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final IJwtService jwtService;


    //CONSTRUCTORS
    @Autowired
    public JwtAuthenticationFilter(IJwtService jwtService) {
        this.jwtService = jwtService;
        //this.userDetailsService = userDetailsService;
    }


    /**
     * This is the filter method itself.
     * It validates the JWT, extracts the user & his roles and initializes the
     * SecurityContextHolder by providing the Authentication object.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request , //http request object
                                    @NonNull HttpServletResponse response, //http response object
                                    @NonNull FilterChain filterChain) //a chain of filters
                                    throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String jwt;
        String username;
        User userDetails;

        /*
        If the header lacks the JWT, then there is no point in any further processing.
        Thus, we send it further in the chain.
         */
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        /*
        Extract the subject claim from the token.

        NOTE: While extracting the username it validates the token completely:
        the signature consistency and expiration date.
        So if there is any mismatch, it will throw an exception.
         */
        username = jwtService.extractUsername(jwt);

        /*
        Proceed only if the subject exists and the Authentication object of the SecurityContextHolder
        is empty.
         */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            userDetails = new User();
            userDetails.setUsername(username);

            //Create the Authentication object.
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    jwtService.extractRoles(jwt)
            );

            /*
            Add the Authentication to the SecurityContextHolder.

            NOTE: At this point other authentication filters will not be triggered, because
            the user is considered to be authenticated. In addition to that, now FilterSecurityInterceptor
            will be able to view current user's authorities.
             */
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        //Call the next filter in the chain.
        filterChain.doFilter(request, response);
    }
}