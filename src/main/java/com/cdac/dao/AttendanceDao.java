package com.cdac.dao;

import com.cdac.model.Attendance;
import java.util.List;

public class AttendanceDao extends BaseHibernateDao {
	
	public void insert(Attendance attendance){
		insert(attendance);
	}
	
	public List<Attendance> listAttendance(){
		return getList(Attendance.class);
	}
	
	public void delete(Attendance attendance){
		delete(attendance);
	}
	
	
}
