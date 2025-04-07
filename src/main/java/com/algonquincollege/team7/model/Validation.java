package com.algonquincollege.team7.model;

import com.algonquincollege.team7.dto.ValidationRequest;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a validation record for a project by a professor.
 *
 * This entity tracks the approval/review process of projects by professors,
 * storing feedback and timestamps for validation actions.
 *
 * @see Project
 * @see User
 * @see ValidationRequest
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "project_validation")
public class Validation {

    /**
     * Unique identifier for the validation record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The project being validated.
     * Many-to-one relationship with {@link Project}.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * The professor validating the project.
     * Many-to-one relationship with {@link User}.
     */
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private User professor;

    /**
     * Feedback or comments provided by the professor during validation.
     */
    @Column(name = "professor_feedback")
    private String professorFeedback;

    /**
     * Timestamp when the validation record was created.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Constructs a new Validation from request data, project, and professor.
     * Automatically sets the creation timestamp.
     *
     * @param data the validation request DTO
     * @param project the project being validated
     * @param professor the professor performing validation
     * @throws jakarta.validation.ConstraintViolationException if request data is invalid
     */
    public Validation(@Valid ValidationRequest data, Project project, User professor) {
        this.project = project;
        this.professor = professor;
        this.professorFeedback = data.professorFeedback();
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Updates the validation record from request data.
     * Note: Currently only updates feedback, not professor reference.
     *
     * @param data the validation request DTO with updated information
     */
    public void updateFrom(ValidationRequest data) {
        this.professor = professor;
        this.professorFeedback = data.professorFeedback();
    }
}

