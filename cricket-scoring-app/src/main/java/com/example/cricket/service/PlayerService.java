package com.example.cricket.service;

import com.example.cricket.entity.Player;
import com.example.cricket.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    // Create or Add new Player
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    // ✅ Bulk add players
    public List<Player> addPlayers(List<Player> players) {
        return playerRepository.saveAll(players);
    }

    // Fetch all players
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Get player by ID
    public Optional<Player> getPlayerById(Long playerId) {
        return playerRepository.findById(playerId);
    }

    // Get players by Team ID
    public List<Player> getPlayersByTeamId(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    // Get players by Role (Batsman, Bowler, All-Rounder)
    public List<Player> getPlayersByRole(String role) {
        return playerRepository.findByRole(role);
    }

    // Update player details (e.g., name, role)
    public Player updatePlayer(Long playerId, Player updatedPlayer) {
        return playerRepository.findById(playerId).map(player -> {
            player.setName(updatedPlayer.getName());
            player.setRole(updatedPlayer.getRole());
            player.setTeam(updatedPlayer.getTeam());
            return playerRepository.save(player);
        }).orElse(null);
    }

    // Delete player by ID
    public void deletePlayer(Long playerId) {
        playerRepository.deleteById(playerId);
    }
}
