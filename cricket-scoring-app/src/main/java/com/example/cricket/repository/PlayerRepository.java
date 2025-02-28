package com.example.cricket.repository;

import com.example.cricket.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long teamId);
    List<Player> findByRole(String role); // Batsman, Bowler, All-Rounder
}
