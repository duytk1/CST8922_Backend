package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.TagValue;

/**
 * Data Transfer Object (DTO) representing a tag in listing views.
 *
 * @param id Unique identifier of the tag value
 * @param tagValue The display text/value of the tag
 * @param tagType The category/classification of the tag
 * @see TagValue
 * @since 1.0
 */
public record TagListResponse(
        Long id,
        String tagValue,
        String tagType) {

    public static TagListResponse fromEntity(TagValue tagValue) {
        return new TagListResponse(tagValue.getId(), tagValue.getValue(), tagValue.getTagType().getName());
    }
}