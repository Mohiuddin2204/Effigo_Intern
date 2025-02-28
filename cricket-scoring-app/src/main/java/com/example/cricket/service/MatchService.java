package com.example.cricket.service;

import com.example.cricket.entity.Match;
import com.example.cricket.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    // Create a new match
    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    // Get all matches
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    // Get a match by ID
    public Optional<Match> getMatchById(Long matchId) {
        return matchRepository.findById(matchId);
    }

    // Get matches by venue
    public List<Match> getMatchesByVenue(String venue) {
        return matchRepository.findByVenue(venue);
    }

    // Get matches by date
    public List<Match> getMatchesByDate(LocalDate date) {
        return matchRepository.findByMatchDate(date);
    }

   

    // Delete a match
    public void deleteMatch(Long matchId) {
        matchRepository.deleteById(matchId);
    }
}
