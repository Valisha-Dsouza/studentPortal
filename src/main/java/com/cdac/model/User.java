package com.cdac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 /*create table userproject(userid varchar(20),username varchar(30),useremail varchar(100),
userphone varchar(20),userpasswd varchar(30),primary key(userid));*/
@Entity
@Table(name="userproject")
public class User
{
	@Id
	@Column(name="userid")
	String userId;
	@Column(name="username")
	String userName;
	@Column(name="userpasswd")
	String userPass;
	@Column(name="role")
	int userRole;
	@Column(name="useremail")
	String userEmail;
	@Column(name="userphone")
	String userPhone;
	@Column(name="userCourse")
	String userCourse;

	
	public String getUserCourse() {
		return userCourse;
	}
	public void setUserCourse(String userCourse) {
		this.userCourse = userCourse;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	
	
}