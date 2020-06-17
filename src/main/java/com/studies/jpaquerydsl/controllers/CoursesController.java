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

import com.studies.jpaquerydsl.models.Courses;
import com.studies.jpaquerydsl.services.CoursesService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {

	@Autowired
	private CoursesService coursesService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Courses> show(@PathVariable(name = "id") Integer id){

		Courses course = coursesService.findAndValidateCourse(id);
		return ResponseEntity.ok(course);

	}
	
	@GetMapping
	public ResponseEntity<List<Courses>> list(){
		
		List<Courses> courses = coursesService.findAll();
		return ResponseEntity.ok(courses);
	
	}
	
	@PostMapping
	public ResponseEntity<Courses> create(@RequestBody Courses course){
		
		coursesService.save(course);
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentContextPath()
		        .path("/api/courses/{id}")
		        .buildAndExpand(course.getId()).toUri())
				.build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Courses> update(@PathVariable(name = "id") Integer id, @RequestBody Courses course) 
			throws NotFoundException{
		
		course = coursesService.update(id, course);
		return ResponseEntity.ok(course);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		
		coursesService.delete(id);
		return ResponseEntity.noContent().build();		
		
	}
	
}
