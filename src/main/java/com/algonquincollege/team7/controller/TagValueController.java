package com.algonquincollege.team7.controller;

import com.algonquincollege.team7.dto.GeneralResponse;
import com.algonquincollege.team7.dto.TagListResponse;
import com.algonquincollege.team7.dto.TagValueRequest;
import com.algonquincollege.team7.service.TagValueService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing tag value operations.
 *
 * Provides endpoints for creating, updating, and retrieving tag values
 * (specific tags within tag types). Supports cross-origin requests from
 * the frontend application running on http://localhost:8080.
 *
 * @see TagValueService
 * @see TagValueRequest
 * @see TagListResponse
 * @since 1.0
 */
@RestController
@RequestMapping("api/tag_value")
@CrossOrigin(origins = "http://localhost:8080")
public class TagValueController {

    /**
     * Service layer for handling tag value business logic.
     */
    @Autowired
    private TagValueService tagValueService;

    /**
     * Registers a new tag value in the system.
     */
    @PostMapping
    public ResponseEntity registerTagValue(@RequestBody @Valid TagValueRequest data) {
        tagValueService.registerTagValue(data);

        var responseOk = new GeneralResponse("Tag value registered successfully");
        return ResponseEntity.ok(responseOk);
    }

    /**
     * Updates an existing tag value.
     */
    @Transactional
    @PutMapping
    public ResponseEntity editTagValue(@RequestBody @Valid TagValueRequest data) {

        tagValueService.editTagValue(data);
        var responseOk = new GeneralResponse("Tag Value edited successfully");
        return ResponseEntity.ok(responseOk);
    }

    /**
     * Retrieves all tag values in the system with their associated types.
     */
    @GetMapping
    public List<TagListResponse> getTagList() {
        return tagValueService.getTagList();
    }
}
