package com.algonquincollege.team7.service;

import com.algonquincollege.team7.dto.ValidationRequest;
import com.algonquincollege.team7.infra.exception.ApiException;
import com.algonquincollege.team7.model.User;
import com.algonquincollege.team7.model.UserType;
import com.algonquincollege.team7.model.Validation;
import com.algonquincollege.team7.repository.ProjectRepository;
import com.algonquincollege.team7.repository.UserRepository;
import com.algonquincollege.team7.repository.ValidationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Service class handling project validation operations.
 *
 * Manages the lifecycle of project validations, including creation and updates of validation records.
 * Ensures only authorized professors can validate projects and maintains data integrity through
 * comprehensive validation checks.
 *
 * @see Validation
 * @see ValidationRepository
 * @see ProjectRepository
 * @see UserRepository
 * @since 1.0
 */
@Service
public class ValidationService {

    /**
     * Repository for CRUD operations on validation entities.
     */
    @Autowired
    private ValidationRepository validationRepository;

    /**
     * Repository for project data access and management.
     */
    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Repository for user data operations and queries.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new validation record for a project.
     *
     * @param data Validation request containing project ID, professor ID, and feedback
     * @throws ApiException HTTP 404 if project not found
     * @throws ApiException HTTP 404 if professor not found
     * @throws ApiException HTTP 409 if user is not a professor
     * @see ValidationRequest
     */
    public void registerValidation(@RequestBody @Valid ValidationRequest data) {
        var project = projectRepository.findById(data.projectId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Project not found"));

        var professor = validateProfessor(data.professorId());

        var validation = new Validation(data, project, professor);
        validationRepository.save(validation);
    }

    /**
     * Updates an existing project validation.
     *
     * @param data Updated validation data
     * @throws ApiException HTTP 404 if project not found
     * @throws ApiException HTTP 404 if validation record not found
     * @throws ApiException HTTP 404 if professor not found
     * @throws ApiException HTTP 409 if user is not a professor
     */
    public void editValidation(@RequestBody @Valid ValidationRequest data) {
        var project = projectRepository.findById(data.projectId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Project not found"));

        var professor = validateProfessor(data.professorId());

        var validation = validationRepository.findByProjectId(data.projectId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Validation not found"));

        validation.updateFrom(data);
        validationRepository.save(validation);
    }

    /**
     * Validates that a user exists and has professor privileges.
     *
     * @param professorId ID of the user to validate
     * @return Validated professor entity
     * @throws ApiException HTTP 404 if user not found
     * @throws ApiException HTTP 409 if user is not a professor
     * @see UserType#PROFESSOR
     */
    private User validateProfessor(Long professorId){
        var invalidProfessorId = !userRepository.existsByIdAndType(professorId,
                UserType.valueOf("PROFESSOR"));

        if (invalidProfessorId) {
            throw new ApiException(HttpStatus.CONFLICT, "Invalid professor ID");
        }

        var professor = userRepository.findById(professorId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Professor not found"));

        return professor;
    }
}
