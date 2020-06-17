package com.studies.jpaquerydsl.services;

import java.util.List;
import java.util.Optional;

import com.studies.jpaquerydsl.exceptions.EntityNotFoundException;
import com.studies.jpaquerydsl.models.Students;

public interface StudentsService {
	
	/**
	 * Receives ID and returns Student instance if exists 
	 * @param id
	 * @return Optional<Student>
 	 */
	Optional<Students> findById(Integer id);
	
	/**
	 * Return all students instances
	 * @return List<Students>
	 */
	List<Students> findAll();
	
	/**
	 * Receives a student instance and save in database
	 * @param student
	 */
	Students save(Students student);
	
	/**
	 * Receives ID and student instance and update in database
	 * @param student
	 * @return Student
	 * @throws EntityNotFoundException 
	 */
	Students update(Integer id, Students student) throws EntityNotFoundException;
	
	/**
	 * Receives ID and remove student from database
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * Receives ID and return student instance if exists, else throw EntityNotFoundException
	 * @param studentId
	 * @return Student
	 */
	Students findAndValidateStudent(Integer studentId);
	
}
