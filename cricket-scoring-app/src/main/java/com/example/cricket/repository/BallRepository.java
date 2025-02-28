package com.example.cricket.repository;

import com.example.cricket.entity.Ball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallRepository extends JpaRepository<Ball, Long> {
    List<Ball> findByOverId(Long overId);
    List<Ball> findByBatsman(String batsman);
}
