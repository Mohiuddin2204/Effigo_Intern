package com.example.cricket.repository;

import com.example.cricket.entity.Innings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InningsRepository extends JpaRepository<Innings, Long> {
    List<Innings> findByMatchId(Long matchId);
}
