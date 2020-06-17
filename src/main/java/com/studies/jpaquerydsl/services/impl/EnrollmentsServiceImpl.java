package com.studies.jpaquerydsl.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studies.jpaquerydsl.daos.EnrollmentsDAO;
import com.studies.jpaquerydsl.dtos.EnrollmentsDTO;
import com.studies.jpaquerydsl.exceptions.EntityNotFoundException;
import com.studies.jpaquerydsl.models.Courses;
import com.studies.jpaquerydsl.models.Enrollments;
import com.studies.jpaquerydsl.models.Students;
import com.studies.jpaquerydsl.services.CoursesService;
import com.studies.jpaquerydsl.services.EnrollmentsService;
import com.studies.jpaquerydsl.services.StudentsService;

@Service
public class EnrollmentsServiceImpl implements EnrollmentsService{

	private final static Logger LOG = LoggerFactory.getLogger(EnrollmentsServiceImpl.class);
	
	@Autowired
	private EnrollmentsDAO enrollmentsDAO;
	@Autowired
	private CoursesService coursesService;
	@Autowired
	private StudentsService studentsService;

	@Override
	public List<Enrollments> findAll() {
		
		LOG.info("Searching all enrollments");
		return enrollmentsDAO.findAll();
	}

	@Override
	public Optional<Enrollments> findByCode(UUID code) {
		
		LOG.info("Searching enrollment by code: {}" + code.toString());
		return enrollmentsDAO.findByCode(code);
	}
	
	@Override
	public List<Enrollments> findByCourse(Integer courseId) {
		
		LOG.info("Searching enrollments of course with ID: {}", courseId);
		coursesService.findAndValidateCourse(courseId);
		
		return enrollmentsDAO.findByCourse(courseId);
	}

	@Override
	public Long countActivesPerCourse(Integer courseId) {
		
		LOG.info("Counting enrollments from course ID: {}", courseId);
		return enrollmentsDAO.countActivesPerCourse(courseId);
	}
	
	@Override
	public Enrollments save(EnrollmentsDTO enrollmentDTO) {
		
		LOG.info("Saving enrollment: {}", enrollmentDTO);
		
		Courses course = coursesService.findAndValidateCourse(enrollmentDTO.getCourseId());
		Long enrollmentsByCourse = countActivesPerCourse(enrollmentDTO.getCourseId());

		if(enrollmentsByCourse.longValue() >= course.getStudentLimit()) {
			throw new IllegalArgumentException("Exhausted enrollments for this course");
		}
		
		Students student = studentsService.findAndValidateStudent(enrollmentDTO.getStudentId());

		Enrollments enrollment = new Enrollments(course, student);
		return enrollmentsDAO.save(enrollment);
	}
	
	@Override
	public void disableRegistration(UUID code) {
		
		findAndValidateEnrollment(code);
		enrollmentsDAO.disableRegistration(code);
	}

	@Override
	public Enrollments findAndValidateEnrollment(UUID code) {
		
		Optional<Enrollments> enrollmentOpt = enrollmentsDAO.findByCode(code);
		
		if(!enrollmentOpt.isPresent()) {
			LOG.error("Enrollment not found for code : {}", code);
			throw new EntityNotFoundException("Enrollment not found for code : "+code);
		}
		return enrollmentOpt.get();
	}

}
