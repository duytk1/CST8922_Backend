package com.algonquincollege.team7.controller;

import com.algonquincollege.team7.dto.SignupRequest;
import com.algonquincollege.team7.dto.SignupResponse;
import com.algonquincollege.team7.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling user registration operations.
 *
 * Provides an endpoint for new users to create accounts in the system.
 * Validates input data and returns appropriate responses.
 *
 * @see UserService
 * @see SignupRequest
 * @see SignupResponse
 * @since 1.0
 */
@RestController
@RequestMapping("api/signup")
public class SignupController {

    /**
     * Service layer for handling user business logic.
     */
    @Autowired
    private UserService userService;

    /**
     * Registers a new user in the system.
     */
    @PostMapping
    public ResponseEntity signupUser(@RequestBody @Valid SignupRequest data) {
        userService.signupUser(data);
        var responseOk = new SignupResponse(data.email(), "User registered successfully");
        return ResponseEntity.ok(responseOk);
    }
}
