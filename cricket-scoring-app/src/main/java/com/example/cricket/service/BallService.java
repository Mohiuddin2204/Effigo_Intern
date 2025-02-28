package com.example.cricket.service;

import com.example.cricket.entity.Ball;
import com.example.cricket.repository.BallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BallService {

    @Autowired
    private BallRepository ballRepository;

    public Ball createBall(Ball ball) {
        if (ball.getOver() == null || ball.getOver().getId() == null) {
            throw new IllegalArgumentException("Over ID is required to add a ball.");
        }

        Long overId = ball.getOver().getId();

        // Fetch all balls for this over
        List<Ball> existingBalls = ballRepository.findByOverId(overId);

        // Calculate legal deliveries (excluding NB and WD)
        long legalDeliveries = existingBalls.stream()
            .filter(existingBall -> !isExtraRebowl(existingBall.getExtra()))
            .count();

        if (!isExtraRebowl(ball.getExtra())) {
            legalDeliveries++;  // This ball counts if it's not NB/WD
        }

        // Enforce 6 legal delivery limit
        if (legalDeliveries > 6) {
            throw new IllegalStateException("Over already has 6 legal deliveries. Cannot add more.");
        }

        return ballRepository.save(ball);
    }

    public List<Ball> createBalls(List<Ball> balls) {
        if (balls == null || balls.isEmpty()) {
            throw new IllegalArgumentException("Ball list cannot be empty.");
        }

        // Group balls by overId
        Map<Long, List<Ball>> ballsByOver = balls.stream()
                .filter(ball -> ball.getOver() != null && ball.getOver().getId() != null)
                .collect(Collectors.groupingBy(ball -> ball.getOver().getId()));

        // Validate each over
        for (Map.Entry<Long, List<Ball>> entry : ballsByOver.entrySet()) {
            Long overId = entry.getKey();
            List<Ball> newBallsForOver = entry.getValue();

            // Fetch already existing balls for this over from DB
            List<Ball> existingBalls = ballRepository.findByOverId(overId);

            // Combine existing and new balls for this over
            List<Ball> combinedBalls = new ArrayList<>(existingBalls);
            combinedBalls.addAll(newBallsForOver);

            // Calculate legal deliveries
            long legalDeliveries = combinedBalls.stream()
                    .filter(ball -> !isExtraRebowl(ball.getExtra()))
                    .count();

            if (legalDeliveries > 6) {
                throw new IllegalStateException(
                        "Over " + overId + " exceeds 6 legal deliveries. Found " + legalDeliveries + " legal balls."
                );
            }
        }

        // All overs passed the check, save all balls
        return ballRepository.saveAll(balls);
    }

    private boolean isExtraRebowl(String extra) {
        if (extra == null) return false;
        return extra.equalsIgnoreCase("NB") || extra.equalsIgnoreCase("WD");
    }

    // Get all balls
    public List<Ball> getAllBalls() {
        return ballRepository.findAll();
    }

    // Get a ball by ID
    public Optional<Ball> getBallById(Long ballId) {
        return ballRepository.findById(ballId);
    }

    // Get all balls by over ID
    public List<Ball> getBallsByOverId(Long overId) {
        return ballRepository.findByOverId(overId);
    }

    // Get all balls faced by a particular batsman
    public List<Ball> getBallsByBatsman(String batsman) {
        return ballRepository.findByBatsman(batsman);
    }

    // Update a ball delivery (optional, if you want to allow correcting entries)
    public Ball updateBall(Long ballId, Ball updatedBall) {
        return ballRepository.findById(ballId).map(ball -> {
            ball.setBallNumber(updatedBall.getBallNumber());
            ball.setBatsman(updatedBall.getBatsman());
            ball.setBowler(updatedBall.getBowler());
            ball.setRuns(updatedBall.getRuns());
            ball.setWicket(updatedBall.isWicket());
            ball.setExtra(updatedBall.getExtra());
            return ballRepository.save(ball);
        }).orElse(null);
    }

    // Delete a ball (e.g., if wrong delivery entry is made)
    public void deleteBall(Long ballId) {
        ballRepository.deleteById(ballId);
    }
}
