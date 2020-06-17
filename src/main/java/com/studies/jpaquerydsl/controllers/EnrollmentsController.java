package com.studies.jpaquerydsl.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.studies.jpaquerydsl.dtos.EnrollmentsDTO;
import com.studies.jpaquerydsl.models.Enrollments;
import com.studies.jpaquerydsl.services.EnrollmentsService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentsController {

	@Autowired
	private EnrollmentsService enrollmentsService;
	
	@GetMapping("/{code}")
	public ResponseEntity<Enrollments> show(@PathVariable(name = "code") String code){
		
		Enrollments enrollment = enrollmentsService.findAndValidateEnrollment(UUID.fromString(code));
		return ResponseEntity.ok(enrollment);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Enrollments>> list(){
		
		List<Enrollments> enrollments = enrollmentsService.findAll();
		return ResponseEntity.ok(enrollments);
		
	}
	
	@GetMapping("/course/{id}")
	public ResponseEntity<List<Enrollments>> listByCourse(@PathVariable(name = "id") Integer id){
		
		List<Enrollments> enrollments = enrollmentsService.findByCourse(id);
		return ResponseEntity.ok(enrollments);
		
	}
	
	@PostMapping
	public ResponseEntity<Enrollments> enroll(@RequestBody EnrollmentsDTO enrollmentsDTO){
		
		Enrollments enrollment = enrollmentsService.save(enrollmentsDTO);
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentContextPath()
		        .path("/api/enrollments/{id}")
		        .buildAndExpand(enrollment.getCode()).toUri())
				.build();
		
	}
	
	@PutMapping("/disable/{code}")
	public ResponseEntity<Void> disable(@PathVariable(name = "code") String code){
		
		enrollmentsService.disableRegistration(UUID.fromString(code));
		return ResponseEntity.noContent().build();
		
	}
	
}
