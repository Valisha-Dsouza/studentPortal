<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 <%@ page import="com.entity.User" %>
    <%@ page import="java.io.PrintWriter" %>
    <%@ page import="java.util.*" %>
    <%@ page import="com.dao.UserDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html ; charset=ISO-8859-1"
	name="viewport" content="width=device-width;intial-scale=1">
<title>Student Management System</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
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
function fetch(){  
	 $(document).ready(function(){
	$.ajax({
		type : "POST",
		url : "./UserServlet",
		dataType : 'json',
		success : function(data) {
		
			document.write("<table border='1' >");
			document.write("<th>User name</th>");
			 document.write("<tbody id='content'></tbody>");
		for(var i=0;i<data.length;i++)
			{
			var tab ="<tr><td>"+data[i].username+"</td></tr>";
			document.getElementById("content").innerHTML+=tab;
			}
		
		}
	});
	});
	} 
$(document).ready(function(){
	$("#show").click(function(){
		$("#showdetls").show();
		$("#editdtls").hide();
	});
	$("#edit").click(function(){
		$("#editdtls").show();
		$("#showdetls").hide();
	});
});
</script>
<link rel="stylesheet" href="tabstyle.css">
</head>
<body>
<%
String user1=(String)session.getAttribute("sesuid");
PrintWriter pw=response.getWriter();
UserDao ud=new UserDao();
User usr=ud.getUserById(user1); 
String username=usr.getUserName();
String userphone=usr.getUserphone();
String useremail=usr.getUseremail();
%>
<p style="color:blue"><% out.print("Welcome  "+ username ); %></p>
<a href="logout.jsp" style="float: right;">Logout</a>
<div class="tab">
		<button class="tablinks" onclick="openPortal(event,'Student')">Student details
		</button>
		<button class="tablinks" onclick="openPortal(event,'Results')">Results
		</button>
		<button class="tablinks" onclick="openPortal(event,'Attendance')">Attendance
		</button>
		<button class="tablinks" onclick="openPortal(event,'Notification')">Notifications
		</button>
</div>
<div id="Student" class="tabcontent">
		<h3>Student Details</h3>
		<button id="edit">Edit Details</button>
		<button id="show">Show Details</button>
		<div id="editdtls" style="display:none">
		<form  action="./UpdateServlet" >
			<table>
				<tr>
					<td>Name</td>
					<td><input type="text" name="uname" /></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input type="number" name="uphone" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="email" name="uemail"
						placeholder="someone@example.com" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"></td>
					<td><input type="reset" name="reset" /></td>
				</tr>
			</table>
		</form>
		</div>
		 <div id="showdetls" style="display:none">
		 <p><span><h7>Name:</h7></span><% out.print(username); %></p>
		 <p><span><h7>Email:</h7></span><% out.print(useremail); %></p>
		 <p><span><h7>Phone:</h7></span><% out.print(userphone); %></p>
		 </div>
	</div>
	<div id="Results" class="tabcontent">
	<form action="./ResultServlet">
		<h3>Results</h3>
		<input type="submit" value="GetResult"/>
		</form>
	</div>
	<div id="Attendance" class="tabcontent">
		<h3>Attendance</h3>
	</div>
	<div id="Notification" class="tabcontent">
		<h3>Notifications</h3>
	</div>
</body>
</html>