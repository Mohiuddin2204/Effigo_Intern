package com.example.ols_backend_spring.coursematerial.entity;




import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "ols")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    @Id
    @GeneratedValue
    private Long courseId;

    private String courseName;
    private String courseDescription;
    private Double coursePrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("courses")
    private Category category;

/*
no need of manytomany table : users and courses, as users will be adding any course in orders one
many to many should be in like books and authors : as adding different authors in books
AND UNLIKELY HERE in our complex project: we are not adding courses in users table and nor users in courses table

    */

    
    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"course", "user"})  // Ignore course and user references in Order
    private List<Order> orders = new ArrayList<>();
}
