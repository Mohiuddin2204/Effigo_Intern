package com.example.cricket.controller;

import com.example.cricket.entity.Player;
import com.example.cricket.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // Add new player
    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player savedPlayer = playerService.addPlayer(player);
        return ResponseEntity.ok(savedPlayer);
    }

    // ✅ Add multiple players at once
    @PostMapping("/bulk")
    public ResponseEntity<List<Player>> addPlayers(@RequestBody List<Player> players) {
        List<Player> savedPlayers = playerService.addPlayers(players);
        return ResponseEntity.ok(savedPlayers);
    }

    // Get all players
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    // Get player by ID
    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long playerId) {
        Optional<Player> player = playerService.getPlayerById(playerId);
        return player.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get players by team ID
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Player>> getPlayersByTeamId(@PathVariable Long teamId) {
        List<Player> players = playerService.getPlayersByTeamId(teamId);
        return ResponseEntity.ok(players);
    }

    // Get players by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Player>> getPlayersByRole(@PathVariable String role) {
        List<Player> players = playerService.getPlayersByRole(role);
        return ResponseEntity.ok(players);
    }

    // Update player details
    @PutMapping("/{playerId}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long playerId, @RequestBody Player updatedPlayer) {
        Player player = playerService.updatePlayer(playerId, updatedPlayer);
        if (player != null) {
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete player by ID
    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.noContent().build();
    }
}
