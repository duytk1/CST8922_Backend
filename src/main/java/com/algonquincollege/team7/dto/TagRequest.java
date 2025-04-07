package com.algonquincollege.team7.dto;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for creating or managing tag associations.
 *
 * Contains all required references to associate a tag with a project,
 * including validation constraints to ensure data integrity.
 *
 * @param projectId The ID of the project being tagged (required)
 * @param tagValueId The ID of the tag value being applied (required)
 * @param professorId The ID of the professor assigning the tag (required)
 * @since 1.0
 */
public record TagRequest(
        @NotNull(message = "Project Id is required")
        Long projectId,

        @NotNull(message = "Tag value Id is required")
        Long tagValueId,

        @NotNull(message = "Professor Id is required")
        Long professorId) {
}
