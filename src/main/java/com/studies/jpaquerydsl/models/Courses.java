package com.studies.jpaquerydsl.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "sq_courses", sequenceName = "sq_courses", allocationSize = 1)
public class Courses {

	private Integer id;
	private String title;
	private String description;
	private Integer studentLimit;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_courses")
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	@Column(name = "student_limit")
	public Integer getStudentLimit() {
		return studentLimit;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStudentLimit(Integer studentLimit) {
		this.studentLimit = studentLimit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Courses)) {
			return false;
		}
		Courses other = (Courses) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Courses [id=" + id + ", title=" + title + ", description=" + description + ", studentLimit="
				+ studentLimit + "]";
	}

}
