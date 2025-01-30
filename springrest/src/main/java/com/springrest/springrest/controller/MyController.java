package com.springrest.springrest.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.services.CourseService;

@RestController
@CrossOrigin(origins="*")
public class MyController {
	@Autowired
	private CourseService courseService;
	@GetMapping("/home")
	public String home() {
		return "home page";
	}
	
	//get courses
	@GetMapping("/courses")
	public List<Course> getCourses(){
		return this.courseService.getCourses();
	}
	@GetMapping("/courses/{courseId}")
	public Course getCourse(@PathVariable String courseId) {
		return this.courseService.getCourse(Long.parseLong(courseId));
	}
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course course) {
		return this.courseService.addCourse(course);
	}
	
	@PutMapping("/courses/{courseId}")
	public Course updateCourse(@RequestBody Course course, @PathVariable String courseId) {
	    return this.courseService.updateCourse(Long.parseLong(courseId), course);
	}

	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId) {
	    try {
	        this.courseService.deleteCourse(Long.parseLong(courseId)); // Try deleting the course
	        return new ResponseEntity<>(HttpStatus.OK); // Return 200 if successful
	    } catch (NoSuchElementException e) { // Catch exception for course not found
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if course doesn't exist
	    } catch (Exception e) { // Catch other exceptions
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 for general errors
	    }
	}

}