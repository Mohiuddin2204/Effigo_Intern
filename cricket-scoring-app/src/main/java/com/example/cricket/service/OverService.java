package com.example.cricket.service;

import com.example.cricket.entity.Over;
import com.example.cricket.repository.OverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OverService {

    @Autowired
    private OverRepository overRepository;

    // Create a new over
    public Over createOver(Over over) {
        return overRepository.save(over);
    }
    public List<Over> createBulkOvers(List<Over> overs) {
        return overRepository.saveAll(overs);
    }

    // Get all overs
    public List<Over> getAllOvers() {
        return overRepository.findAll();
    }

    // Get over by ID
    public Optional<Over> getOverById(Long overId) {
        return overRepository.findById(overId);
    }

    // Get overs by innings ID
    public List<Over> getOversByInningsId(Long inningsId) {
        return overRepository.findByInningsId(inningsId);
    }

    // Update over details (e.g., overNumber can be updated)
    public Over updateOver(Long overId, Over updatedOver) {
        return overRepository.findById(overId).map(over -> {
            over.setOverNumber(updatedOver.getOverNumber());
            // Note: If you want to update balls list, you would handle it at the BallService level
            return overRepository.save(over);
        }).orElse(null);
    }

    // Delete an over
    public void deleteOver(Long overId) {
        overRepository.deleteById(overId);
    }
}
