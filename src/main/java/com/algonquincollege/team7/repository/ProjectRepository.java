package com.algonquincollege.team7.repository;

import com.algonquincollege.team7.model.Project;
import com.algonquincollege.team7.model.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Project} entities.
 * Provides CRUD operations and custom query methods for projects.
 * Extends {@link JpaRepository} to inherit standard JPA operations.
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Finds all projects associated with a specific organization, with pagination support.
     *
     * @param organizationId the ID of the organization to filter projects by
     * @param pageable the pagination information (page number, page size, sorting)
     * @return a {@link Page} of projects belonging to the specified organization
     */
    Page<Project> findByOrganizationId(Long organizationId, Pageable pageable);

    /**
     * Finds all projects associated with a specific organization and semester, with pagination support.
     *
     * @param organizationId the ID of the organization to filter projects by
     * @param semester the semester to filter projects by
     * @param pageable the pagination information (page number, page size, sorting)
     * @return a {@link Page} of projects matching the specified organization and semester
     */
    Page<Project> findByOrganizationIdAndSemester(Long organizationId, Semester semester, Pageable pageable);
}
