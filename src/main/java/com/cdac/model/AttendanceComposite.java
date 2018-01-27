package com.cdac.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AttendanceComposite implements Serializable {
	@Column(name = "student_id")
	String userId;
	@Column(name = "subject_id")
	String subjectid;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public AttendanceComposite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AttendanceComposite(String userId, String subjectid) {
		super();
		this.userId = userId;
		this.subjectid = subjectid;
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;
		AttendanceComposite obj2 = (AttendanceComposite) obj;
		if (this.userId.equals(obj2.getUserId()) && this.subjectid == obj2.getSubjectid()) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int tmp = 0;
		tmp = (userId + subjectid).hashCode();
		return tmp;
	}
	
}
