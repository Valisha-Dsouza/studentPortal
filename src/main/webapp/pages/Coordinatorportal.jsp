<%@page import="com.cdac.dao.CourseDao"%>
<%@page import="com.cdac.model.Course"%>
<%@page import="com.cdac.model.Subject" %>
<%@page import="com.cdac.dao.SubjectDao" %>
<%@page import="com.cdac.dao.AttendanceDao" %>
<%@page import="com.cdac.dao.ResultsDao" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.cdac.model.User"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cdac.dao.UserDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/dataTables.jqueryui.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"
	name="viewport" content="width=device-width,intial-scale=1">
<title>Student Management System</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="http://cdn.datatables.net/1.10.3/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/dataTables.jqueryui.min.js"></script>
 <link rel="stylesheet" href="pages/tabstyle.css">
<script  >
$(document).ready(function () {
    $('.nav ul li:first').addClass('active');
    $('.tabcontent:not(:first)').hide();
    $('.nav ul li a').click(function (event) {
        event.preventDefault();
        var content = $(this).attr('href');
        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass('active');
        $(content).show();
        $(content).siblings('.tabcontent').hide();
        $('#tb1').DataTable({
    		"sPaginationType" : "simple",
    		"bJQueryUI" : true,
    		"bProcessing" : true,
    		"bServerSide" : true,
    		"sAjaxSource" : "UserServlet",
    		"iDisplayLength" : 10,
    		"iDisplayStart" : 0,
    		"columnDefs" : [ {
    			"width" : "20%",
    			"targets" : 0
    		}, {
    			"width" : "20%",
    			"targets" : 0
    		}, {
    			"width" : "20%",
    			"targets" : 0
    		}, {
    			"width" : "20%",
    			"targets" : 0
    		} ]
    	});
		$('#result_tb').DataTable({
			"sPaginationType" : "simple",
			"bJQueryUI" : true,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "ResultServlet",
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
			"columnDefs" : [ {
				"width" : "20%",
				"targets" : 0
			}, {
				"width" : "20%",
				"targets" : 0
			}, {
				"width" : "20%",
				"targets" : 0
			}, {
				"width" : "20%",
				"targets" : 0
			}, {
				"width" : "20%",
				"targets" : 0
			} ]
		});
		$('#attendance_tb').DataTable({
			"sPaginationType" : "simple",
			"bJQueryUI" : true,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "CoordinatorAttendanceService",
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
		});

    });
});
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
function openResPortal(evt,portalName)
{
	var i,tabcontent,tablinks;
	tabcontent=document.getElementsByClassName("result");
	for(i=0;i<tabcontent.length;i++)
	{
		tabcontent[i].style.display="none";
	}
	tablinks=document.getElementsByClassName("resultlinks");
	for(i=0;i<tablinks.length;i++)
	{
	tablinks[i].className=tablinks[i].className.replace("active","");
	}
	document.getElementById(portalName).style.display="block";
	evt.currentTarget.className+="active";
}

function openAttenPortal(evt,portalName)
{
	var i,tabcontent,tablinks;
	tabcontent=document.getElementsByClassName("attendance");
	for(i=0;i<tabcontent.length;i++)
	{
		tabcontent[i].style.display="none";
	}
	tablinks=document.getElementsByClassName("attendlinks");
	for(i=0;i<tablinks.length;i++)
	{
	tablinks[i].className=tablinks[i].className.replace("active","");
	}
	document.getElementById(portalName).style.display="block";
	evt.currentTarget.className+="active";
}

</script>
</head>
 
<%
 
String courseid=(String)session.getAttribute("courseid");
SubjectDao subjectDao = new SubjectDao();
List<Subject> subList = subjectDao.findByCourse(courseid);
 
%>
<body>
<header >COURSE COORDINATOR PORTAL</header>
<%
String user1=(String)session.getAttribute("sesuid");	
PrintWriter pw=response.getWriter();
UserDao ud=new UserDao();
User usr=ud.findById(user1,User.class); 
String username=usr.getUserName();
%> 
<nav class="tab">
<ul>
<li><a class="tablinks" href="#Student" onclick="openPortal(event,'Student')">Student details</a>
</li>
<li><a class="tablinks" href="#Results" onclick="openPortal(event,'Results')">Results</a>



</li>
<li>
<a class="tablinks" href="#Attendance" onclick="openPortal(event,'Attendance')">Attendance
</a>
</li>
<li>
<a class="tablinks" href="#Notification" onclick="openPortal(event,'Notification')">Notifications
</a></li>
<li>
<a  class="tablinks" href='pages/logout.jsp';">Logout</a></li>
</ul>
</nav>
<div>
<!-- ------STUDENT LIST IN COORDINATOR PORTAL -->
<div id="Student" class="tabcontent container" style="display:none;">
 <table name="tb" id="tb1" class="table table-striped table-bordered"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>UserId</th>
					<th>UserName</th>
					<th>Email</th>
					<th>Phone No</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			<tfoot></tfoot>
		</table>
	</div>
</div>

<!-- ------RESULTS IN COORDINATOR PORTAL -->
<div id="Results" class="tabcontent"  style="display:none;">
<table ><tr ><td> <a class="resultlinks" href="#fileUpload" onclick="openResPortal(event,'fileUpload')"><button class="cp">Upload </button></a></td>
<td ><a class="resultlinks" href="#viewResult" onclick="openResPortal(event,'viewResult')"><button class="cp">View </button></a></tr>
</table>
 
 <!-- for results upload  and view -->
 <div id="fileUpload"   class="result" style="display:none;">
<form  method="post" action="./ResultServlet" enctype='multipart/form-data' ">
<table>
<tr>
<td>Select the subject</td>
<td>
<select name="subid">
<%for(Subject sub:subList){%>
        <option value=<%=sub.getSubjectId()%>><%=sub.getSubjectName()%></option>
<%} %>
 </select>
 </td>
 </tr>
 <tr>
 <td>
<input type="file" name="resultfile" accept=".xlsx,.xls"/>
</td>
<td>
<input type="submit" value="Upload">
</td>
</tr>
</table>
</form>
</div>
<!-- -----------------for view -->
<div id="viewResult" style="display:none;" class="result"> 
<p> view result</p>
<form method="post" action=" pages/sucess.jsp   ">
<input type="submit" value="View Result">
</form>
</div>
		<table name="tb" id="result_tb"
			class="table table-striped table-bordered" cellspacing="0"
			width="100%">
			<thead>
				<tr>
					<th>UserId</th>
					<th>UserName</th>
					<th>Email</th>
					<th>Phone No</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			<tfoot></tfoot>
		</table>
	</div>

</div>

<!-- ATTENDANCE IN COORDINATOR PORTAL -->
<div id="Attendance" class="tabcontent" style="display:none;">
 <table>
  
<tr> <td><a class="attendlinks" href="#attendUpload" onclick="openAttenPortal(event,'attendUpload')">
<button class="cp"  >Upload</button>
</a></td>
 
<td ><a class="attendlinks" href="#viewAttend" onclick="openAttenPortal(event,'viewAttend')">
<button class="cp" >View </button>
</a></td>
</tr>
</table>

<!-- for results upload  and view -->
<div id="attendUpload" class="attendance" style="display:none;">
<form id="fileUpload" method="post" action="./CoordinatorAttendanceService" enctype='multipart/form-data'>
<table>
<tr>
<td>
<p>Select subject name</p></td>
<td>
<select name="subid"> 
<%for(Subject sub:subList){%>
        <option value=<%=sub.getSubjectId()%>><%=sub.getSubjectName()%></option>
<%} %>
</select>
</td>
<tr>
<td>
<input type="file" name="attendancefile" accept=".xlsx,.xls"/></td>
<td>
<input type="submit" value="Upload"></td>
</tr>
</table>
</form>
 </div>
 
 <!-- -----------------for view -->
 <div id="viewAttend" class="attendance" style="display:none;">
 
 <form method="post" action=" pages/sucess.jsp   ">
<input type="submit" value="View Attendance">
</form>
	<table name="tb" id="attendance_tb"
			class="table table-striped table-bordered" cellspacing="0"
			width="100%">
			<thead>
				<tr>
					<th>StudentId</th>
					<th>SubjectId</th>
					<th>Total Class</th>
					<th>Class Attended</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			<tfoot></tfoot>
		</table>
	</div>
	
 </div>
 </div>
<div id="Notification" class="tabcontent" style="display:none;">
<h3>Notifications</h3>
<p>New notifications</p>
</div>

</div>
</body>
</html>