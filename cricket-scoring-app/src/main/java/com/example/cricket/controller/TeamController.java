package com.example.cricket.controller;

import com.example.cricket.entity.Team;
import com.example.cricket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // Add new team
    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team savedTeam = teamService.addTeam(team);
        return ResponseEntity.ok(savedTeam);
    }

    // Get all teams
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    // Get team by ID
    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long teamId) {
        Optional<Team> team = teamService.getTeamById(teamId);
        return team.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get team by name
    @GetMapping("/name/{teamName}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String teamName) {
        Team team = teamService.getTeamByName(teamName);
        if (team != null) {
            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update team details
    @PutMapping("/{teamId}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long teamId, @RequestBody Team updatedTeam) {
        Team team = teamService.updateTeam(teamId, updatedTeam);
        if (team != null) {
            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete team by ID
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }
}
