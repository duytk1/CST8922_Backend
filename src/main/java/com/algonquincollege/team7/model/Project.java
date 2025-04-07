package com.algonquincollege.team7.model;

import com.algonquincollege.team7.dto.ProjectEditRequest;
import com.algonquincollege.team7.dto.ProjectRegistrationRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a Project entity in the system.
 *
 * This class models all the attributes and relationships of a project,
 * including its basic information, requirements, associated users,
 * and tagging system. Projects are created by organizations and
 * may be assigned to professors.
 *
 * @see User
 * @see Tag
 * @see Semester
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "project")
public class Project {

    /**
     * Unique identifier for the project.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the project.
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * Detailed description of the project.
     * This field is required and cannot be null.
     */
    @Column(nullable = false)
    private String description;

    /**
     * Available time (in hours) required for the project.
     */
    @Column(name = "available_time")
    private Integer availableTime;

    /**
     * Purchasing requirements for the project.
     */
    @Column(name = "purchasing_requirements")
    private String purchasingRequirements;

    /**
     * Flag indicating if a Non-Disclosure Agreement is required.
     */
    @Column(name = "nda_required")
    private Boolean ndaRequired;

    /**
     * Flag indicating if the project can be showcased.
     */
    @Column(name = "showcase_allowed")
    private Boolean showcaseAllowed;

    /**
     * Academic semester when the project is active.
     * Stored as an enumerated string value.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Semester semester;

    /**
     * Organization that created the project.
     * Many-to-one relationship with User entity.
     */
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private User organization;

    /**
     * Professor assigned to the project.
     * Many-to-one relationship with User entity.
     */
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private User professor;

    /**
     * Timestamp when the project was created.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Timestamp when the project was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * List of tags associated with the project.
     * One-to-many relationship with Tag entity.
     * Uses lazy fetching and cascades all operations.
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tag> tags;

    /**
     * Constructs a new Project from registration data.
     *
     * @param data the registration data transfer object
     * @param user the organization user creating the project
     */
    public Project(ProjectRegistrationRequest data, User user) {
        this.projectName = data.projectName();
        this.description = data.description();
        this.availableTime = data.availableTime();
        this.purchasingRequirements = data.purchasingRequirements();
        this.ndaRequired = data.ndaRequired();
        this.showcaseAllowed = data.showcaseAllowed();
        this.semester = data.semester();
        this.createdAt = LocalDateTime.now();
        this.organization = user;
    }

    /**
     * Updates the project properties from edit request data.
     * Automatically sets the updatedAt timestamp to current time.
     *
     * @param data the edit data transfer object
     */
    public void updateFrom(ProjectEditRequest data) {
        this.projectName = data.projectName();
        this.description = data.description();
        this.availableTime = data.availableTime();
        this.purchasingRequirements = data.purchasingRequirements();
        this.ndaRequired = data.ndaRequired();
        this.showcaseAllowed = data.showcaseAllowed();
        this.semester = data.semester();
        this.updatedAt = LocalDateTime.now();
    }
}
