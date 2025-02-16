package com.example.ols_backend_spring.coursematerial.entity;



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
public class User {
    @Id
    private Long userId;

    private String userName;
    private String userEmail;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")  // Prevent User → Order → User loop
    private List<Order> orders = new ArrayList<>();

/*
no need of manytomany table : users and courses, as users will be adding any course in orders one
many to many should be in like books and authors : as adding different authors in books
AND UNLIKELY HERE in our complex project: we are not adding courses in users table and nor users in courses table

    */

    
    @ManyToMany
    @JoinTable(
            name = "user_courses",
            schema = "ols",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnoreProperties("users")  // Prevent Course → User → Course loop
    private List<Course> courses = new ArrayList<>();
}
