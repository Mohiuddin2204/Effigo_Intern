package com.example.cricket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "performances")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonIgnore // This breaks recursive loop if player references performances
    private Player player;

    private int runs;
    private int ballsFaced;
    private int wickets;
    private int oversBowled;
}
