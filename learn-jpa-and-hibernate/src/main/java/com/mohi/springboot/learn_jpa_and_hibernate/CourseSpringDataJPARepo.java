package com.mohi.springboot.learn_jpa_and_hibernate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataJPARepo extends JpaRepository<Course,Long> {
	List<Course> findByAuthor(String author);
}
