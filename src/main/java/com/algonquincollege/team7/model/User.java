package com.algonquincollege.team7.model;

import com.algonquincollege.team7.dto.SignupRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Represents a user entity in the system, implementing Spring Security's {@link UserDetails}.
 *
 * This class models all user types in the system (professors, organizations, etc.),
 * handling authentication, authorization, and user profile information. It serves as
 * the principal object for Spring Security authentication.
 *
 * @see UserDetails
 * @see UserType
 * @see UserStatus
 * @see SignupRequest
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "user")
public class User implements UserDetails {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User's email address, used as username for authentication.
     * Must be unique across all users.
     */
    private String email;

    /**
     * User's first name.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * User's last name.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Organization name (for organization-type users).
     * Null for non-organization users.
     */
    @Column(name = "organization_name")
    private String organizationName;

    /**
     * User's contact phone number.
     */
    private String phone;

    /**
     * Type of user (e.g., PROFESSOR, ORGANIZATION).
     * Determines role-based access and capabilities.
     */
    @Enumerated(EnumType.STRING)
    private UserType type;

    /**
     * Encrypted password for authentication.
     * Should never be stored in plain text.
     */
    private String password;

    /**
     * Current status of the user account (ACTIVE, INACTIVE, etc.).
     */
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    /**
     * Timestamp of the last update to user information.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Constructs a new User from signup request data.
     * Initializes account as ACTIVE and sets creation timestamp.
     *
     * @param data the signup request DTO containing user information
     */
    public User(SignupRequest data) {
        this.email = data.email();
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.organizationName = data.organizationName();
        this.phone = data.phone();
        this.type = data.type();
        this.password = data.password();
        this.status = UserStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Returns the authorities granted to the user.
     * Currently grants ROLE_USER to all users by default.
     *
     * @return collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Returns the username used to authenticate the user.
     * Uses email address as the username.
     *
     * @return the email address
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true if account is valid (non-expired), false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * @return true if account is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     *
     * @return true if credentials are valid (non-expired), false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return true if user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
