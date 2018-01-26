package com.cdac.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.dao.ResultsDao;
import com.cdac.model.ResultComposite;
import com.cdac.model.Results;
import com.cdac.utils.ExcelReader;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
@MultipartConfig
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter pw = response.getWriter();
		ResultsDao resultdao = new ResultsDao();
		String userid1 = (String) session.getAttribute("sesuid");
		String studentId = request.getParameter("stuid");
		String subjectId = request.getParameter("subid");
		int labmarks = Integer.parseInt(request.getParameter("lab_marks"));
		int theorymarks = Integer.parseInt(request.getParameter("theory_mark"));

		int updtresult = resultdao.updateResult(studentId, subjectId, labmarks, theorymarks);
		if (updtresult == 1) {
			pw.print("<h4>update sucessfull</h4>");
			pw.print("<p>Please login by clicking on the link</p>");
			pw.print("<a href='login.jsp'>login</a>");
			System.out.println("update sucessfull");

		} else {
			pw.print("<h4>update unsucessfull</h4>");
			pw.print("<p>Please login by clicking on the link</p>");
			pw.print("<a href='login.jsp'>login</a>");
			System.out.println("could not update");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("id"));
		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE
		List<Results> results = new ArrayList<Results>();													// fix.
		InputStream fileContent = filePart.getInputStream();
		List<Row> rows = ExcelReader.read(fileContent);
		for (Row row : rows) {
			Results result = new Results();
			result.setResultId(new ResultComposite(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue()));
			result.setLabMarks((int)row.getCell(2).getNumericCellValue());
			result.setTheoryMarks((int)row.getCell(3).getNumericCellValue());
			results.add(result);
		}
		ResultsDao dao = new ResultsDao();
		dao.insert(results);
		
	}
}
