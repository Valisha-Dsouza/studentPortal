<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.cdac.model.User" %>
    <%@ page import="java.io.PrintWriter" %>
    <%@ page import="java.util.*" %>
    <%@ page import="com.cdac.dao.UserDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" name="viewport" content="width=device-width,intial-scale=1">
<title>Student Management System</title>
<script  >
function openPortal(evt,portalName)
{
	var i,tabcontent,tablinks;
	tabcontent=document.getElementsByClassName("tabcontent");
	for(i=0;i<tabcontent.length;i++)
	{
		tabcontent[i].style.display="none";
	}
	tablinks=document.getElementsByClassName("tablinks");
	for(i=0;i<tablinks.length;i++)
	{
	tablinks[i].className=tablinks[i].className.replace("active","");
	}
	document.getElementById(portalName).style.display="block";
	evt.currentTarget.className+="active";
}
</script>
<link rel="stylesheet" href="pages/tabstyle.css" >
</head>
<body>
<h3 id="heading">COURSE COORDINATOR PORTAL</h3>
<%
String user1=(String)session.getAttribute("sesuid");	
PrintWriter pw=response.getWriter();
UserDao ud=new UserDao();
User usr=ud.findById(user1,User.class); 
String username=usr.getUserName();
%>
 
 <p style="color:blue"><% out.print("Welcome  "+ username ); %></p>
<div class="tab">
<button class="tablinks" onclick="openPortal(event,'Student')">Student details
</button>
<button class="tablinks" onclick="openPortal(event,'Results')">Results
</button>
<button class="tablinks" onclick="openPortal(event,'Attendance')">Attendance
</button>
<button class="tablinks" onclick="openPortal(event,'Notification')">Notifications
</button>
<button class="tablinks"><a href="logout.jsp" style="text-decoration:none;color:inherit">Logout</a>
		</buttton>
</div>
<div id="Student" class="tabcontent">
<h3>Student details</h3>
<p>student details</p>
</div>
<div id="Results" class="tabcontent">
<h3>Results</h3>
<form action="./ResultServlet" method="post">
<table>
<tr>
<td>Subject Id</td>
<td><input type="text" name="subid"/>
</td>
</tr>
<tr>
<td>Student Id</td>
<td><input type="text" name="stuid"/>
</td>
</tr>
<tr>
<td>Theory Marks</td>
<td><input type="number" name="theory_mark"/>
</tr>
<tr>
<td>Lab Marks</td>
<td><input type="number" name="lab_marks"/>
</td>
</tr>
<tr>
<td>Percent</td>
<td><input type="number" name="result_pct"/>
</td>
</tr>
<tr>
<td>Result</td>
<td><input type="text" name="result"/>
</td>
</tr>
<tr><td><input type="submit" name="submit"/></td>
<td><input type="reset" name="reset"/></td></tr>
</table>
</form>
</div>
<div id="Attendance" class="tabcontent">
<h3>Attendance</h3>
<form action="Attendance" method="post">
<table>
<tr>
<td>Subject Id</td>
<td><input type="text" name="subid"/>
</td>
</tr>
<tr>
<td>Student Id</td>
<td><input type="text" name="stuid"/>
</td>
</tr>
<tr>
<td>Total Classes</td>
<td><input type="number" name="total"/>
</tr>
<tr>
<td>Classes Attended</td>
<td><input type="number" name="class_att"/>
</td>
</tr>
<tr>
<td>Percent</td>
<td><input type="number" name="attend_pct"/>
</td>
</tr>
<tr><td><input type="submit" name="submit"/></td>
<td><input type="reset" name="reset"/></td></tr>
</table></form>
</div>
<div id="Notification" class="tabcontent">
<h3>Notifications</h3>
<p>New notifications</p>
</div>
</body>
</html>