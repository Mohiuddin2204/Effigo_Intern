package com.springrest.springrest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entities.Course;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	//List<Course> list;
	public CourseServiceImpl() {
	//	list=new ArrayList<>();
		//list.add(new Course(1,"java","learning"));
		//list.add(new Course(2,"spring","spring boot"));
	}
	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		//return list;
		return courseDao.findAll();
	}
	@Override
	public Course getCourse(long courseId) {
		// TODO Auto-generated method stub
	/*	Course c =null;
		for(Course course:list) {
			if(course.getId()==courseId) {
				c=course;
				break;
			}
		}
		return c;*/
		return courseDao.findById(courseId).get();
	}
	@Override
	public Course addCourse(Course course) {
		// TODO Auto-generated method stub
		//list.add(course);
		//return course;
		courseDao.save(course);
		return course;
	}
	@Override
	public Course updateCourse(long courseId, Course course) {
	/*    for (int i = 0; i < list.size(); i++) {
	        if (list.get(i).getId() == courseId) {
	            list.set(i, course);
	            return course;
	        }
	    }
	    return null;*/ // Return null if course ID is not found
		courseDao.save(course);
		return course;
	}

	@Override
	public void deleteCourse(long courseId) {
	 /*   boolean exists = list.stream().anyMatch(course -> course.getId() == courseId); // Check if course exists
	    if (!exists) {
	        throw new NoSuchElementException("Course with ID " + courseId + " not found"); // Throw exception if not found
	    }
	    // Filter the list to remove the course with the given ID
	    list = list.stream()
	               .filter(course -> course.getId() != courseId)
	               .collect(Collectors.toList()); */
	Course entity=courseDao.findById(courseId).get();
	courseDao.delete(entity);
	}


}
