package com.algonquincollege.team7.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for user login requests.
 *
 * Represents the required credentials for authenticating a user in the system.
 * Includes validation constraints to ensure properly formatted input.
 *
 */
public record LoginRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        String password) {
}
