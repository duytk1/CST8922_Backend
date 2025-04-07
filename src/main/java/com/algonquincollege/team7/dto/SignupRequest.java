package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for user registration requests.
 *
 * Contains all required information to create a new user account in the system,
 * with validation constraints to ensure data integrity and security.
 *
 * @param email User's email address (serves as username)
 * @param firstName User's first name
 * @param lastName User's last name
 * @param organizationName Organization name (required for ORGANIZATION type)
 * @param phone User's contact phone number
 * @param type User's role/type in the system
 * @param password User's password (will be hashed)
 * @see UserType
 * @since 1.0
 */
public record SignupRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        String organizationName,

        @NotNull(message = "Phone number is required")
        @Size(max = 20, message = "Phone number must not exceed 20 characters")
        String phone,

        @NotNull(message = "User type is required")
        UserType type,

        @NotBlank(message = "Password is required")
        String password) {
}
