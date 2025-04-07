package com.algonquincollege.team7.controller;

import com.algonquincollege.team7.dto.*;
import com.algonquincollege.team7.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing project-related operations.
 *
 * Provides endpoints for creating, retrieving, and updating projects,
 * with proper validation and transactional boundaries.
 *
 * @see ProjectService
 * @since 1.0
 */
@RestController
@RequestMapping("api/project")
@CrossOrigin(origins = "http://localhost:8080")
public class ProjectController {

    /**
     * Service layer for handling project business logic.
     */
    @Autowired
    private ProjectService projectService;

    /**
     * Registers a new project in the system.
     */
    @PostMapping
    public ResponseEntity registerProject(@RequestBody @Valid ProjectRegistrationRequest data) {

        projectService.registerProject(data);
        var responseOk = new GeneralResponse("Project registered successfully");
        return ResponseEntity.ok(responseOk);
    }

    /**
     * Retrieves projects associated with a specific organization.
     */
    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<ProjectListResponseWrapper> getProjectsByOrganization(
            @PathVariable Long organizationId,
            @ModelAttribute ProjectListRequest request) {

        ProjectListResponseWrapper response = projectService.getProjectsByOrganizationId(organizationId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all projects in the system.
     */
    @GetMapping
    public ResponseEntity<ProjectListResponseWrapper> getAllProjects() {
        ProjectListResponseWrapper response = projectService.getAllProjects();
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves detailed information about a specific project.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectViewResponse> getProjectById(@PathVariable Long id) {
        ProjectViewResponse response = projectService.findProjectById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing project's information.
     */
    @Transactional
    @PutMapping
    public ResponseEntity editProject(@RequestBody @Valid ProjectEditRequest data) {

        projectService.editProject(data);
        var responseOk = new GeneralResponse("Project updated successfully");
        return ResponseEntity.ok(responseOk);
    }
}
