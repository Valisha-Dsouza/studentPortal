package com.cdac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * create table db_subject_info (subject_id varchar(10),subject_name varchar(20)
course_id varchar(20),primary key(subject_id));
 * 
 * 
 * */
@Entity
@Table(name = "db_subject_info")
public class Subject {
	@Id
	@Column(name = "subject_id")
	String subjectId;
	@Column(name = "subject_name")
	String subjectName;
	@Column(name = "course_id")
	String courseId;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

}
