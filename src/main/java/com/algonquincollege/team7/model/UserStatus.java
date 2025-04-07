package com.algonquincollege.team7.model;

/**
 * Represents the possible statuses of a user account in the system.
 *
 * This enum defines the lifecycle states that a {@link User} account can be in,
 * controlling access and functionality within the application.
 *
 * @see User
 */
public enum UserStatus {
    ACTIVE,
    PENDING,
    DELETED
}