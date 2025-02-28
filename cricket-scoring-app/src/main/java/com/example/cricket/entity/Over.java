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
@Table(name = "overs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Over {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "innings_id")
    @JsonBackReference // prevents infinite loop
    private Innings innings;

    private int overNumber;

    @OneToMany(mappedBy = "over", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // manages balls list
    private List<Ball> balls = new ArrayList<>();
}
