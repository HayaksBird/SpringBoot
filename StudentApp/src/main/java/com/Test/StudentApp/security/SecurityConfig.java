package com.Test.StudentApp.security;

import com.Test.StudentApp.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
/**
 * This class provides the security configuration.
 */
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;


    //CONSTRUCTORS
    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter,
                          AuthenticationProvider authenticationProvider
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }


    /**
     * This method is responsible for securing the endpoints with the role requirements.
     * In addition to that, it embeds the custom JWT filter to the chain for the authentication.
     */
    /*
    This method is called before the filters are executed and its primary role is to manage
    these filters before they are processed.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configurer ->
                        configurer
                            //Regarding authentication
                            .requestMatchers("/auth").permitAll()
                            .requestMatchers("/auth/**").permitAll()

                            //Regarding students
                            .requestMatchers(HttpMethod.GET, "/students").hasRole("STUDENT")
                            .requestMatchers(HttpMethod.GET, "/students/**").hasRole("STUDENT")
                            .requestMatchers(HttpMethod.POST, "/students").hasRole("TEACHER")
                            .requestMatchers(HttpMethod.PUT, "/students").hasRole("TEACHER")
                            .requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("PRINCIPAL")
        );

        http.csrf(csrf -> csrf.disable());

        /*
        Make sure that the JWT filter is processed before the UsernamePasswordAuthenticationFilter.
        Since we handle the authentication ourselves, we want this custom filter to be invoked before
        other authentication filters, so that after the SecurityContextHolder's Authentication object
        is initialized all the other authentication filters are skipped.
         */
        return  http.authenticationProvider(authenticationProvider) //Add additional authentication provider
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
    }
}
