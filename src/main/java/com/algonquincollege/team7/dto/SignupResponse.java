package com.algonquincollege.team7.dto;

/**
 * Data Transfer Object (DTO) representing a successful user registration response.
 *
 * Contains confirmation details after a new user account has been successfully created.
 * This response provides feedback to the client about the registration outcome.
 *
 * @param userName The username (email) of the newly registered user
 * @param message A human-readable success message or instructions
 * @since 1.0
 */
public record SignupResponse(String userName, String message) {
}