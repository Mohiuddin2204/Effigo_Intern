package com.example.cricket.controller;

import com.example.cricket.entity.Performance;
import com.example.cricket.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/performances")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    // Create a new performance record
    @PostMapping
    public ResponseEntity<Performance> createPerformance(@RequestBody Performance performance) {
        Performance savedPerformance = performanceService.createPerformance(performance);
        return ResponseEntity.ok(savedPerformance);
    }

    // Get all performance records
    @GetMapping
    public ResponseEntity<List<Performance>> getAllPerformances() {
        List<Performance> performances = performanceService.getAllPerformances();
        return ResponseEntity.ok(performances);
    }

    // Get a performance record by ID
    @GetMapping("/{id}")
    public ResponseEntity<Performance> getPerformanceById(@PathVariable Long id) {
        Optional<Performance> performance = performanceService.getPerformanceById(id);
        return performance.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get performances for a specific player
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<Performance>> getPerformancesByPlayerId(@PathVariable Long playerId) {
        List<Performance> performances = performanceService.getPerformancesByPlayerId(playerId);
        return ResponseEntity.ok(performances);
    }

    // Update an existing performance record
    @PutMapping("/{id}")
    public ResponseEntity<Performance> updatePerformance(@PathVariable Long id, @RequestBody Performance updatedPerformance) {
        Performance performance = performanceService.updatePerformance(id, updatedPerformance);
        if (performance != null) {
            return ResponseEntity.ok(performance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a performance record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformance(@PathVariable Long id) {
        performanceService.deletePerformance(id);
        return ResponseEntity.noContent().build();
    }
}
