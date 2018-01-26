package com.cdac.dao;

import java.util.List;


import com.cdac.model.Course;

public class CourseDao extends BaseHibernateDao {
	
	public List<Course> listCourse(){
		return getList(Course.class);
	}
	
	public void delete(Course course){
		delete(course);
	}
	
	public List<Course> findyId(String id){
		return executeQuery("select from Course where courseId = "+id);
	}
	
}
