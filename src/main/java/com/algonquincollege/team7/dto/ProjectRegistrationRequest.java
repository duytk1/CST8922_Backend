package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.Semester;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for registering new projects.
 *
 * Contains all required information to create a new project in the system,
 * with validation constraints to ensure data integrity.
 *
 * @param projectName Name of the project (required)
 * @param description Detailed project description (required)
 * @param availableTime Estimated time commitment in hours (required)
 * @param purchasingRequirements Optional materials/equipment needed
 * @param ndaRequired Whether NDA is required (required)
 * @param showcaseAllowed Whether project can be showcased (required)
 * @param semester Academic semester for the project (required)
 * @param organizationId ID of the registering organization (required)
 * @see Semester
 * @since 1.0
 */
public record ProjectRegistrationRequest(
        @NotBlank(message = "Project name is required")
        String projectName,

        @NotBlank(message = "Project description is required")
        String description,

        @NotNull(message = "Project available time is required")
        Integer availableTime,

        String purchasingRequirements,

        @NotNull(message = "Project purchasing requirements is required")
        Boolean ndaRequired,

        @NotNull(message = "Project showcase allowed is required")
        Boolean showcaseAllowed,

        @NotNull(message = "Semester is required")
        Semester semester,

        @NotNull(message = "Organization ID is required")
        Long organizationId) {
}
