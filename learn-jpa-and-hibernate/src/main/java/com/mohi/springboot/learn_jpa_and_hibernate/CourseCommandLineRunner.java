package com.mohi.springboot.learn_jpa_and_hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {
	//@Autowired
	//private CourseJDBCRepo repository;
	
	//@Autowired
	//private CourseJPARepo repository;
	
	@Autowired
	private CourseSpringDataJPARepo repository;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		repository.save(new Course(4,"jjdbcc","mmmohii"));
		repository.save(new Course(3,"jjdbcc","mdmohii"));
		repository.deleteById(3l);
		System.out.println(repository.findById(4l));
		System.out.println(repository.findAll());
		System.out.println(repository.count());
		System.out.println(repository.findByAuthor("mmmohii"));
	}
}