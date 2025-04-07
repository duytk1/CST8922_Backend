package com.algonquincollege.team7;

/**
 * Custom runtime exception for validation failures in the application.
 *
 * This exception is thrown when business logic validation fails, typically
 * representing cases where an operation cannot be completed due to invalid
 * data or state, even if the request is syntactically correct.
 *
 * @see RuntimeException
 * @since 1.0
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructs a new validation exception with the specified detail message.
     *
     * The message should clearly describe the validation failure and,
     * where applicable, how to correct it.
     */
    public ValidationException(String message) {
        super(message);
    }
}
