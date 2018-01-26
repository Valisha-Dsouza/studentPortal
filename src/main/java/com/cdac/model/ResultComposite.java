package com.cdac.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ResultComposite implements Serializable {
	@Column(name = "userid")
	String userId;
	@Column(name = "subjectid")
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

	public ResultComposite() {
		super();
	}

	public ResultComposite(String userId, String subjectid) {
		super();
		this.userId = userId;
		this.subjectid = subjectid;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;
		ResultComposite obj2 = (ResultComposite) obj;
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
