package com.example.cricket.controller;

import com.example.cricket.entity.Innings;
import com.example.cricket.service.InningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/innings")
public class InningsController {

    @Autowired
    private InningsService inningsService;

    // Create a new innings
    @PostMapping
    public ResponseEntity<Innings> createInnings(@RequestBody Innings innings) {
        Innings savedInnings = inningsService.createInnings(innings);
        return ResponseEntity.ok(savedInnings);
    }

    // Get all innings
    @GetMapping
    public ResponseEntity<List<Innings>> getAllInnings() {
        List<Innings> inningsList = inningsService.getAllInnings();
        return ResponseEntity.ok(inningsList);
    }

    // Get innings by ID
    @GetMapping("/{inningsId}")
    public ResponseEntity<Innings> getInningsById(@PathVariable Long inningsId) {
        Optional<Innings> innings = inningsService.getInningsById(inningsId);
        return innings.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all innings by match ID
    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<Innings>> getInningsByMatchId(@PathVariable Long matchId) {
        List<Innings> inningsList = inningsService.getInningsByMatchId(matchId);
        return ResponseEntity.ok(inningsList);
    }

    // Update innings
    @PutMapping("/{inningsId}")
    public ResponseEntity<Innings> updateInnings(@PathVariable Long inningsId, @RequestBody Innings updatedInnings) {
        Innings innings = inningsService.updateInnings(inningsId, updatedInnings);
        if (innings != null) {
            return ResponseEntity.ok(innings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete innings
    @DeleteMapping("/{inningsId}")
    public ResponseEntity<Void> deleteInnings(@PathVariable Long inningsId) {
        inningsService.deleteInnings(inningsId);
        return ResponseEntity.noContent().build();
    }
}
