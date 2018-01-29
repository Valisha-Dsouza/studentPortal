package com.cdac.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.cdac.dao.SubjectDao;
import com.cdac.model.ResultComposite;
import com.cdac.model.Results;
import com.cdac.model.Subject;
import com.cdac.utils.ExcelReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
		HttpSession httpSession = request.getSession();
		String courseId = (String) httpSession.getAttribute("courseid");
		ResultsDao resultsDao = new ResultsDao();
		// get only results of this course
		SubjectDao subjectDao = new SubjectDao();
		List<Subject> subjects = subjectDao.findByCourse(courseId);
		List<String> subjectCodes = new ArrayList<String>();
		List<Results> resultsList = new ArrayList<Results>();
		for (Subject subject : subjects) {
			subjectCodes.add(subject.getSubjectId());
		}
		for (Results results : resultsDao.listResults()) {
			if (subjectCodes.contains(results.getResultId().getSubjectid())) {
				resultsList.add(results);
			}
		}
		
		JsonObject jsonResponse = new JsonObject();
		JsonArray data = new JsonArray();
		List<Results> outList = resultsList;
		int displayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		int displayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		// Searching
		String searchParam = request.getParameter("sSearch");
		if (searchParam != null && searchParam != "") {
			outList = new ArrayList<Results>();
			searchParam = searchParam.toLowerCase();
			for (Results result : resultsList) {
				if (result.getResultId().getUserId().toLowerCase().contains(searchParam)
						|| result.getResultId().getSubjectid().toLowerCase().contains(searchParam)
						|| String.valueOf(result.getLabMarks()).toLowerCase().contains(searchParam)
						|| String.valueOf(result.getTheoryMarks()).toLowerCase().contains(searchParam)
						|| result.getStatus().toLowerCase().contains(searchParam)) {
					outList.add(result);
				}
			}
		}
		// Sorting
		if (request.getParameter("iSortCol_0") != null && request.getParameter("sSortDir_0") != null
				&& outList != null) {
			final int sortColumnIndex = Integer.valueOf(request.getParameter("iSortCol_0"));
			final int sortDirection = request.getParameter("sSortDir_0").equals("asc") ? -1 : 1;

			Collections.sort(outList, new Comparator<Results>() {
				public int compare(Results r1, Results r2) {
					switch (sortColumnIndex) {
					case 0:return r1.getResultId().getUserId().compareTo(r2.getResultId().getUserId())
							* sortDirection;
					case 1:
						int sortOrder = -1;
						if (r1.getLabMarks() > r2.getLabMarks())
							sortOrder = +1;
						return sortOrder * sortDirection;
					case 2:
						sortOrder = -1;
						if (r1.getTheoryMarks() > r2.getTheoryMarks())
							sortOrder = +1;
						return sortOrder * sortDirection;
					case 3:
						return r1.getResultId().getSubjectid().compareTo(r2.getResultId().getSubjectid())
								* sortDirection;
					case 4:
						return r1.getStatus().compareTo(r2.getStatus()) * sortDirection;
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
				row.add(new JsonPrimitive(outList.get(i).getResultId().getUserId()));
				row.add(new JsonPrimitive(outList.get(i).getResultId().getSubjectid()));
				row.add(new JsonPrimitive(outList.get(i).getLabMarks()));
				row.add(new JsonPrimitive(outList.get(i).getTheoryMarks()));
				row.add(new JsonPrimitive(outList.get(i).getStatus()));
				data.add(row);
			}
		}
		jsonResponse.add("aaData", data);
		jsonResponse.addProperty("iTotalRecords", outList != null ? outList.size() : 0);
		jsonResponse.addProperty("iTotalDisplayRecords", outList != null ? outList.size() : 0);
		response.getWriter().write(jsonResponse.toString());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 PrintWriter pw=response.getWriter();
		Part filePart = request.getPart("resultfile");
		String sub=(String) request.getAttribute("subid");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE
		List<Results> results = new ArrayList<Results>(); // fix.
		InputStream fileContent = filePart.getInputStream();
		List<Row> rows = ExcelReader.read(fileContent);
		for (Row row : rows) {
			Results result = new Results();
			result.setResultId(new ResultComposite(row.getCell(0).getStringCellValue(), sub));
			result.setLabMarks((int)row.getCell(1).getNumericCellValue());
			result.setTheoryMarks((int)row.getCell(2).getNumericCellValue());
			result.setStatus(row.getCell(3).getStringCellValue());
			results.add(result);
		}
		ResultsDao dao = new ResultsDao();
		dao.insert(results);
		
		if(filePart!=null)
		{
			pw.println("upload successful");
		}
			
	}
}
