package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.Semester;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for editing existing projects.
 *
 * Contains all editable fields of a project with validation constraints.
 * Used when updating existing project information.
 *
 * @param id Project database identifier (required)
 * @param projectName Name of the project (required, non-blank)
 * @param description Detailed project description (required, non-blank)
 * @param availableTime Estimated time required for project completion in hours (required)
 * @param purchasingRequirements Optional purchasing needs for the project
 * @param ndaRequired Flag indicating if NDA is required (required)
 * @param showcaseAllowed Flag indicating if project can be showcased (required)
 * @param semester Academic semester for the project (required)
 * @see Semester
 * @since 1.0
 */
public record ProjectEditRequest (
        @NotNull(message = "Project Id is required")
        Long id,

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
        Semester semester) {
        }