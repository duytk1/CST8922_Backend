package com.algonquincollege.team7.controller;

import com.algonquincollege.team7.dto.LoginRequest;
import com.algonquincollege.team7.dto.LoginResponse;
import com.algonquincollege.team7.model.User;
import com.algonquincollege.team7.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling user authentication.
 *
 * Provides an endpoint for users to log in to the system and receive
 * a JWT token for authenticated requests. Uses Spring Security's
 * authentication framework to verify credentials.
 *
 * @see TokenService
 * @see AuthenticationManager
 * @since 1.0
 */
@RestController
@RequestMapping("api/login")
public class LoginController {

    /**
     * Spring Security component for handling authentication.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Service for generating JWT tokens.
     */
    @Autowired
    private TokenService tokenService;

    /**
     * Authenticates a user and generates an access token.
     */
    @PostMapping
    public ResponseEntity loginUser(@RequestBody @Valid LoginRequest data) {

        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = authenticationManager.authenticate(token);
        var user = (User) authentication.getPrincipal();

        var tokenJWT = tokenService.buildToken(user);

        return ResponseEntity.ok(new LoginResponse(
            tokenJWT,
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getType().toString()
        ));
    }
}
