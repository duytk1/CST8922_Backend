package com.algonquincollege.team7.model;

/**
 * Represents the different types of users in the system.
 *
 * This enum defines the roles that determine a user's permissions
 * and capabilities within the application. Each type has distinct
 * access levels and functionality.
 *
 * @see User
 */
public enum UserType {
    ORGANIZATION,
    PROFESSOR,
    ADMIN
}