package com.studies.jpaquerydsl.services;

import java.util.List;
import java.util.Optional;

import com.studies.jpaquerydsl.exceptions.EntityNotFoundException;
import com.studies.jpaquerydsl.models.Courses;

public interface CoursesService {
	
	/**
	 * Receives ID and returns Course instance if exists 
	 * @param id
	 * @return Optional<Course>
 	 */
	Optional<Courses> findById(Integer id);
	
	/**
	 * Return all courses instances
	 * @return List<Courses>
	 */
	List<Courses> findAll();
	
	/**
	 * Receives a course instance and save in database
	 * @param course
	 */
	Courses save(Courses course);
	
	/**
	 * Receives ID and course instance and update in database
	 * @param course
	 * @return Course
	 * @throws EntityNotFoundException 
	 */
	Courses update(Integer id, Courses course) throws EntityNotFoundException;
	
	/**
	 * Receives ID and remove course from database
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * Receives ID and return course instance if exists, else throw EntityNotFoundException
	 * @param courseId
	 * @return Course
	 */
	Courses findAndValidateCourse(Integer courseId);
	
}
