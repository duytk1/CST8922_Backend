package com.algonquincollege.team7.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 *
 * This class provides centralized exception handling across all controllers
 * through Spring's {@code @RestControllerAdvice} mechanism. It translates various
 * exceptions into appropriate HTTP responses with standardized error formats.
 *
 * @see RestControllerAdvice
 * @see ExceptionHandler
 * @since 1.0
 */
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Handles JPA entity not found exceptions.
     *
     * @return ResponseEntity with HTTP 404 status
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    /**
     * Handles method argument validation errors.
     *
     * @param ex the validation exception containing error details
     * @return ResponseEntity with HTTP 400 status and list of field errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataErrors::new).toList());
    }

    /**
     * Record representing field-level validation errors.
     *
     * @param field the name of the invalid field
     * @param message the validation error message
     */
    private record DataErrors(String field, String message) {
        public DataErrors(FieldError errors) {
            this(errors.getField(), errors.getDefaultMessage());
        }
    }

    /**
     * Handles custom API exceptions.
     *
     * @param ex the custom API exception
     * @return ResponseEntity with custom status and error details
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        return buildErrorResponse(ex.getStatus(), ex.getStatus().getReasonPhrase(), ex.getMessage());
    }

    /**
     * Builds a standardized error response.
     *
     * @param status the HTTP status code
     * @param error the error type
     * @param message the detailed error message
     * @return ResponseEntity containing the error response
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String error, String message) {
        return ResponseEntity.status(status).body(new ErrorResponse(error, message));
    }

    /**
     * Record representing a standardized API error response.
     *
     * @param error the error type/category
     * @param message the detailed error message
     */
    private record ErrorResponse(String error, String message) {}
}
