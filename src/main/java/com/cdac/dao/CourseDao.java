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
		return executeQuery(" from Course where courseId = '"+id+"'");
	}
	
	public static void main(String[] args) {
		CourseDao courseDao = new CourseDao();
		System.out.println(courseDao.listCourse());
		System.out.println(courseDao.findById("aa", Course.class));
	}

}
