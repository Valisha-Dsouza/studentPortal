package com.cdac.dao;

import java.util.List;

import com.cdac.model.Subject;;

public class SubjectDao extends BaseHibernateDao{
	public void insert(Subject subject){
		insert(subject);
	}
	
	public List<Subject> listCourse(){
		return getList(Subject.class);
	}
	
	public void delete(Subject subject){
		delete(subject);
	}
}
