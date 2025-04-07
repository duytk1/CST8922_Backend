package com.algonquincollege.team7.service;

import com.algonquincollege.team7.dto.TagTypeRequest;
import com.algonquincollege.team7.infra.exception.ApiException;
import com.algonquincollege.team7.model.TagType;
import com.algonquincollege.team7.repository.TagTypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Service class handling all business logic related to tag type management.
 *
 * Provides functionality for creating and updating tag type categories,
 * ensuring unique names and proper validation.
 *
 * @see TagType
 * @see TagTypeRepository
 */
@Service
public class TagTypeService {

    /**
     * Repository for tag type data access.
     */
    @Autowired
    private TagTypeRepository tagTypeRepository;

    /**
     * Registers a new tag type category.
     *
     * @param data the tag type registration data
     * @throws ApiException with CONFLICT status if tag type name already exists
     */
    public void registerTagType(@RequestBody @Valid TagTypeRequest data) {
        validateTagType(data);

        var tagType = new TagType(data.name());
        tagTypeRepository.save(tagType);
    }

    /**
     * Updates an existing tag type category.
     *
     * @param data the tag type update data containing ID and new name
     * @throws ApiException with CONFLICT status if new name already exists
     * @throws jakarta.persistence.EntityNotFoundException if tag type ID doesn't exist
     */
    public void editTagType(@RequestBody @Valid TagTypeRequest data) {
        validateTagType(data);

        var tagType = tagTypeRepository.findById(data.id()).get();
        tagType.updateFrom(data.name());
        tagTypeRepository.save(tagType);
    }

    /**
     * Validates tag type data for uniqueness.
     *
     * @param data the tag type data to validate
     * @throws ApiException with CONFLICT status if tag type name already exists
     */
    private void validateTagType(@RequestBody @Valid TagTypeRequest data) {
        var duplicatedTagType = tagTypeRepository.existsByName(data.name());

        if (duplicatedTagType) {
            throw new ApiException(HttpStatus.CONFLICT, "Tag type already registered");
        }
    }
}
