package com.cdac.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;

@Entity
@Table(name = "resultsproject")
public class Results {
	@EmbeddedId
	ResultComposite resultId;
	@Column(name = "labmarks")
	int labMarks;
	@Column(name = "theorymarks")
	int theoryMarks;
	
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
