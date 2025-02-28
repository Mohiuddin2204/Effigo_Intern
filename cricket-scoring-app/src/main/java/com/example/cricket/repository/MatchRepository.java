package com.example.cricket.repository;

import com.example.cricket.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByVenue(String venue);
    List<Match> findByMatchDate(java.time.LocalDate matchDate);
}
