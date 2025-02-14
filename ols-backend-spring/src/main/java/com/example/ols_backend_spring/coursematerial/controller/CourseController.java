package com.example.ols_backend_spring.coursematerial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ols_backend_spring.coursematerial.entity.Course;
import com.example.ols_backend_spring.coursematerial.service.CourseService;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping({"/auth/user/api/courses", "/auth/admin/api/courses"})
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/auth/admin/api/courses/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId){
        Course course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }

    @PostMapping("/auth/admin/api/courses")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @DeleteMapping("/auth/admin/api/courses/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId){
        courseService.deleteCourseById(courseId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/auth/admin/api/courses/{courseId}/assign/{catId}")
    public ResponseEntity<Course> assignCourseInCategory(@PathVariable Long courseId, @PathVariable Long catId){
        Course updatedCourse = courseService.addCourseToCategory(courseId, catId);
        return ResponseEntity.ok(updatedCourse);
    }

    // update course price
    @PatchMapping("/auth/admin/api/courses/{courseId}/price")
    public ResponseEntity<Void> updateCoursePrice(@PathVariable Long courseId, @RequestParam Double newPrice){
        courseService.updateCoursePrice(courseId, newPrice);
        return ResponseEntity.ok().build();
    }
}