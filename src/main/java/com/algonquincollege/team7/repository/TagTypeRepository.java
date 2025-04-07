package com.algonquincollege.team7.repository;

import com.algonquincollege.team7.model.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link TagType} entities.
 * Provides CRUD operations and custom query methods for tag types.
 * Extends {@link JpaRepository} to inherit standard JPA operations.
 */
public interface TagTypeRepository extends JpaRepository<TagType, Long> {

    /**
     * Checks if a TagType with the specified name already exists in the database.
     *
     * @param name the name of the tag type to check for existence
     * @return {@code true} if a TagType with the given name exists,
     *         {@code false} otherwise
     */
    boolean existsByName(String name);
}
