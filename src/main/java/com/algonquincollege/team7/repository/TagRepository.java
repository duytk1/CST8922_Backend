package com.algonquincollege.team7.repository;

import com.algonquincollege.team7.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Tag} entities.
 * Provides CRUD operations and custom query methods for tags.
 * Extends {@link JpaRepository} to inherit standard JPA operations.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * Checks whether a tag association exists between a specific project and tag value.
     *
     * @param projectId the ID of the project to check
     * @param tagValueId the ID of the tag value to check
     * @return {@code true} if a tag association exists between the specified project and tag value,
     *         {@code false} otherwise
     */
    boolean existsByProjectIdAndTagValueId(Long projectId, Long tagValueId);
}
