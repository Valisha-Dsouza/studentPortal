package com.cdac.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.cdac.dao.CourseDao;
import com.cdac.dao.SubjectDao;
import com.cdac.dao.UserDao;
import com.cdac.model.Course;
import com.cdac.model.Subject;
import com.cdac.model.User;

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
		if(course!=null){
			List<Course> courses = courseDao.findyId(course);
			List<Subject> subjects = subjectDao.findByCourse(course);
			response.set
		}
		if (user != null && user.getUserRole() == 0) {

			RequestDispatcher rd = request.getRequestDispatcher("/Studentportal.jsp");
			rd.forward(request, response);
			System.out.println("authentic");
		} else if (user != null && user.getUserRole() == 1) {
			RequestDispatcher rd = request.getRequestDispatcher("/Coordinatorportal.jsp");
			rd.forward(request, response);
		} else {

			response.sendRedirect("error.jsp");
		}

		// JSON call valisha
		/*
		 * UserDao userdao=new UserDao(); List<User> users = userdao.listUser();
		 * System.out.println(users.size()); Gson gson = new Gson();
		 * response.getWriter().write(gson.toJson(users));
		 */
		/*
		 * UserDao userdao1=new UserDao(); String
		 * userid1=request.getParameter("userid"); String
		 * uname=request.getParameter("uname"); String
		 * uphone=request.getParameter("uphone"); String
		 * uemail=request.getParameter("uemail"); int
		 * updtresult=userdao1.updateUser(userid1,uname,uphone,uemail);
		 * if(updtresult==1) System.out.println("update sucessfull"); else
		 * System.out.println("could not update");
		 */

	}

}
