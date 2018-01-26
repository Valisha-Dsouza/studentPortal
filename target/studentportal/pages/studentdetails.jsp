<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.entity.User" %>
    <%@ page import="java.io.PrintWriter" %>
    <%@ page import="com.dao.UserDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Management System</title>
</head>
<body>

 <%
  
 String user1=(String)session.getAttribute("sesuid");
 PrintWriter pw=response.getWriter();
 UserDao ud=new UserDao();
 User usr=ud.getUserById(user1);
 pw.print("<h5>Student Details</h5>");
 pw.print(usr.getUserName());
 pw.print(usr.getUseremail());
 pw.print(usr.getUserphone());
	 
	
 %>
</body>
</html>