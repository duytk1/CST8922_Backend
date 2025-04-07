package com.algonquincollege.team7.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a Tag entity that associates projects with tag values.
 *
 * This entity serves as a many-to-many relationship between {@link Project} and
 * {@link TagValue}, with additional metadata about who assigned the tag (professor)
 * and when it was created.
 *
 * @see Project
 * @see TagValue
 * @see User
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "project_tag")
public class Tag {

    /**
     * Unique identifier for the tag association.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The project this tag is associated with.
     * Many-to-one relationship with {@link Project}.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * The specific tag value being applied.
     * Many-to-one relationship with {@link TagValue}.
     */
    @ManyToOne
    @JoinColumn(name = "tag_value_id")
    private TagValue tagValue;

    /**
     * The professor who assigned this tag.
     * Many-to-one relationship with {@link User}.
     */
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private User professor;

    /**
     * Timestamp when the tag was assigned to the project.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Constructs a new Tag association between a project and tag value.
     * Automatically sets the creation timestamp to current time.
     *
     * @param project the project being tagged
     * @param tagValue the tag value being applied
     * @param professor the professor assigning the tag
     */
    public Tag(Project project, TagValue tagValue, User professor) {
        this.project = project;
        this.tagValue = tagValue;
        this.professor = professor;
        this.createdAt = LocalDateTime.now();
    }
}
