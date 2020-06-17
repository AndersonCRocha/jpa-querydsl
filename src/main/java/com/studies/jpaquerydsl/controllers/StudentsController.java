package com.studies.jpaquerydsl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.studies.jpaquerydsl.models.Students;
import com.studies.jpaquerydsl.services.StudentsService;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

	@Autowired
	private StudentsService studentsService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Students> show(@PathVariable(name = "id") Integer id){

		Students student = studentsService.findAndValidateStudent(id);
		return ResponseEntity.ok(student);

	}
	
	@GetMapping
	public ResponseEntity<List<Students>> list(){
		
		List<Students> students = studentsService.findAll();
		return ResponseEntity.ok(students);
	
	}
	
	@PostMapping
	public ResponseEntity<Students> create(@RequestBody Students student){
		
		studentsService.save(student);
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentContextPath()
		        .path("/api/students/{id}")
		        .buildAndExpand(student.getId()).toUri())
				.build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Students> update(@PathVariable(name = "id") Integer id, @RequestBody Students student) {
		
		student = studentsService.update(id, student);
		return ResponseEntity.ok(student);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		
		studentsService.delete(id);
		return ResponseEntity.noContent().build();		
		
	}
	
}
