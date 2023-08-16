package com.Test.StudentApp.security;

import com.Test.StudentApp.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class has a purpose of defining custom authentication
 * components, which will be used by filters in order to
 * correctly manage the database data.
 */
@Configuration
public class AuthenticationComponentsConfig {
    private final IUserService userService;


    //CONSTRUCTORS
    @Autowired
    public AuthenticationComponentsConfig(IUserService userService) {
        this.userService = userService;
    }


    /**
     * Creates a bean out of UserDetailsService.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            return userService.getUser(username);
        };
    }


    /**
     * Returns the default AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    /**
     * Specify the encoding type for the AuthenticationProvider method to use.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * The AuthenticationProvider is responsible for performing the actual authentication
     * process and validating the user's credentials. It contains the logic to check the
     * provided credentials against the user details obtained from a UserDetailsService.
     *
     * Here we specify that the password in the database is encoded, and we provide
     * the encoding type in order for it to perform a compare.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
