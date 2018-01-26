package com.cdac.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.dao.ResultsDao;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
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
		ResultsDao resultdao=new ResultsDao();
		String userid1=(String)session.getAttribute("sesuid");
		String studentId=request.getParameter("stuid");
		String subjectId=request.getParameter("subid");
		int labmarks=Integer.parseInt(request.getParameter("lab_marks"));
		int theorymarks=Integer.parseInt(request.getParameter("theory_mark"));
		 
		int updtresult=resultdao.updateResult(studentId,subjectId,labmarks,theorymarks);
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
