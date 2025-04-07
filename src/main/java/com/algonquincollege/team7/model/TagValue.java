package com.algonquincollege.team7.model;

import com.algonquincollege.team7.dto.TagValueRequest;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

/**
 * Represents a specific tag value within a tag type category.
 *
 * This entity stores the actual values that can be assigned as tags to projects.
 * Each TagValue belongs to a specific {@link TagType} which defines its category.
 *
 * Example: For a TagType "Technology", TagValues might include "Java", "Python", "React".
 *
 * @see TagType
 * @see TagValueRequest
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tag_value")
public class TagValue {

    /**
     * Unique identifier for the tag value.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The category type this tag value belongs to.
     * Many-to-one relationship with {@link TagType}.
     */
    @ManyToOne
    @JoinColumn(name = "tag_type_id")
    private TagType tagType;

    /**
     * The actual tag value text.
     * This represents the specific value that can be assigned to projects.
     */
    private String value;

    /**
     * Constructs a new TagValue from request data and associated tag type.
     *
     * @param data the request DTO containing tag value information
     * @param tagType the category type this value belongs to
     * @throws jakarta.validation.ConstraintViolationException if the request data is invalid
     */
    public TagValue(@Valid TagValueRequest data, TagType tagType) {
        this.tagType = tagType;
        this.value = data.value();
    }

    /**
     * Updates the tag value from request data and associated tag type.
     *
     * @param data the request DTO containing updated tag value information
     * @param tagType the (potentially updated) category type for this value
     * @throws jakarta.validation.ConstraintViolationException if the request data is invalid
     */
    public void updateFrom(TagValueRequest data, TagType tagType) {
        this.tagType = tagType;
        this.value = data.value();
    }
}
