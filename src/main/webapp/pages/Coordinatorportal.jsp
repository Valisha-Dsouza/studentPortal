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
	//chANGES
	$(document)
			.ready(
					function() {
						var options = {
							beforeSend : function() {
								$("#progressbox").show();
								// clear everything
								$("#progressbar").width('0%');
								$("#message").empty();
								$("#percent").html("0%");
							},
							uploadProgress : function(event, position, total,
									percentComplete) {
								$("#progressbar").width(percentComplete + '%');
								$("#percent").html(percentComplete + '%');

								// change message text to red after 50%
								if (percentComplete > 50) {
									$("#message")
											.html(
													"<font color='red'>File Upload is in progress</font>");
								}
							},
							success : function() {
								$("#progressbar").width('100%');
								$("#percent").html('100%');
							},
							complete : function(response) {
								$("#message")
										.html(
												"<font color='blue'>Your file has been uploaded!</font>");
							},
							error : function() {
								$("#message")
										.html(
												"<font color='red'> ERROR: unable to upload files</font>");
							}
						};
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
						$("#UploadForm").ajaxForm(options);
					});
</script>
<link rel="stylesheet" href="pages/tabstyle.css">
</head>
<body>
	<h3 id="heading">COURSE COORDINATOR PORTAL</h3>
	<%
		String user1 = (String) session.getAttribute("sesuid");
		PrintWriter pw = response.getWriter();
		UserDao ud = new UserDao();
		User usr = ud.findById(user1, User.class);
		String username = usr.getUserName();
	%>

	<p style="color: blue">
		<%
			out.print("Welcome  " + username);
		%>
	</p>
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
	<div id="Student" class="tabcontent container">
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
	<div id="Results" class="tabcontent container">
		<h3>Results</h3>
		<form id="UploadForm" action="./ResultServlet" method="post"
			enctype="multipart/form-data">
			<input type="file" id="myfile" name="myfile" accept=".xlsx,.xls">
			<input type="submit" value="Upload">
			<div id="progressbox">
				<div id="progressbar"></div>
				<div id="percent">0%</div>
			</div>
			<br />
			<div id="message"></div>
		</form>
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



	<div id="Attendance" class="tabcontent">
		<h3>Attendance</h3>
		<form action="Attendance" method="post">
			<table>
				<tr>
					<td>Subject Id</td>
					<td><input type="text" name="subid" /></td>
				</tr>
				<tr>
					<td>Student Id</td>
					<td><input type="text" name="stuid" /></td>
				</tr>
				<tr>
					<td>Total Classes</td>
					<td><input type="number" name="total" />
				</tr>
				<tr>
					<td>Classes Attended</td>
					<td><input type="number" name="class_att" /></td>
				</tr>
				<tr>
					<td>Percent</td>
					<td><input type="number" name="attend_pct" /></td>
				</tr>
				<tr>
					<td><input type="submit" name="submit" /></td>
					<td><input type="reset" name="reset" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="Notification" class="tabcontent">
		<h3>Notifications</h3>
		<p>New notifications</p>
	</div>
</body>
</html>