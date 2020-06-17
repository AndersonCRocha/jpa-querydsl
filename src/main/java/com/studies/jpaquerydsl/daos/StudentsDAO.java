package com.studies.jpaquerydsl.daos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studies.jpaquerydsl.models.QStudents;
import com.studies.jpaquerydsl.models.Students;

@Repository
public class StudentsDAO {
	
	private static final QStudents STUDENTS = QStudents.students;
	
	@PersistenceContext
	private EntityManager manager;
	
	public Optional<Students> findById(Integer id) {

		return Optional.ofNullable(new JPAQuery<Students>(manager)
				.from(STUDENTS)
				.where(STUDENTS.id.eq(id))
				.fetchOne());
	}
	
	public List<Students> findAll(){
		
		return new JPAQuery<Students>(manager)
				.from(STUDENTS)
				.orderBy(STUDENTS.id.asc())
				.fetch();
	}
	
	@Transactional
	public Students save(Students student) {
		
		manager.persist(student);
		return student;
		
	}

	@Transactional
	public Students update(Students student) {
		
		manager.merge(student);
		return student;
		
	}

	@Transactional
	public void delete(Integer id) {
		new JPAQueryFactory(manager)
				.delete(STUDENTS)
				.where(STUDENTS.id.eq(id))
				.execute();
	}
	
}
