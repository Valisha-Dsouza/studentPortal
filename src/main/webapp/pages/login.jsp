<%@page import="com.cdac.dao.CourseDao"%>
<%@page import="com.cdac.model.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Management System</title>
<link rel="stylesheet" href="login.css">
</head>
<%
CourseDao courseDao = new CourseDao();
List<Course> courseList = courseDao.listCourse();
out.print(courseList.size());
%>
<body>

<form action="./UserServlet" onsubmit="return requiredfields(this)" method="post">
<table class="log">

<tr>
<td>LoginId</td><td><input type="text" name="loginid" value=""/> <span class="required">*</span></td>
</tr>

<tr>
<td>Password</td>
<td><input type="password" name="upass" value=""/> <span class="required">*</span></td>
</tr>
<tr>
<td>Course</td>
<td>
<select> 
<%for(Course course:courseList){%>
        <option value=<%=course.getCourseId()%>><%=course.getCourseName()%></option>
<%} %>
 
</td>
</tr>

</select>
</td>
</tr>
<tr>
<td rowspan="2">Role</td>
<td rowspan="2"><input type="radio" name="role" value="1"/>Course-Coordinator <br> 
<input type="radio" name="role" value="0"/>Student</td>
</tr>
<tr><td></td></tr>
<tr >
<td></td>
<td><input type="submit" value="Login"/></td>
</tr>
</table>

</form>
</body>
</html>