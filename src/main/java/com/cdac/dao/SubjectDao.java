package com.cdac.dao;

import java.util.List;

import com.cdac.model.Subject;;

public class SubjectDao extends BaseHibernateDao{
	
	public List<Subject> listCourse(){
		return getList(Subject.class);
	}
	
	public void delete(Subject subject){
		delete(subject);
	}
	
	public List<Subject> findByCourse(String courseId){
		return executeQuery("from Subject where courseId ='"+courseId+"'");

	}
	
	
	public static void main(String[] args)
	{
		SubjectDao sd=new SubjectDao();
		List<Subject> list=sd.findByCourse("DAC");
		System.out.println(list.size());

	}
}
