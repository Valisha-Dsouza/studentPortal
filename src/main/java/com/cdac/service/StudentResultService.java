package com.cdac.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;

import com.cdac.dao.ResultsDao;
import com.cdac.model.Results;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Servlet implementation class StudentResultService
 */
public class StudentResultService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentResultService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		ResultsDao resultsDao = new ResultsDao();
		JsonObject jsonResponse = new JsonObject();
		JsonArray data = new JsonArray();
		List<Results> resultsList = resultsDao.getResultByUserId(httpSession.getAttribute("sesuid").toString());
		List<Results> outList = resultsList;
		int displayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		int displayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		// Searching
		String searchParam = request.getParameter("sSearch");
		if (searchParam != null && searchParam != "") {
			outList = new ArrayList<Results>();
			searchParam = searchParam.toLowerCase();
			for (Results result : resultsList) {
				if (result.getResultId().getSubjectid().toLowerCase().contains(searchParam)
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
					case 0:
						int sortOrder = -1;
						if (r1.getLabMarks() > r2.getLabMarks())
							sortOrder = +1;
						return sortOrder * sortDirection;
					case 1:
						sortOrder = -1;
						if (r1.getTheoryMarks() > r2.getTheoryMarks())
							sortOrder = +1;
						return sortOrder * sortDirection;
					case 2:
						return r1.getResultId().getSubjectid().compareTo(r2.getResultId().getSubjectid())
								* sortDirection;
					case 3:
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
				row.add(new JsonPrimitive(outList.get(i).getResultId().getSubjectid()));
				row.add(new JsonPrimitive(outList.get(i).getLabMarks()));
				row.add(new JsonPrimitive(outList.get(i).getTheoryMarks()));
				row.add(new JsonPrimitive(outList.get(i).getStatus()));
				data.add(row);
			}
		}
		jsonResponse.add("aaData", data);
		jsonResponse.addProperty("iTotalRecords", outList!=null?outList.size():0);
		jsonResponse.addProperty("iTotalDisplayRecords", outList!=null?outList.size():0);
		response.getWriter().write(jsonResponse.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
