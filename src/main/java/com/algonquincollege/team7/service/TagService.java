package com.algonquincollege.team7.service;

import com.algonquincollege.team7.dto.TagRequest;

import com.algonquincollege.team7.infra.exception.ApiException;
import com.algonquincollege.team7.model.Tag;
import com.algonquincollege.team7.model.UserType;
import com.algonquincollege.team7.repository.ProjectRepository;
import com.algonquincollege.team7.repository.TagRepository;
import com.algonquincollege.team7.repository.TagValueRepository;
import com.algonquincollege.team7.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Service class handling all business logic related to tag management.
 *
 * Provides functionality for tag registration and deletion,
 * including validation of professor permissions and duplicate tags.
 *
 * @see Tag
 * @see TagRepository
 * @see ProjectRepository
 * @see TagValueRepository
 */
@Service
public class TagService {

    /**
     * Repository for tag data access.
     */
    @Autowired
    private TagRepository tagRepository;

    /**
     * Repository for project data access.
     */
    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Repository for tag value data access.
     */
    @Autowired
    private TagValueRepository tagValueRepository;

    /**
     * Repository for user data access.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new tag association between a project and tag value.
     *
     * @param data the tag registration data
     * @throws ApiException with NOT_FOUND status if:
     *         - Project doesn't exist
     *         - Tag value doesn't exist
     *         - Professor doesn't exist
     * @throws ApiException with CONFLICT status if:
     *         - Professor ID is invalid
     *         - Tag association already exists
     */
    public void registerTag(@RequestBody @Valid TagRequest data) {
        validateTag(data);

        var project = projectRepository.findById(data.projectId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Project not found"));

        var tagValue = tagValueRepository.findById(data.tagValueId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Tag value not found"));

        var professor = userRepository.findById(data.professorId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Professor not found"));

        var tag = new Tag(project, tagValue, professor);
        tagRepository.save(tag);
    }

    /**
     * Deletes a tag association by its ID.
     *
     * @param Id the ID of the tag association to delete
     * @throws ApiException with NOT_FOUND status if tag doesn't exist
     */
    public void deleteTag(Long Id) {
        if (!tagRepository.existsById(Id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Project not found");
        }

        tagRepository.deleteById(Id);
    }

    /**
     * Validates tag registration data.
     *
     * @param data the tag request data to validate
     * @throws ApiException with CONFLICT status if:
     *         - Professor ID is invalid
     *         - Tag association already exists
     */
    private void validateTag(@RequestBody @Valid TagRequest data) {
        var invalidProfessorId = !userRepository.existsByIdAndType(data.professorId(),
                UserType.valueOf("PROFESSOR"));

        if (invalidProfessorId) {
            throw new ApiException(HttpStatus.CONFLICT, "Invalid professor ID");
        }

        var duplicatedTag = tagRepository.existsByProjectIdAndTagValueId(data.projectId(), data.tagValueId());
        if (duplicatedTag) {
            throw new ApiException(HttpStatus.CONFLICT, "Tag already registered");
        }
    }
}
