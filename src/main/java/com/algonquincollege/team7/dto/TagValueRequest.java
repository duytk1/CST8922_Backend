package com.algonquincollege.team7.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for creating or updating tag values.
 *
 * Represents a request to create or modify a specific tag value within a tag type category,
 * with validation constraints to ensure proper data integrity.
 *
 * @param id The ID of the tag value (required for updates, null for creations)
 * @param tagTypeId The ID of the parent tag type category (required)
 * @param value The actual tag value text (1-100 characters)
 * @since 1.0
 */
public record TagValueRequest(
        Long id,

        @NotNull(message = "Tag type Id is required")
        Long tagTypeId,

        @NotBlank(message = "Tag value is required")
        @Size(max = 100, message = "Tag value must be under 100 characters")
        @Size(min = 1, message = "Tag value must have at least 1 character")
        String value) {
}
