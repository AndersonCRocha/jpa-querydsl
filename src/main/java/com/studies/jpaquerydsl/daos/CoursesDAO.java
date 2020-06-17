package com.studies.jpaquerydsl.daos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studies.jpaquerydsl.models.Courses;
import com.studies.jpaquerydsl.models.QCourses;

@Repository
public class CoursesDAO {
	
	private static final QCourses COURSES = QCourses.courses;
	
	@PersistenceContext
	private EntityManager manager;
	
	public Optional<Courses> findById(Integer id) {

		return Optional.ofNullable(new JPAQuery<Courses>(manager)
				.from(COURSES)
				.where(COURSES.id.eq(id))
				.fetchOne());
	}
	
	public List<Courses> findAll(){
		
		return new JPAQuery<Courses>(manager)
				.from(COURSES)
				.orderBy(COURSES.id.asc())
				.fetch();
	}
	
	@Transactional
	public Courses save(Courses course) {
		
		manager.persist(course);
		return course;
		
	}

	@Transactional
	public Courses update(Courses course) {
		
		manager.merge(course);
		return course;
		
	}

	@Transactional
	public void delete(Integer id) {
		
		new JPAQueryFactory(manager)
				.delete(COURSES)
				.where(COURSES.id.eq(id))
				.execute();
	}
	
}
