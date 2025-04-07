package com.algonquincollege.team7.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a category or classification for tags in the system.
 *
 * TagTypes define different categories that can be used to group and organize
 * {@link TagValue} entities. Each TagType has a unique name that identifies
 * the category (e.g., "Technology", "Domain", "Skill Level").
 *
 * @see TagValue
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tag_type")
public class TagType {

    /**
     * Unique identifier for the tag type.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the tag type category.
     * This should be unique across all tag types.
     */
    private String name;

    /**
     * Constructs a new TagType with the specified name.
     *
     * @param name the name of the tag type category
     */
    public TagType(String name) {
        this.name = name;
    }

    /**
     * Updates the name of this tag type.
     *
     * @param name the new name for the tag type
     */
    public void updateFrom(String name) {
        this.name = name;
    }
}
