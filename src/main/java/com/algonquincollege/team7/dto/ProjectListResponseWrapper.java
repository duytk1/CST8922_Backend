package com.algonquincollege.team7.dto;

import java.util.List;

/**
 * Wrapper class for paginated project list responses.
 *
 * Contains both the paginated project data and metadata about the pagination state.
 * This structure follows REST pagination best practices for consistent API responses.
 *
 * @param projects List of projects for the current page
 * @param page Current page number (0-indexed)
 * @param size Number of items per page
 * @param totalElements Total number of projects across all pages
 * @param totalPages Total number of pages available
 * @see ProjectListResponse
 * @since 1.0
 */
public record ProjectListResponseWrapper(
        List<ProjectListResponse> projects,
        int page,
        int size,
        long totalElements,
        int totalPages
) {}
