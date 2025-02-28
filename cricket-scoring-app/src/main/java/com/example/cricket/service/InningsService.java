package com.example.cricket.service;

import com.example.cricket.entity.Innings;
import com.example.cricket.repository.InningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InningsService {

    @Autowired
    private InningsRepository inningsRepository;

    // Create a new innings
    public Innings createInnings(Innings innings) {
        return inningsRepository.save(innings);
    }

    // Get all innings
    public List<Innings> getAllInnings() {
        return inningsRepository.findAll();
    }

    // Get innings by ID
    public Optional<Innings> getInningsById(Long inningsId) {
        return inningsRepository.findById(inningsId);
    }

    // Get innings by match ID
    public List<Innings> getInningsByMatchId(Long matchId) {
        return inningsRepository.findByMatchId(matchId);
    }

    // Update innings details
    public Innings updateInnings(Long inningsId, Innings updatedInnings) {
        return inningsRepository.findById(inningsId).map(innings -> {
            innings.setBattingTeam(updatedInnings.getBattingTeam());
            innings.setTotalRuns(updatedInnings.getTotalRuns());
            innings.setTotalWickets(updatedInnings.getTotalWickets());
            innings.setTotalOvers(updatedInnings.getTotalOvers());
            return inningsRepository.save(innings);
        }).orElse(null);
    }

    // Delete an innings
    public void deleteInnings(Long inningsId) {
        inningsRepository.deleteById(inningsId);
    }
}
