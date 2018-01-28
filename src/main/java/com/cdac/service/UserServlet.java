package com.cdac.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import com.cdac.dao.AttendanceDao;
import com.cdac.dao.CourseDao;
import com.cdac.dao.SubjectDao;
import com.cdac.dao.UserDao;
import com.cdac.model.Attendance;
import com.cdac.model.Course;
import com.cdac.model.Subject;
import com.cdac.model.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Servlet implementation class Ser
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UserServlet() {
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		String userid = (String) session.getAttribute("sesuid");
		String courseId = (String) session.getAttribute("courseid");
		UserDao userDao = new UserDao();
		List<User> userList = userDao.getStudentByCourseId(courseId);
		List<User> outList = userList;
		JsonObject jsonResponse = new JsonObject();
		JsonArray data = new JsonArray();

		int displayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		int displayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		// Searching
		String searchParam = request.getParameter("sSearch");
		if (searchParam != null && searchParam != "") {
			outList = new ArrayList<User>();
			searchParam = searchParam.toLowerCase();
			for (User user : userList) {
				if (user.getUserName().toLowerCase().contains(searchParam)
						|| user.getUserEmail().toLowerCase().contains(searchParam)
						|| user.getUserPhone().toLowerCase().contains(searchParam)
						|| user.getUserId().toLowerCase().contains(searchParam)) {
					outList.add(user);
				}
			}
		}
		// Sorting
		if (request.getParameter("iSortCol_0") != null && request.getParameter("sSortDir_0") != null
				&& outList != null) {
			final int sortColumnIndex = Integer.valueOf(request.getParameter("iSortCol_0"));
			final int sortDirection = request.getParameter("sSortDir_0").equals("asc") ? -1 : 1;

			Collections.sort(outList, new Comparator<User>() {
				public int compare(User u1, User u2) {
					switch (sortColumnIndex) {
					case 0:
						return u1.getUserId().compareTo(u1.getUserId());

					case 1:
						return u1.getUserName().compareTo(u2.getUserName());

					case 2:
						return u1.getUserEmail().compareTo(u2.getUserEmail());

					case 3:
						return u1.getUserPhone().compareTo(u2.getUserPhone());
					}
					return 0;
				}
			});
		}
		// Paginating
		if (outList != null) {
			int startParam = 0;
			int endParam = 0;
			if (outList.size() < displayStart + displayLength) {
				startParam = displayStart;
				endParam = outList.size();
			} else {
				startParam = displayStart;
				endParam = displayStart + displayLength;
			}

			for (int i = startParam; i < endParam; i++) {
				JsonArray row = new JsonArray();
				row.add(new JsonPrimitive(outList.get(i).getUserId()));
				row.add(new JsonPrimitive(outList.get(i).getUserName()));
				row.add(new JsonPrimitive(outList.get(i).getUserEmail()));
				row.add(new JsonPrimitive(outList.get(i).getUserPhone()));
				data.add(row);
			}
		}
		jsonResponse.add("aaData", data);
		jsonResponse.addProperty("iTotalRecords", outList != null ? outList.size() : 0);
		jsonResponse.addProperty("iTotalDisplayRecords", outList != null ? outList.size() : 0);
		response.getWriter().write(jsonResponse.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		User user = null;
		String userid = request.getParameter("loginid");
		String upasswd = request.getParameter("upass");
		String course = request.getParameter("courseId");
		UserDao userdao = new UserDao();
		CourseDao courseDao = new CourseDao();
		SubjectDao subjectDao = new SubjectDao();
		user = userdao.authenticate(userid, upasswd);
		HttpSession session = request.getSession();
		session.setAttribute("sesuid", userid);
		session.setAttribute("courseid", course);
		if (user != null && user.getUserRole() == 1) {

			RequestDispatcher rd = request.getRequestDispatcher("/pages/Studentportal.jsp");
			rd.forward(request, response);
			System.out.println("authentic");
		} else if (user != null && user.getUserRole() == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Coordinatorportal.jsp");
			rd.forward(request, response);
		} else {

			response.sendRedirect("/pages/error.jsp");
		}

	}

}
