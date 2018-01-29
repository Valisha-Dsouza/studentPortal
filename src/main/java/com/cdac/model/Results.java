package com.cdac.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
/*
 * create table resultsproject(userid varchar(50),subjectid varchar(50),labmarks int(3),theorymarks int(3),
 * res varchar(5),primary key(userid,subjectid));
 * 
 * */
@Entity
@Table(name = "resultsproject")
public class Results {
	@EmbeddedId
	ResultComposite resultId;
	@Column(name = "labmarks")
	int labMarks;
	@Column(name = "theorymarks")
	int theoryMarks;
	
	@Column(name="res")
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ResultComposite getResultId() {
		return resultId;
	}

	public void setResultId(ResultComposite resultId) {
		this.resultId = resultId;
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
