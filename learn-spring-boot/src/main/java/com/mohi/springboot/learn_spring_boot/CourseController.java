package com.mohi.springboot.learn_spring_boot;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:9090/courses		


@RestController
public class CourseController {
	@RequestMapping("/courses")
	public List<Course> retrieveCourses(){
		return Arrays.asList(
				new Course(1,"springboot","mohi"),
				new Course(2,"spring","mohi"),
				new Course(3,"sb","mohi")
				);
	}
}