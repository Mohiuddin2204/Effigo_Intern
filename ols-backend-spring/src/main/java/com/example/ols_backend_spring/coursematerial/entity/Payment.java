package com.example.ols_backend_spring.coursematerial.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "ols")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue
    private Long payId;

    private String payMethod;
    private Double payAmount;
    private String payStatus;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("payment")  // Prevent Order → Payment → Order loop
    private Order order;
}