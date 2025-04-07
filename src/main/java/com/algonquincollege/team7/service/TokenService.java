package com.algonquincollege.team7.service;

import com.algonquincollege.team7.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Service class handling JSON Web Token (JWT) generation and validation.
 *
 * This service is responsible for creating and verifying JWT tokens used for
 * authentication and authorization in the application.
 *
 * @see User
 */
@Service
public class TokenService {
    private static final String ISSUER = "API team7";

    /**
     * Secret key used for JWT signing and verification.
     * Injected from application properties.
     */
    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Generates a JWT token for the specified user.
     *
     * @param user the user to generate token for
     * @return the generated JWT token string
     * @throws RuntimeException if token creation fails
     */
    public String buildToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Build token error", exception);
        }
    }

    /**
     * Extracts the subject (username) from a JWT token.
     *
     * @param tokenJWT the JWT token to verify
     * @return the username (subject) from the token
     * @throws RuntimeException if token verification fails (invalid or expired)
     */
    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid or expired token", exception);
        }
    }

    /**
     * Calculates the expiration time for tokens (24 hours from now).
     * Uses UTC-5 timezone offset.
     *
     * @return the expiration Instant
     */
    private Instant expiresAt() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-05:00"));
    }
}
