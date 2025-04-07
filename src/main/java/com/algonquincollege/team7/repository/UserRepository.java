package com.algonquincollege.team7.repository;

import com.algonquincollege.team7.model.User;
import com.algonquincollege.team7.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * Repository interface for {@link User} entities.
 * Provides CRUD operations and custom query methods for user management.
 * Extends {@link JpaRepository} to inherit standard JPA operations.
 * Includes security-related user lookup methods.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by email and returns it as Spring Security's {@link UserDetails}.
     * Primarily used for authentication purposes.
     *
     * @param email the email address to search for
     * @return the {@link UserDetails} implementation for the found user
     */
    UserDetails findByEmail(String email);

    /**
     * Checks if a user with the specified email exists in the system.
     *
     * @param email the email address to check
     * @return true if a user with the email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Checks if a user with the specified ID exists in the system.
     *
     * @param id the user ID to check
     * @return true if a user with the ID exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Checks if a user with the specified ID and type exists in the system.
     * Uses a custom JPQL query for the check.
     *
     * @param id the user ID to check
     * @param type the {@link UserType} to verify
     * @return true if a user with the specified ID and type exists, false otherwise
     */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.id = :id AND u.type = :type")
    boolean existsByIdAndType(@Param("id") Long id, @Param("type") UserType type);

    /**
     * Finds a user by email and password combination.
     * Note: Consider using Spring Security's password encoding instead of raw password comparison.
     *
     * @param email the user's email address
     * @param password the user's raw password (consider hashing)
     * @return an {@link Optional} containing the user if found, empty otherwise
     */
    Optional<User> findByEmailAndPassword(String email, String password);
}
