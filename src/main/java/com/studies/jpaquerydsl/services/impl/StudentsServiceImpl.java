package com.studies.jpaquerydsl.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studies.jpaquerydsl.daos.StudentsDAO;
import com.studies.jpaquerydsl.exceptions.EntityNotFoundException;
import com.studies.jpaquerydsl.models.Students;
import com.studies.jpaquerydsl.services.StudentsService;

@Service
public class StudentsServiceImpl implements StudentsService {

	private final static Logger LOG = LoggerFactory.getLogger(StudentsServiceImpl.class);
	
	@Autowired
	private StudentsDAO studentsDAO;
	
	@Override
	public Optional<Students> findById(Integer id) {
		
		LOG.info("Searching student by ID : {}", id);
		return studentsDAO.findById(id);
	}

	@Override
	public List<Students> findAll() {
		
		LOG.info("Searching all students");
		return studentsDAO.findAll();
	}
	
	@Override
	public Students save(Students student) {
		
		LOG.info("Saving student : {}", student);
		return studentsDAO.save(student);
	}

	@Override
	public Students update(Integer id, Students student) throws EntityNotFoundException {
		
		LOG.info("Updating student with ID: {}, {}", id, student);
		findAndValidateStudent(id);
		student.setId(id);
		
		return studentsDAO.update(student);
	}

	@Override
	public void delete(Integer id) {

		LOG.info("Deleting student with ID: {}", id);
		findAndValidateStudent(id);
		
		studentsDAO.delete(id);
	}
	
	public Students findAndValidateStudent(Integer studentId){
		Optional<Students> studentOpt = studentsDAO.findById(studentId);
		
		if(!studentOpt.isPresent()) {
			LOG.error("Student not found for ID : {}", studentId);
			throw new EntityNotFoundException("Student not found for ID : "+studentId);
		}
		return studentOpt.get();
	}
	
}
