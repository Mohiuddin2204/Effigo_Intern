package com.example.cricket.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "innings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Innings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference // prevents infinite loop
    private Match match;

    private String battingTeam;
    private int totalRuns;
    private int totalWickets;
    private int totalOvers;

    @OneToMany(mappedBy = "innings", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // manages overs list
    private List<Over> overs = new ArrayList<>();
}
