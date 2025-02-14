package com.example.ols_backend_spring.coursematerial.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ols_backend_spring.coursematerial.entity.Category;
import com.example.ols_backend_spring.coursematerial.entity.Course;
import com.example.ols_backend_spring.coursematerial.entity.User;
import com.example.ols_backend_spring.coursematerial.repository.CategoryRepository;
import com.example.ols_backend_spring.coursematerial.repository.CourseRepository;
import com.example.ols_backend_spring.coursematerial.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get a course by its ID
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found by ID"));
    }

    // Create a new course
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Delete a course by ID (ensuring users' courses are updated)
    @Transactional
    public void deleteCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Remove course reference from users
        for (User user : course.getUsers()) {
            user.getCourses().remove(course);
        }
        userRepository.saveAll(course.getUsers());

        // Delete the course
        courseRepository.deleteById(courseId);
    }

    // Assign a course to a category
    public Course addCourseToCategory(Long courseId, Long categoryId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (courseOptional.isPresent() && categoryOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setCategory(categoryOptional.get());
            return courseRepository.save(course);
        } else {
            throw new RuntimeException("Course or category not found. Check IDs.");
        }
    }

    // Update course price
    public void updateCoursePrice(Long courseId, Double newPrice) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setCoursePrice(newPrice);
        courseRepository.save(course);
    }
}
