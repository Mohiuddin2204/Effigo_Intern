package com.example.cricket.service;

import com.example.cricket.entity.Team;
import com.example.cricket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    // Create or Add new Team
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    // Get all Teams
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Get Team by ID
    public Optional<Team> getTeamById(Long teamId) {
        return teamRepository.findById(teamId);
    }

    // Get Team by Name
    public Team getTeamByName(String teamName) {
        return teamRepository.findByTeamName(teamName);
    }

    // Update Team details (like team name)
    public Team updateTeam(Long teamId, Team updatedTeam) {
        return teamRepository.findById(teamId).map(team -> {
            team.setTeamName(updatedTeam.getTeamName());
            return teamRepository.save(team);
        }).orElse(null);
    }

    // Delete Team by ID
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
