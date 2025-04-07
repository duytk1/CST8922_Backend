package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.Project;
import com.algonquincollege.team7.model.Semester;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) representing a project in listing views.
 *
 * Contains summarized project information suitable for display in project lists,
 * including basic details and associated tags.
 *
 * @param id Unique project identifier
 * @param projectName Name of the project
 * @param description Brief project description
 * @param semester Academic semester for the project
 * @param ndaRequired Indicates if a Non-Disclosure Agreement is required
 * @param showcaseAllowed Indicates if the project can be publicly showcased
 * @param createdAt Timestamp when the project was created
 * @param tags List of tags associated with the project
 * @see Project
 * @see Semester
 * @see ProjectTagResponse
 * @since 1.0
 */
public record ProjectListResponse(
        Long id,
        String projectName,
        String description,
        Semester semester,
        Boolean ndaRequired,
        Boolean showcaseAllowed,
        LocalDateTime createdAt,
        List<ProjectTagResponse>tags

) {
    public static ProjectListResponse fromProject(Project project) {
        return new ProjectListResponse(
                project.getId(),
                project.getProjectName(),
                project.getDescription(),
                project.getSemester(),
                project.getNdaRequired(),
                project.getShowcaseAllowed(),
                project.getCreatedAt(),
                project.getTags().stream().map(ProjectTagResponse::new).collect(Collectors.toList())
        );
    }
}