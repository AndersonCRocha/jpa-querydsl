package com.studies.jpaquerydsl.models;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

@Entity
public class Enrollments {

	private UUID code;
	private Students student;
	private Courses course;
	private Boolean active = Boolean.TRUE;
	private Timestamp createdAt;

	public Enrollments() {
	}

	public Enrollments(Courses course, Students student) {
		this.course = course;
		this.student = student;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type="org.hibernate.type.UUIDCharType")
	public UUID getCode() {
		return code;
	}

	@ManyToOne
	public Students getStudent() {
		return student;
	}

	@ManyToOne
	public Courses getCourse() {
		return course;
	}

	public Boolean getActive() {
		return active;
	}

	@Column(name = "created_at")
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCode(UUID code) {
		this.code = code;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
	public void prePersist() {
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Enrollments)) {
			return false;
		}
		Enrollments other = (Enrollments) obj;
		return Objects.equals(code, other.code);
	}

	@Override
	public String toString() {
		return "StudentsCourses [code=" + code + ", student=" + student + ", course=" + course + "]";
	}

}
