package com.example.cricket.service;

import com.example.cricket.entity.Performance;
import com.example.cricket.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    // Create or Save a Performance record
    public Performance createPerformance(Performance performance) {
        return performanceRepository.save(performance);
    }

    // Retrieve all performance records
    public List<Performance> getAllPerformances() {
        return performanceRepository.findAll();
    }

    // Retrieve a specific performance record by ID
    public Optional<Performance> getPerformanceById(Long id) {
        return performanceRepository.findById(id);
    }

    // Retrieve performance records by player ID
    public List<Performance> getPerformancesByPlayerId(Long playerId) {
        return performanceRepository.findByPlayerId(playerId);
    }

    // Update performance (useful if a correction is needed after match review)
    public Performance updatePerformance(Long id, Performance updatedPerformance) {
        return performanceRepository.findById(id).map(performance -> {
            performance.setRuns(updatedPerformance.getRuns());
            performance.setBallsFaced(updatedPerformance.getBallsFaced());
            performance.setWickets(updatedPerformance.getWickets());
            performance.setOversBowled(updatedPerformance.getOversBowled());
            return performanceRepository.save(performance);
        }).orElse(null);
    }

    // Delete a performance record (if needed)
    public void deletePerformance(Long id) {
        performanceRepository.deleteById(id);
    }
}
