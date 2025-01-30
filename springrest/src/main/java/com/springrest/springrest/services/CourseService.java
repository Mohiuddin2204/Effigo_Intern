package com.springrest.springrest.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springrest.springrest.entities.Course;

@Service
public interface CourseService {
	public List<Course> getCourses();

	Course getCourse(long courseId);

	public Course addCourse(Course course);
	
	Course updateCourse(long courseId, Course course);

	void deleteCourse(long courseId);

}
