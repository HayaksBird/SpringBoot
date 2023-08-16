package com.Test.StudentApp.security.service.implementation;

import com.Test.StudentApp.security.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

/**
 * The implementation class of the jwt service.
 */
@Service
public class JwtService implements IJwtService {
    @Value("${application.security.jwt.secret-key}")
    private String PRIVATE_KEY;
    @Value("${application.security.jwt.expiration}")
    private long tokenLifespan;


    /**
     * Generate a token with extra claims.
     */
    @Override
    public <T> String generateToken(Map<String, Object> extraClaims,
                                Function<T, String> usernameExtractor,
                                T usernameContainer) {
        return buildToken(extraClaims, usernameExtractor, usernameContainer);
    }


    /**
     * Generate a token without extra claims.
     */
    @Override
    public <T> String generateToken(Function<T, String> usernameExtractor,
                                    T usernameContainer) {
        return buildToken(new HashMap<>(), usernameExtractor, usernameContainer);
    }


    /**
     * This method creates and returns the JWT based on the input params.
     */
    private <T> String buildToken(Map<String, Object> extraClaims,
                                  Function<T, String> usernameExtractor,
                                  T usernameContainer) {
        return Jwts.builder()
                .addClaims(extraClaims)
                .setSubject(usernameExtractor.apply(usernameContainer))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenLifespan))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }


    /*
    It utilizes the extractClaim(...) method and as an input it sends the token itself,
    and in addition it specifies that the function it wants to be executed is the
    getSubject(...) method.
     */
    /**
     * This method returns the username claim for the JWT.
     */
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    /**
     * This method returns the roles claim for the JWT.
     */
    @Override
    public List<? extends GrantedAuthority> extractRoles(String token) {
        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        List<String> rolesString = extractClaim(token, claims -> claims.get("roles", List.class));

        for (String role : rolesString) {
            roles.add(new SimpleGrantedAuthority(role));
        }

        return roles;
    }


    /*
    <T> shows that the method is generic.
    T represents the actual return type (which happens to be the data type of the generic param).

    Function<Claims, T> means the variable of this type references a function that takes as an input Claims
    and spits out T. The apply method executes the function itself. When calling the method we need to pass
    the function, so that this method will know what function exactly to execute.

    The convenience of this approach is that we can extract any claim regardless of its data type.

    NOTE: Though the function could be of any kind, the input and the output types must match those
    declared in the method signature.
     */
    /**
     * This method is responsible for extracting any claim from the JWT.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    /**
     * The Jwts first receives the private key in order
     * to perform the jwt check through its signature. Then it
     * receives the token itself. If it makes sure that the token is indeed not
     * corrupted, then it returns the claims of that token.
     *
     * NOTE: If the JWT was tampered, then the SignatureException is thrown.
     */
    private Claims extractAllClaims(String token) throws SignatureException {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * This method first gets the binary form of the private key,
     * and then inserts it into a Key object, so that it will
     * be compatible with the signing algorithm.
    */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(PRIVATE_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}