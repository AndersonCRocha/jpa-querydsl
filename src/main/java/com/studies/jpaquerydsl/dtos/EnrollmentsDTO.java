package com.studies.jpaquerydsl.dtos;

public class EnrollmentsDTO {

	private Integer studentId;
	private Integer courseId;

	public EnrollmentsDTO(){
	}
	
	public EnrollmentsDTO(Integer studentId, Integer courseId) {
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

}
