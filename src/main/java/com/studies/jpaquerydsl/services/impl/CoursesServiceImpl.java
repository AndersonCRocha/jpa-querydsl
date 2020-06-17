package com.studies.jpaquerydsl.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studies.jpaquerydsl.daos.CoursesDAO;
import com.studies.jpaquerydsl.exceptions.EntityNotFoundException;
import com.studies.jpaquerydsl.models.Courses;
import com.studies.jpaquerydsl.services.CoursesService;

@Service
public class CoursesServiceImpl implements CoursesService {

	private final static Logger LOG = LoggerFactory.getLogger(CoursesServiceImpl.class);
	
	@Autowired
	private CoursesDAO coursesDAO;
	
	@Override
	public Optional<Courses> findById(Integer id) {
		
		LOG.info("Searching course by ID : {}", id);
		return coursesDAO.findById(id);
	}

	@Override
	public List<Courses> findAll() {
		
		LOG.info("Searching all courses");
		return coursesDAO.findAll();
	}
	
	@Override
	public Courses save(Courses course) {
		
		LOG.info("Saving course : {}", course);
		return coursesDAO.save(course);
	}

	@Override
	public Courses update(Integer id, Courses course) throws EntityNotFoundException {
		
		LOG.info("Updating course with ID: {}, {}", id, course);
		findAndValidateCourse(id);
		course.setId(id);
		
		return coursesDAO.update(course);
	}

	@Override
	public void delete(Integer id) {

		LOG.info("Deleting course with ID: {}", id);
		findAndValidateCourse(id);
		
		coursesDAO.delete(id);
	}
	
	public Courses findAndValidateCourse(Integer courseId){
		Optional<Courses> courseOpt = coursesDAO.findById(courseId);
		
		if(!courseOpt.isPresent()) {
			LOG.error("Course not found for ID : {}", courseId);
			throw new EntityNotFoundException("Course not found for ID : "+courseId);
		}
		return courseOpt.get();
	}
	
}
