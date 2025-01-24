package com.mohi.springboot.learn_jpa_and_hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseJPARepo {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Course course) {
		entityManager.merge(course);
	}
	public Course selectById(long id) {
		return entityManager.find(Course.class,id);
	}
	public void deleteById(long id) {
		Course course=entityManager.find(Course.class,id);
		entityManager.remove(course);
	}
}
