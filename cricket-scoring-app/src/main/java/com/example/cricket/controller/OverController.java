package com.example.cricket.controller;

import com.example.cricket.entity.Over;
import com.example.cricket.service.OverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/overs")
public class OverController {

    @Autowired
    private OverService overService;

    // Create a new over
    @PostMapping
    public ResponseEntity<Over> createOver(@RequestBody Over over) {
        Over savedOver = overService.createOver(over);
        return ResponseEntity.ok(savedOver);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<Over>> createBulkOvers(@RequestBody List<Over> overs) {
        List<Over> savedOvers = overService.createBulkOvers(overs);
        return ResponseEntity.ok(savedOvers);
    }

    // Get all overs
    @GetMapping
    public ResponseEntity<List<Over>> getAllOvers() {
        List<Over> overs = overService.getAllOvers();
        return ResponseEntity.ok(overs);
    }

    // Get over by ID
    @GetMapping("/{overId}")
    public ResponseEntity<Over> getOverById(@PathVariable Long overId) {
        Optional<Over> over = overService.getOverById(overId);
        return over.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all overs for a specific innings
    @GetMapping("/innings/{inningsId}")
    public ResponseEntity<List<Over>> getOversByInningsId(@PathVariable Long inningsId) {
        List<Over> overs = overService.getOversByInningsId(inningsId);
        return ResponseEntity.ok(overs);
    }

    // Update an over (only basic properties, not nested balls list here)
    @PutMapping("/{overId}")
    public ResponseEntity<Over> updateOver(@PathVariable Long overId, @RequestBody Over updatedOver) {
        Over over = overService.updateOver(overId, updatedOver);
        if (over != null) {
            return ResponseEntity.ok(over);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an over
    @DeleteMapping("/{overId}")
    public ResponseEntity<Void> deleteOver(@PathVariable Long overId) {
        overService.deleteOver(overId);
        return ResponseEntity.noContent().build();
    }
}
