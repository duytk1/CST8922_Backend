package com.algonquincollege.team7.service;

import com.algonquincollege.team7.dto.TagListResponse;
import com.algonquincollege.team7.dto.TagValueRequest;
import com.algonquincollege.team7.infra.exception.ApiException;
import com.algonquincollege.team7.model.TagValue;
import com.algonquincollege.team7.repository.TagTypeRepository;
import com.algonquincollege.team7.repository.TagValueRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Service class handling all business logic related to tag value management.
 *
 * Provides functionality for creating, updating, and retrieving tag values,
 * including validation of tag type associations and uniqueness constraints.
 *
 * @see TagValue
 * @see TagValueRepository
 * @see TagTypeRepository
 */
@Service
public class TagValueService {

    /**
     * Repository for tag value data access.
     */
    @Autowired
    private TagValueRepository tagValueRepository;

    /**
     * Repository for tag type data access.
     */
    @Autowired
    private TagTypeRepository tagTypeRepository;

    /**
     * Registers a new tag value associated with a tag type.
     *
     * @param data the tag value registration data
     * @throws ApiException with CONFLICT status if:
     *         - Tag value already exists for the given tag type
     *         - Tag type ID is invalid
     * @throws jakarta.persistence.EntityNotFoundException if tag type doesn't exist
     */
    public void registerTagValue(@RequestBody @Valid TagValueRequest data) {
        validateTagValue(data);

        var tagType = tagTypeRepository.findById(data.tagTypeId()).get();
        var tagValue = new TagValue(data, tagType);

        tagValueRepository.save(tagValue);
    }

    /**
     * Updates an existing tag value.
     *
     * @param data the tag value update data
     * @throws ApiException with CONFLICT status if:
     *         - Tag value already exists for the given tag type
     *         - Tag type ID is invalid
     * @throws jakarta.persistence.EntityNotFoundException if tag value or tag type doesn't exist
     */
    public void editTagValue(@RequestBody @Valid TagValueRequest data) {
        validateTagValue(data);

        var tagValue = tagValueRepository.findById(data.id()).get();
        var tagType = tagTypeRepository.findById(data.tagTypeId()).get();

        tagValue.updateFrom(data, tagType);
        tagValueRepository.save(tagValue);
    }

    /**
     * Validates tag value data for uniqueness and tag type existence.
     *
     * @param data the tag value data to validate
     * @throws ApiException with CONFLICT status if:
     *         - Tag value already exists for the given tag type
     *         - Tag type ID is invalid
     */
    private void validateTagValue(@RequestBody @Valid TagValueRequest data) {
        var duplicatedTagValue = tagValueRepository.existsByValueAndTagTypeId(data.value(), data.tagTypeId());
        if (duplicatedTagValue) {
            throw new ApiException(HttpStatus.CONFLICT, "Tag value already registered");
        }

        var validTagValue = tagTypeRepository.existsById(data.tagTypeId());
        if (!validTagValue) {
            throw new ApiException(HttpStatus.CONFLICT, "Tag type is invalid");
        }
    }

    /**
     * Retrieves all tag values ordered by their associated tag type name.
     *
     * @return list of tag values with their type information
     */
    public List<TagListResponse> getTagList() {
        return tagValueRepository.findAllOrderedByTagType()
                .stream()
                .map(tag -> new TagListResponse(tag.getId(), tag.getValue(), tag.getTagType().getName()))
                .toList();
    }
}
