package com.example.cricket.controller;

import com.example.cricket.entity.Ball;
import com.example.cricket.service.BallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/balls")
public class BallController {

    @Autowired
    private BallService ballService;

    // Create a new ball (add delivery to an over)
    @PostMapping
    public ResponseEntity<Ball> createBall(@RequestBody Ball ball) {
        Ball savedBall = ballService.createBall(ball);
        return ResponseEntity.ok(savedBall);
    }
    
    @PostMapping("/bulk")
    public ResponseEntity<List<Ball>> createBalls(@RequestBody List<Ball> balls) {
        List<Ball> savedBalls = ballService.createBalls(balls);
        return ResponseEntity.ok(savedBalls);
    }

    // Get all balls
    @GetMapping
    public ResponseEntity<List<Ball>> getAllBalls() {
        List<Ball> balls = ballService.getAllBalls();
        return ResponseEntity.ok(balls);
    }

    // Get a ball by ID
    @GetMapping("/{ballId}")
    public ResponseEntity<Ball> getBallById(@PathVariable Long ballId) {
        Optional<Ball> ball = ballService.getBallById(ballId);
        return ball.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all balls by over ID
    @GetMapping("/over/{overId}")
    public ResponseEntity<List<Ball>> getBallsByOverId(@PathVariable Long overId) {
        List<Ball> balls = ballService.getBallsByOverId(overId);
        return ResponseEntity.ok(balls);
    }

    // Get all balls faced by a particular batsman
    @GetMapping("/batsman/{batsman}")
    public ResponseEntity<List<Ball>> getBallsByBatsman(@PathVariable String batsman) {
        List<Ball> balls = ballService.getBallsByBatsman(batsman);
        return ResponseEntity.ok(balls);
    }

    // Update a ball delivery (if correction is needed)
    @PutMapping("/{ballId}")
    public ResponseEntity<Ball> updateBall(@PathVariable Long ballId, @RequestBody Ball updatedBall) {
        Ball ball = ballService.updateBall(ballId, updatedBall);
        if (ball != null) {
            return ResponseEntity.ok(ball);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a ball (e.g., if wrong delivery entry is made)
    @DeleteMapping("/{ballId}")
    public ResponseEntity<Void> deleteBall(@PathVariable Long ballId) {
        ballService.deleteBall(ballId);
        return ResponseEntity.noContent().build();
    }
}
