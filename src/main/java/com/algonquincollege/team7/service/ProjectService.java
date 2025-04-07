package com.algonquincollege.team7.service;

import com.algonquincollege.team7.dto.*;
import com.algonquincollege.team7.infra.exception.ApiException;
import com.algonquincollege.team7.model.Project;
import com.algonquincollege.team7.model.UserType;
import com.algonquincollege.team7.model.Validation;
import com.algonquincollege.team7.repository.ProjectRepository;
import com.algonquincollege.team7.repository.UserRepository;
import com.algonquincollege.team7.repository.ValidationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class handling all business logic related to project management.
 *
 * Provides functionality for project registration, retrieval, and modification,
 * including validation and organization-specific operations.
 *
 * @see Project
 * @see ProjectRepository
 * @see UserRepository
 */
@Service
public class ProjectService {

    /**
     * Repository for project data access.
     */
    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Repository for user data access.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Repository for validation data access.
     */
    @Autowired
    private ValidationRepository validationRepository;

    /**
     * Registers a new project in the system.
     *
     * @param data the project registration data
     * @throws ApiException with CONFLICT status if organization ID is invalid
     * @throws ApiException with NOT_FOUND status if organization doesn't exist
     */
    public void registerProject(@RequestBody @Valid ProjectRegistrationRequest data) {
        var invalidOrganizationId = !userRepository.existsByIdAndType(
            data.organizationId(), 
            UserType.valueOf("ORGANIZATION")
        );
        
        if (invalidOrganizationId) {
            throw new ApiException(HttpStatus.CONFLICT, "Invalid organization ID");
        }

        var user = userRepository.findById(data.organizationId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Organization not found"));

        var project = new Project(data, user);
        projectRepository.save(project);
    }

    /**
     * Retrieves projects filtered by organization ID with pagination support.
     *
     * @param organizationId the ID of the organization to filter by
     * @param request the pagination and filtering parameters
     * @return wrapped paginated response of projects
     * @throws ApiException with NOT_FOUND status if organization doesn't exist
     */
    public ProjectListResponseWrapper getProjectsByOrganizationId(Long organizationId, ProjectListRequest request) {
        var organizationExists = userRepository.existsByIdAndType(organizationId, UserType.valueOf("ORGANIZATION"));
        if (!organizationExists) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Organization not found");
        }

        Pageable pageable = PageRequest.of(
                request.page(),
                request.size(),
                Sort.Direction.fromString(request.sortDirection()),
                request.sortBy()
        );

        Page<Project> projectPage;
        if (request.semesterFilter() != null) {
            projectPage = projectRepository.findByOrganizationIdAndSemester(organizationId, request.semesterFilter(), pageable);
        } else {
            projectPage = projectRepository.findByOrganizationId(organizationId, pageable);
        }

        List<ProjectListResponse> projectResponses = projectPage.getContent().stream()
                .map(ProjectListResponse::fromProject)
                .collect(Collectors.toList());

        return new ProjectListResponseWrapper(
                projectResponses,
                projectPage.getNumber(),
                projectPage.getSize(),
                projectPage.getTotalElements(),
                projectPage.getTotalPages()
        );
    }

    /**
     * Retrieves all projects in the system.
     *
     * @return wrapped response containing all projects
     */
    public ProjectListResponseWrapper getAllProjects() {
        List<Project> projects = projectRepository.findAll(); 
        List<ProjectListResponse> projectResponses = projects.stream()
            .map(ProjectListResponse::fromProject)
            .collect(Collectors.toList());

        return new ProjectListResponseWrapper(
            projectResponses, 
            0, 
            projects.size(), 
            projects.size(), 
            1
        );
    }

    /**
     * Finds a specific project by its ID including validation information.
     *
     * @param projectId the ID of the project to find
     * @return detailed project view response
     * @throws ApiException with NOT_FOUND status if project doesn't exist
     */
    public ProjectViewResponse findProjectById(Long projectId) {
        var project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Project not found"));

        Optional<Validation> validation = validationRepository.findByProjectId(projectId);
        return new ProjectViewResponse(project, validation.orElse(null));
    }

    /**
     * Updates an existing project.
     *
     * @param data the project edit data
     * @throws ApiException with NOT_FOUND status if project doesn't exist
     */
    public void editProject(@RequestBody @Valid ProjectEditRequest data) {
        var project = projectRepository.findById(data.id())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Project not found"));

        project.updateFrom(data);
        projectRepository.save(project);
    }
}