package com.algonquincollege.team7.dto;

/**
 * Data Transfer Object (DTO) containing authentication response details.
 *
 * @param token JSON Web Token (JWT) for authenticated sessions
 * @param userId Unique identifier of the authenticated user
 * @param email Email address of the authenticated user
 * @param firstName User's first name
 * @param lastName User's last name
 * @param type User's role/type (e.g., "PROFESSOR", "ORGANIZATION", "ADMIN")
 * @since 1.0
 */
public record LoginResponse(
    String token,
    Long userId,
    String email,
    String firstName,
    String lastName,
    String type
) {}