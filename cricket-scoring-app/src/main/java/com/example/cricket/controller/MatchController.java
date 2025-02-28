package com.example.cricket.controller;

import com.example.cricket.entity.Match;
import com.example.cricket.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    // Create a new match
    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        Match savedMatch = matchService.createMatch(match);
        return ResponseEntity.ok(savedMatch);
    }

    // Get all matches
    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    // Get match by ID
    @GetMapping("/{matchId}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long matchId) {
        Optional<Match> match = matchService.getMatchById(matchId);
        return match.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get matches by venue
    @GetMapping("/venue/{venue}")
    public ResponseEntity<List<Match>> getMatchesByVenue(@PathVariable String venue) {
        List<Match> matches = matchService.getMatchesByVenue(venue);
        return ResponseEntity.ok(matches);
    }

    // Get matches by date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Match>> getMatchesByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Match> matches = matchService.getMatchesByDate(date);
        return ResponseEntity.ok(matches);
    }

    // Delete match by ID
    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long matchId) {
        matchService.deleteMatch(matchId);
        return ResponseEntity.noContent().build();
    }
}
