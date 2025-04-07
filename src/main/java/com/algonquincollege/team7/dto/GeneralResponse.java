package com.algonquincollege.team7.dto;

/**
 * A generic response DTO for returning simple message responses from API endpoints.
 *
 * This record provides a standardized format for returning success/status messages
 * or simple textual responses from API operations.
 *
 * @param message The response message content
 * @since 1.0
 */
public record GeneralResponse(String message) {
}