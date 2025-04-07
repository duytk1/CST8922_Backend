package com.algonquincollege.team7.infra.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom runtime exception for API error handling.
 *
 * This exception is used throughout the application to represent
 * business logic errors that should be translated to specific HTTP
 * responses. It carries both an HTTP status code and a detailed
 * error message.
 *
 * @see HttpStatus
 * @see RuntimeException
 * @since 1.0
 */
public class ApiException extends RuntimeException {
    private final HttpStatus status;

    /**
     * Constructs a new API exception with the specified status and message.
     *
     * @param status the HTTP status code to return
     * @param message the detailed error message
     * @throws IllegalArgumentException if status is null
     */
    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * Gets the HTTP status code associated with this exception.
     *
     * @return the HTTP status code
     */
    public HttpStatus getStatus() {
        return status;
    }
}
