package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.Tag;

/**
 * Data Transfer Object (DTO) representing a tag associated with a project.
 *
 * Contains simplified tag information suitable for display in project views,
 * including the tag value and its category type.
 *
 * @param id Unique identifier of the tag association
 * @param value The actual tag value/text
 * @param tagType The category/type of the tag
 * @see Tag
 * @since 1.0
 */
public record ProjectTagResponse(Long id, String value, String tagType) {

    public ProjectTagResponse(Tag tag) {
        this(tag.getId(), tag.getTagValue().getValue(), tag.getTagValue().getTagType().getName());
    }
}
