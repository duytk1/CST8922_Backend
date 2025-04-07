package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.Project;
import com.algonquincollege.team7.model.Semester;
import com.algonquincollege.team7.model.Validation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) representing a detailed project view.
 *
 * Contains complete project information including all metadata, validation feedback,
 * and associated tags for display in detailed project views.
 *
 * @param id Unique project identifier
 * @param projectName Name of the project
 * @param description Detailed project description
 * @param availableTime Estimated time commitment in hours
 * @param purchasingRequirements Required materials/equipment
 * @param ndaRequired Whether NDA is required
 * @param showcaseAllowed Whether project can be showcased
 * @param semester Academic semester for the project
 * @param createdAt Project creation timestamp
 * @param updatedAt Project last update timestamp
 * @param professorFeedback Validation feedback from professor (nullable)
 * @param tags List of tags associated with the project
 * @see Project
 * @see Validation
 * @see Semester
 * @see ProjectTagResponse
 * @since 1.0
 */
public record ProjectViewResponse(
        Long id,
        String projectName,
        String description,
        Integer availableTime,
        String purchasingRequirements,
        Boolean ndaRequired,
        Boolean showcaseAllowed,
        Semester semester,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String professorFeedback,
        List<ProjectTagResponse> tags
) {

    public ProjectViewResponse(Project data, Validation validation) {
        this(
                data.getId(),
                data.getProjectName(),
                data.getDescription(),
                data.getAvailableTime(),
                data.getPurchasingRequirements(),
                data.getNdaRequired(),
                data.getShowcaseAllowed(),
                data.getSemester(),
                data.getCreatedAt(),
                data.getUpdatedAt(),
                validation != null ? validation.getProfessorFeedback() : null,
                data.getTags().stream().map(ProjectTagResponse::new).collect(Collectors.toList())
        );
    }

}