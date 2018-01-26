package com.cdac.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BaseHibernateDao;
import com.dao.UserDao;
import com.entity.User;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		PrintWriter pw=response.getWriter();
		UserDao userdao1=new UserDao();
		String userid1=(String)session.getAttribute("sesuid");
		String uname=request.getParameter("uname");
		String uphone=request.getParameter("uphone");
		String uemail=request.getParameter("uemail");
		int updtresult=userdao1.updateUser(userid1,uname,uphone,uemail);
		  if(updtresult==1)
		  {
			 pw.print("<h4>update sucessfull</h4>");
			  pw.print("<p>Please login by clicking on the link</p>");
			  pw.print("<a href='login.jsp'>login</a>");
			  System.out.println("update sucessfull"); 
			  
		  }
		  else {
			  pw.print("<h4>update unsucessfull</h4>");
			  pw.print("<p>Please login by clicking on the link</p>");
			  pw.print("<a href='login.jsp'>login</a>");System.out.println("could not update");
		  }
		
	}

}
