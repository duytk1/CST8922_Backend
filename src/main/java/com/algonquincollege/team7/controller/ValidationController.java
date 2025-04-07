package com.algonquincollege.team7.controller;

import com.algonquincollege.team7.dto.GeneralResponse;
import com.algonquincollege.team7.dto.ValidationRequest;
import com.algonquincollege.team7.service.ValidationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing project validation operations.
 *
 * Provides endpoints for professors to submit and update project validations,
 * including feedback and approval status. Supports cross-origin requests from
 * the frontend application running on http://localhost:8080.
 *
 * @see ValidationService
 * @see ValidationRequest
 * @since 1.0
 */
@RestController
@RequestMapping("api/project/validation")
@CrossOrigin(origins = "http://localhost:8080")
public class ValidationController {

    /**
     * Service layer for handling validation business logic.
     */
    @Autowired
    private ValidationService validationService;

    /**
     * Registers a new project validation.
     */
    @PostMapping
    public ResponseEntity registerValidation(@RequestBody @Valid ValidationRequest data) {
        validationService.registerValidation(data);

        var responseOk = new GeneralResponse("Validation registered successfully");
        return ResponseEntity.ok(responseOk);
    }

    /**
     * Updates an existing project validation.
     */
    @Transactional
    @PutMapping
    public ResponseEntity editValidation(@RequestBody @Valid ValidationRequest data) {

        validationService.editValidation(data);
        var responseOk = new GeneralResponse("Validation edited successfully");
        return ResponseEntity.ok(responseOk);
    }
}
