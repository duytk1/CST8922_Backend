package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.Semester;

/**
 * Data Transfer Object (DTO) for project listing requests with pagination and filtering.
 *
 * Provides parameters for paginated project listings with sorting and semester filtering capabilities.
 * Includes sensible defaults for all pagination parameters.
 *
 * @param page Page number (0-based index), defaults to 0
 * @param size Number of items per page, defaults to 10
 * @param sortBy Field to sort by, defaults to "createdAt"
 * @param sortDirection Sort direction ("ASC" or "DESC"), defaults to "DESC"
 * @param semesterFilter Optional semester filter to limit results
 * @see Semester
 * @since 1.0
 */
public record ProjectListRequest(
        Integer page,
        Integer size,
        String sortBy,
        String sortDirection,
        Semester semesterFilter
) {
    public ProjectListRequest {
        if (page == null) page = 0;
        if (size == null) size = 10;
        if (sortBy == null) sortBy = "createdAt";
        if (sortDirection == null) sortDirection = "DESC";
    }
}
