package com.algonquincollege.team7.controller;

import com.algonquincollege.team7.dto.GeneralResponse;
import com.algonquincollege.team7.dto.TagTypeRequest;
import com.algonquincollege.team7.service.TagTypeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing tag type operations.
 *
 * Provides endpoints for creating and updating tag categories in the system.
 * Supports cross-origin requests from the frontend application.
 *
 * @see TagTypeService
 * @see TagTypeRequest
 * @since 1.0
 */
@RestController
@RequestMapping("api/tag_type")
@CrossOrigin(origins = "http://localhost:8080")
public class TagTypeController {

    /**
     * Service layer for handling tag type business logic.
     */
    @Autowired
    private TagTypeService tagTypeService;

    /**
     * Registers a new tag category in the system.
     */
    @PostMapping
    public ResponseEntity registerTagType(@RequestBody @Valid TagTypeRequest data) {
        tagTypeService.registerTagType(data);

        var responseOk = new GeneralResponse("Tag type registered successfully");
        return ResponseEntity.ok(responseOk);
    }

    /**
     * Updates an existing tag category.
     */
    @Transactional
    @PutMapping
    public ResponseEntity editTagType(@RequestBody @Valid TagTypeRequest data) {

        tagTypeService.editTagType(data);
        var responseOk = new GeneralResponse("Tag type edited successfully");
        return ResponseEntity.ok(responseOk);
    }
}
