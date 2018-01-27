package com.cdac.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cdac.dao.AttendanceDao;
import com.cdac.model.Attendance;
import com.google.gson.Gson;

/**
 * Servlet implementation class AttendancePrintByUser
 */
public class StudentAttendanceService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentAttendanceService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		String userid=(String)session.getAttribute("sesuid");
		AttendanceDao attndao=new AttendanceDao();
		 List<Attendance> users = attndao.getAttendanceById(userid);
		 Gson gson = new Gson();
		 response.getWriter().write(gson.toJson(users));
	}

}
