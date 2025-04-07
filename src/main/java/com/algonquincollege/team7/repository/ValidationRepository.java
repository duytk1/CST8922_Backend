package com.algonquincollege.team7.repository;

import com.algonquincollege.team7.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for {@link Validation} entities.
 * Provides CRUD operations and custom query methods for validation records.
 * Extends {@link JpaRepository} to inherit standard JPA operations.
 */
public interface ValidationRepository extends JpaRepository<Validation, Long> {

    /**
     * Finds a validation record by its associated project ID.
     *
     * @param projectId the ID of the project to search for validations
     * @return an {@link Optional} containing the validation record if found for the given project ID,
     *         or empty {@link Optional} if no validation exists for the project
     */
    Optional<Validation> findByProjectId(Long projectId);
}
