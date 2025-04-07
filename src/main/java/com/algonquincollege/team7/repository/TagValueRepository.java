package com.algonquincollege.team7.repository;

import com.algonquincollege.team7.model.TagValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Repository interface for {@link TagValue} entities.
 * Provides CRUD operations and custom query methods for tag values.
 * Extends {@link JpaRepository} to inherit standard JPA operations.
 */
public interface TagValueRepository extends JpaRepository<TagValue, Long> {

    /**
     * Checks if a TagValue with the specified value and tag type ID already exists.
     *
     * @param value the value to check for existence
     * @param tagTypeId the ID of the associated tag type
     * @return {@code true} if a TagValue with the given value and tag type ID exists,
     *         {@code false} otherwise
     */
    boolean existsByValueAndTagTypeId( String value, Long tagTypeId);

    /**
     * Retrieves all TagValue entities with their associated TagType eagerly fetched,
     * ordered alphabetically by the TagType name in ascending order.
     *
     * @return a {@link List} of all TagValue entities ordered by their associated TagType name
     */
    @Query("SELECT t FROM TagValue t JOIN FETCH t.tagType ORDER BY t.tagType.name ASC")
    List<TagValue> findAllOrderedByTagType();
}
