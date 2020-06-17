package com.studies.jpaquerydsl.models;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Entity
@SequenceGenerator(name = "sq_students", sequenceName = "sq_students", allocationSize = 1)
@JsonIgnoreProperties({"enrollments"})
public class Students {

	private Integer id;
	private String name;
	private String email;
	private Timestamp birth;

	private Set<Enrollments> enrollments;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_students")
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@ApiModelProperty(dataType = "timestamp", example = "2002-05-11 22:00:21")
	public Timestamp getBirth() {
		return birth;
	}

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
	public Set<Enrollments> getEnrollments() {
		return enrollments;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}

	public void setEnrollments(Set<Enrollments> enrollments) {
		this.enrollments = enrollments;
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
		if (!(obj instanceof Students)) {
			return false;
		}
		Students other = (Students) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + name + ", email=" + email + ", birth=" + birth + "]";
	}

}
