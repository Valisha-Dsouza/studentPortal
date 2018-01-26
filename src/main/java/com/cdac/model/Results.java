package com.cdac.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "resultsproject")
public class Results {
	@Column(name = "userid")
	String userId;
	@Column(name = "subjectid")
	String subjectId;
	@Column(name = "labmarks")
	int labMarks;
	@Column(name = "theorymarks")
	int theoryMarks;

	@AttributeOverrides({ @AttributeOverride(name = "userid", column = @Column(name = "userid")),
			@AttributeOverride(name = "subjectid", column = @Column(name = "subjectid")) })
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public int getLabMarks() {
		return labMarks;
	}

	public void setLabMarks(int labMarks) {
		this.labMarks = labMarks;
	}

	public int getTheoryMarks() {
		return theoryMarks;
	}

	public void setTheoryMarks(int theoryMarks) {
		this.theoryMarks = theoryMarks;
	}

}
