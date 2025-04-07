package com.algonquincollege.team7.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for creating or updating tag types.
 *
 * Represents a request to create or modify a tag category/classification,
 * with validation constraints to ensure proper naming conventions.
 *
 * @param id The ID of the tag type (required for updates, null for creations)
 * @param name The name of the tag category (1-50 characters)
 * @since 1.0
 */
public record TagTypeRequest(
        Long id,

        @NotBlank(message = "Tag type is required")
        @Size(max = 50, message = "Tag type must be under 50 characters")
        @Size(min = 1, message = "Tag type must have at least 1 character")
        String name) {
}
