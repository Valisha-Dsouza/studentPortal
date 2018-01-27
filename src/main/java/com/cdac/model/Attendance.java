package com.cdac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * create table db_student_attendance(student_id varchar(20),total_class int(3),
 class_attended int(3),subject_id varchar(20),percent float(3) ,primary key(student_id,subject_id));
 * */
@Entity
@Table(name = "db_student_attendance")
public class Attendance {
	@Id
	@Column(name = "student_id")
	String studentId;
	@Column(name = "total_class")
	int totalClass;
	@Column(name = "class_attended")
	int classAttended;
	@Column(name = "subject_id")
	String subjectId;
	@Column(name = "percent")
	float percent;

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getTotalClass() {
		return totalClass;
	}

	public void setTotalClass(int totalClass) {
		this.totalClass = totalClass;
	}

	public int getClassAttended() {
		return classAttended;
	}

	public void setClassAttended(int classAttended) {
		this.classAttended = classAttended;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

}
