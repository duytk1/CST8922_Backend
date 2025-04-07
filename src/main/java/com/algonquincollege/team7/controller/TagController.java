package com.algonquincollege.team7.controller;

import com.algonquincollege.team7.dto.GeneralResponse;
import com.algonquincollege.team7.dto.TagRequest;
import com.algonquincollege.team7.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing project tag associations.
 *
 * Provides endpoints for creating and removing tags on projects, with validation
 * to ensure proper authorization and data integrity. Supports cross-origin requests
 * from the frontend application running on http://localhost:8080.
 *
 * @see TagService
 * @see TagRequest
 * @since 1.0
 */
@RestController
@RequestMapping("api/project/tag")
@CrossOrigin(origins = "http://localhost:8080")
public class TagController {

    /**
     * Service layer for handling tag-related business logic.
     */
    @Autowired
    private TagService tagService;

    /**
     * Creates a new tag association between a project and a tag value.
     */
    @PostMapping
    public ResponseEntity registerTag(@RequestBody @Valid TagRequest data) {
        tagService.registerTag(data);

        var responseOk = new GeneralResponse("Tag registered successfully");
        return ResponseEntity.ok(responseOk);
    }

    /**
     * Removes a tag association from the system.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);

        var responseOk = new GeneralResponse("Tag deleted successfully");
        return ResponseEntity.ok(responseOk);
    }
}
