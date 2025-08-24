package com.aurionpro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.*;

import com.aurionpro.model.EmployeeLeave;

@WebServlet("/EmployeeLeaveServlet")
public class EmployeeLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final EmployeeLeaveService leaveService = new EmployeeLeaveService();

	public EmployeeLeaveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String action = request.getParameter("action");
	    if ("viewSummary".equals(action)) {
	        String empId = request.getParameter("filterEmpId"); // This will match your form field
	        List<EmployeeLeave> leaveList = null;
	        if (empId != null && !empId.isEmpty()) {
	            leaveList = leaveService.getLeaveSummary(empId);
	        }
	        request.setAttribute("leaveList", leaveList);
	        request.setAttribute("showSummary", Boolean.TRUE);
	    } else {
	        request.setAttribute("showSummary", false);
	    }
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Employee.jsp");
	    dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

//		EmployeeLeave leave = new EmployeeLeave(empId, empName, totalLeaves, leaveFrom, tillLeave, reason, status);

//		boolean isSuccess = leaveService.applyLeave(leave);

		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String leaveFrom = request.getParameter("leaveFrom");
		String tillLeave = request.getParameter("tillLeave");
		String totalLeavesStr = request.getParameter("totalLeaves");
		int totalLeaves = 0; // parsing it since all the input from form http takes as in string format
		try {
			totalLeaves = Integer.parseInt(totalLeavesStr);
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Invalid total leaves number.");
		}

		String reason = request.getParameter("reason");
		String status = "Pending";

//		System.out.println("EmpId: " + request.getParameter("empId"));
//		System.out.println("TotalLeaves: " + request.getParameter("totalLeaves"));

		// validations
		if (empId != null && empName != null && leaveFrom != null && tillLeave != null && totalLeaves > 0
				&& reason != null) {
			EmployeeLeave leave = new EmployeeLeave(empId, empName, totalLeaves, leaveFrom, tillLeave, reason, status);
			boolean isSuccess = leaveService.applyLeave(leave);

			if (isSuccess) {
				request.setAttribute("message", "Leave applied successfully.");
			} else {
				request.setAttribute("error", "Failed to apply leave. Please try again.");
			}
		} else {
			request.setAttribute("error", "Some input fields are missing or invalid.");
		}

		

		RequestDispatcher dispatcher = request.getRequestDispatcher("Employee.jsp");
		dispatcher.forward(request, response);
		
//		List<EmployeeLeave> leaveList = leaveService.getLeaveSummary();
//		request.setAttribute("leaveList", leaveList);

	}

}
