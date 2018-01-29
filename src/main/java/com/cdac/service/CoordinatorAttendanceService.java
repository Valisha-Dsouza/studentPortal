package com.cdac.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.Row;

import com.cdac.dao.AttendanceDao;
import com.cdac.dao.SubjectDao;
import com.cdac.model.Attendance;
import com.cdac.model.AttendanceComposite;
import com.cdac.model.Results;
import com.cdac.model.Subject;
import com.cdac.utils.ExcelReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Servlet implementation class CoordinatorAttendanceService
 */
public class CoordinatorAttendanceService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoordinatorAttendanceService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		String userid = (String) session.getAttribute("sesuid");
		String courseId = (String) session.getAttribute("courseid");
		AttendanceDao attndao = new AttendanceDao();
		SubjectDao subjectDao = new SubjectDao();
		List<Subject> subjects = subjectDao.findByCourse(courseId);
		List<Attendance> attendanceList = new ArrayList<Attendance>();
		List<String> subjectCodes = new ArrayList<String>();
		for (Subject subject : subjects) {
			subjectCodes.add(subject.getSubjectId());
		}
		for (Attendance attendance : attndao.listAttendance()) {
			if (subjectCodes.contains(attendance.getAttendanceId().getSubjectid())) {
				attendanceList.add(attendance);
			}
		}
		List<Attendance> outList = attendanceList;
		JsonObject jsonResponse = new JsonObject();
		JsonArray data = new JsonArray();

		int displayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		int displayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		// Searching
		String searchParam = request.getParameter("sSearch");
		if (searchParam != null && searchParam != "") {
			outList = new ArrayList<Attendance>();
			searchParam = searchParam.toLowerCase();
			for (Attendance attendance : attendanceList) {
				if (attendance.getAttendanceId().getUserId().toLowerCase().contains(searchParam) ||
						attendance.getAttendanceId().getSubjectid().toLowerCase().contains(searchParam)
						|| String.valueOf(attendance.getClassAttended()).toLowerCase().contains(searchParam)
						|| String.valueOf(attendance.getTotalClass()).toLowerCase().contains(searchParam)) {
					outList.add(attendance);
				}
			}
		}
		// Sorting
		if (request.getParameter("iSortCol_0") != null && request.getParameter("sSortDir_0") != null
				&& outList != null) {
			final int sortColumnIndex = Integer.valueOf(request.getParameter("iSortCol_0"));
			final int sortDirection = request.getParameter("sSortDir_0").equals("asc") ? -1 : 1;

			Collections.sort(outList, new Comparator<Attendance>() {
				public int compare(Attendance a1, Attendance a2) {
					switch (sortColumnIndex) {
					case 0:
						return a1.getAttendanceId().getUserId().compareTo(a2.getAttendanceId().getUserId());
					case 1:
						return a1.getAttendanceId().getSubjectid().compareTo(a2.getAttendanceId().getSubjectid());
					case 2:
						int sortOrder = -1;
						if (a1.getClassAttended() > a2.getClassAttended())
							sortOrder = +1;
						return sortOrder * sortDirection;
					case 3:
						sortOrder = -1;
						if (a1.getTotalClass() > a2.getTotalClass())
							sortOrder = +1;
						return sortOrder * sortDirection;

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
				row.add(new JsonPrimitive(outList.get(i).getAttendanceId().getUserId()));
				row.add(new JsonPrimitive(outList.get(i).getAttendanceId().getSubjectid()));
				row.add(new JsonPrimitive(outList.get(i).getTotalClass()));
				row.add(new JsonPrimitive(outList.get(i).getClassAttended()));
				data.add(row);
			}
				}
		jsonResponse.add("aaData", data);
		jsonResponse.addProperty("iTotalRecords", outList!=null?outList.size():0);
		jsonResponse.addProperty("iTotalDisplayRecords", outList!=null?outList.size():0);
		response.getWriter().write(jsonResponse.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sub=(String) request.getAttribute("subid");
		PrintWriter pw=response.getWriter();
		Part filePart = request.getPart("attendancefile");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE
		List<Attendance> attendance = new ArrayList<Attendance>();													// fix.
		InputStream fileContent = filePart.getInputStream();
		List<Row> rows = ExcelReader.read(fileContent);
		for (Row row : rows) {
			Attendance result = new Attendance();
			result.setAttendanceId(new AttendanceComposite(row.getCell(0).getStringCellValue(),sub));
			result.setTotalClass((int)row.getCell(1).getNumericCellValue());
			result.setClassAttended((int)row.getCell(2).getNumericCellValue());
			attendance.add(result);
		}
		AttendanceDao dao = new AttendanceDao();
		dao.insert(attendance);
		
		if(filePart!=null)
		{
			pw.println("upload successful");
		}
	}

}
