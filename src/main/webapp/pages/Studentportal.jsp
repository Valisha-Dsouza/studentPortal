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

<meta http-equiv="Content-Type" content="text/html ; charset=ISO-8859-1"
	name="viewport" content="width=device-width;intial-scale=1">
<title>Student Management System</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="http://cdn.datatables.net/1.10.3/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/dataTables.jqueryui.min.js"></script>
<script>
	function openPortal(evt, portalName) {
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].className = tablinks[i].className.replace("active", "");
		}
		document.getElementById(portalName).style.display = "block";
		evt.currentTarget.className += "active";
	}
	////refer for subject id and user id
	$(document).ready(function() {

		$("#edit").click(function() {
			$("#editdtls").show();
			$("#edit").hide();
			$("#showdetls").hide();
		});

	});
</script>
<script>
	$(document).ready(function() {
		$('#tb1').DataTable({
			"sPaginationType" : "simple",
			"bJQueryUI" : true,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "StudentResultService",
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
		$('#attendance_tb1').DataTable({
			"sPaginationType" : "simple",
			"bJQueryUI" : true,
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : "StudentAttendanceService",
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
			} ]
		});
	});
</script>
<link rel="stylesheet" href="pages/tabstyle.css">
</head>
<body>
	<h3 id="heading">STUDENT PORTAL</h3>
	<%
		String user1 = (String) session.getAttribute("sesuid");
		PrintWriter pw = response.getWriter();
		UserDao ud = new UserDao();
		User usr = ud.getUserById(user1);
		String username = usr.getUserName();
		String userphone = usr.getUserPhone();
		String useremail = usr.getUserEmail();
	%>

	<div class="tab">
		<button class="tablinks" onclick="openPortal(event,'Student')">Student
			details</button>
		<button class="tablinks" onclick="openPortal(event,'Results')">Results
		</button>
		<button class="tablinks" onclick="openPortal(event,'Attendance')">Attendance
		</button>
		<button class="tablinks" onclick="openPortal(event,'Notification')">Notifications
		</button>
		<button class="tablinks">
			<a href="logout.jsp" style="text-decoration: none; color: inherit">Logout</a>
			</buttton>
	</div>
	<div id="Student" class="tabcontent">
		<h3>
			<%
				out.print("Welcome  " + username);
			%>
		</h3>
		<br />
		<div id="showdetls">
			<table>
				<tr>
					<td>Name</td>
					<td>
						<%
							out.print(username);
						%>
					</td>
				</tr>
				<tr>
					<td>Phone</td>
					<td>
						<%
							out.print(useremail);
						%>
					</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>
						<%
							out.print(userphone);
						%>
					</td>
				</tr>
			</table>
		</div>
		<pre>
		 
		<button id="edit">Edit Details</button>
		 </pre>
		<div id="editdtls" style="display: none">
			<form action="./UpdateServlet">
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
	</div>
	<div id="Results" class="tabcontent container">
		<table name="tb" id="tb1" class="table table-striped table-bordered"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>Subject</th>
					<th>Lab</th>
					<th>Theory</th>
					<th>Pass/Fail</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			<tfoot></tfoot>
		</table>
	</div>

	<div id="Attendance" class="tabcontent container">
		<table name="tb" id="attendance_tb1" class="table table-striped table-bordered"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>Subject</th>
					<th>Total Class</th>
					<th>Class Attended</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			<tfoot></tfoot>
		</table>
	</div>

	<div id="Notification" class="tabcontent">
		<h3>Notifications</h3>
	</div>
</body>
</html>


<!-- ---------function fetchResultByid(){  
	 $(document).ready(function(){
	$.ajax({
		type : "POST",
		url : "../ResultsPrintByUser",
		dataType : 'json',
		success : function(data) { 
			for(var i=0;i<data.length;i++)
			{
			var t1="<tr><td>"+data[i].userid+"</td><td>"+data[i].subjectid+"</td><td>"+data[i].labmarks+"</td><td>"+data[i].theorymarks+"</td></tr>";
			$("#resultTable").append(t1);
			}
		}
	});
	});
	}
function fetchAttendanceByid(){  
	 $(document).ready(function(){
	$.ajax({
		type : "POST",
		url : "./AttendancePrintByUser",
		dataType : 'json',
		success : function(data) { 
			for(var i=0;i<data.length;i++)
			{
			var t1="<tr><td>"+data[i].userid+"</td><td>"+data[i].subjectid+"</td><td>"+data[i].totalClass+"</td><td>"+data[i].classAttended+"</td></tr>";
			$("#attendTable").append(t1);
			}
		}
	});
	});
	} <div id="Results" class="tabcontent">
	<input type="button" value="Show Result" id="showTable" onclick="fetchResultByid()"/>
	<div id="tablediv">
	<table cellspacing="0" id="resultTable" border="1">
	<tr>
	<th scope="col">User id</th>
	<th scope="col">Subject id</th>
	<th scope="col">Labmarks</th>
	<th scope="col">Theory marks</th>
	</tr>
	</table>
	</div>
	</div>
	<div id="Attendance" class="tabcontent">
		<input type="button" value="Show Attendance" id="showAttend" onclick="fetchAttendanceByid()"/>
	<div id="tablediv1">
	<table cellspacing="0" id="attendTable" border="1">
	<tr>
	<th scope="col">User id</th>
	<th scope="col">Subject id</th>
	<th scope="col">Totalclass</th>
	<th scope="col">ClassAttended</th>
	<th scope="col">Percent</th>
	</tr>
	</table>-->