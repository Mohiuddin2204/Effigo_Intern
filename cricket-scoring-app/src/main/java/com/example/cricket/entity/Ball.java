package com.example.cricket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "balls")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Ball {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "over_id")
    @JsonBackReference // prevents infinite loop
    private Over over;

    private int ballNumber;
    private String batsman;
    private String bowler;
    private int runs;
    private boolean isWicket;
    private String extra;  // "NB", "WD", "LB", "B"
}
