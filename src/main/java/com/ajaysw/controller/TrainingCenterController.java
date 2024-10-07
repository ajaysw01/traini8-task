package com.ajaysw.controller;

import com.ajaysw.exception.ResourceNotFoundException;
import com.ajaysw.model.TrainingCenter;
import com.ajaysw.service.TrainingCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-centers")
@Tag(name = "Training Center", description = "Training Center management APIs")
public class TrainingCenterController {

    @Autowired
    private TrainingCenterService trainingCenterService;

    @Operation(summary = "Create a new Training Center")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Training Center created",
                    content = @Content(schema = @Schema(implementation = TrainingCenter.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<TrainingCenter> createTrainingCenter(
            @Valid @RequestBody TrainingCenter trainingCenter) {
        TrainingCenter savedCenter = trainingCenterService.createTrainingCenter(trainingCenter);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCenter);
    }

    @Operation(summary = "Get all Training Centers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Training Centers",
                    content = @Content(schema = @Schema(implementation = TrainingCenter.class)))
    })
    @GetMapping
    public ResponseEntity<List<TrainingCenter>> getAllTrainingCenters(
            @Parameter(description = "City to filter by") @RequestParam(value = "city", required = false) String city,
            @Parameter(description = "State to filter by") @RequestParam(value = "state", required = false) String state) {
        List<TrainingCenter> centers = trainingCenterService.getAllTrainingCenters(city, state);
        return ResponseEntity.ok(centers);
    }

    @Operation(summary = "Get a Training Center by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Training Center",
                    content = @Content(schema = @Schema(implementation = TrainingCenter.class))),
            @ApiResponse(responseCode = "404", description = "Training Center not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrainingCenter> getTrainingCenterById(
            @Parameter(description = "id of Training Center to be searched") @PathVariable Long id) {
        TrainingCenter center = trainingCenterService.getTrainingCenterById(id);
        if (center == null) {
            throw new ResourceNotFoundException("Training center not found with ID: " + id);
        }
        return ResponseEntity.ok(center);
    }

    @Operation(summary = "Update a Training Center")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training Center updated",
                    content = @Content(schema = @Schema(implementation = TrainingCenter.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Training Center not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TrainingCenter> updateTrainingCenter(
            @Parameter(description = "id of Training Center to be updated") @PathVariable Long id,
            @Valid @RequestBody TrainingCenter trainingCenterDetails) {
        TrainingCenter updatedCenter = trainingCenterService.updateTrainingCenter(id, trainingCenterDetails);
        if (updatedCenter == null) {
            throw new ResourceNotFoundException("Training center not found with ID: " + id);
        }
        return ResponseEntity.ok(updatedCenter);
    }

    @Operation(summary = "Delete a Training Center")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Training Center deleted"),
            @ApiResponse(responseCode = "404", description = "Training Center not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingCenter(
            @Parameter(description = "id of Training Center to be deleted") @PathVariable Long id) {
        trainingCenterService.deleteTrainingCenter(id);
        return ResponseEntity.noContent().build();
    }
}