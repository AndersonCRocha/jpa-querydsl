package com.studies.jpaquerydsl.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.studies.jpaquerydsl.dtos.EnrollmentsDTO;
import com.studies.jpaquerydsl.models.Enrollments;

public interface EnrollmentsService {
	
	/**
	 * Receives code and returns enrollments instance if exists 
	 * @param code
	 * @return Optional<Course>
 	 */
	Optional<Enrollments> findByCode(UUID code);
	
	/**
	 * Return all enrollments instances
	 * @return List<Enrollments>
	 */
	List<Enrollments> findAll();

	/**
	 * Return all enrollments instances from course
	 * @param courseId
	 * @return List<Enrollments>
	 */
	List<Enrollments> findByCourse(Integer courseId);

	/**
	 * Receives a enrollmentDTO instance and save in database
	 * @param enrollment
	 */
	Enrollments save(EnrollmentsDTO enrollmentDTO);
	
	/**
	 * Receives course ID and return the number of enrollments
	 * @param courseId
	 * @return Long
	 */
	Long countActivesPerCourse(Integer courseId);
	
	/**
	 * Receives ID os enrollment and set active = true
	 * @param code
	 */
	void disableRegistration(UUID code);
	
	/**
	 * Receives ID and return enrollment instance if exists, else throw EntityNotFoundException
	 * @param enrollmentId
	 * @return Enrollment
	 */
	Enrollments findAndValidateEnrollment(UUID code);
	
}
