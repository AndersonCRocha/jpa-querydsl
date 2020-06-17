package com.studies.jpaquerydsl.daos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studies.jpaquerydsl.models.Enrollments;
import com.studies.jpaquerydsl.models.QEnrollments;

@Repository
public class EnrollmentsDAO {

	private static final QEnrollments ENROLLMENTS = QEnrollments.enrollments;
	
	@PersistenceContext
	private EntityManager manager;
	
	public Optional<Enrollments> findByCode(UUID code) {
		
		return Optional.ofNullable(new JPAQuery<Enrollments>(manager)
				.from(ENROLLMENTS)
				.where(ENROLLMENTS.code.eq(code))
				.fetchOne());
		
	}
	
	public List<Enrollments> findAll(){
		
		return new JPAQuery<Enrollments>(manager)
				.from(ENROLLMENTS)
				.orderBy(ENROLLMENTS.createdAt.asc())
				.fetch();
		
	}
	
	public List<Enrollments> findByCourse(Integer courseId){
	
		return new JPAQuery<Enrollments>(manager)
				.from(ENROLLMENTS)
				.where(ENROLLMENTS.course.id.eq(courseId))
				.fetch();
	
	}

	@Transactional
	public Enrollments save(Enrollments enrollment) {
		
		manager.persist(enrollment);
		return enrollment;
				
	}
	
	public Long countActivesPerCourse(Integer courseId) {
		
		return new JPAQuery<Enrollments>(manager)
				.from(ENROLLMENTS)
				.where((ENROLLMENTS.active.eq(true))
					.and(ENROLLMENTS.course.id.eq(courseId)))
				.fetchCount();
		
	}

	@Transactional
	public void disableRegistration(UUID code) {

		new JPAQueryFactory(manager)
			.update(ENROLLMENTS)
			.set(ENROLLMENTS.active, Boolean.FALSE)
			.where(ENROLLMENTS.code.eq(code))
			.execute();
	}
	
}
