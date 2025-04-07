package com.algonquincollege.team7.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for project validation requests.
 *
 * Contains all required information for a professor to validate or provide feedback
 * on a project, with validation constraints to ensure complete and properly
 * formatted submissions.
 *
 * @param projectId The ID of the project being validated (required)
 * @param professorId The ID of the professor providing validation (required)
 * @param professorFeedback Detailed feedback from the professor (1-10,000 characters)
 * @since 1.0
 */
public record ValidationRequest(
        @NotNull(message = "Project Id is required")
        Long projectId,

        @NotNull(message = "Professor Id is required")
        Long professorId,

        @NotBlank(message = "Professor feedback is required")
        @Size(max = 10000, message = "Feedback must be under 10000 characters")
        String professorFeedback) {
}

