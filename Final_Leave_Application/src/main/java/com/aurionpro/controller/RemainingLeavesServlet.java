package com.aurionpro.controller;

import com.aurionpro.service.EmployeeLeaveService;
import com.aurionpro.service.LeaveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

@WebServlet("/remainingLeaves")
public class RemainingLeavesServlet extends HttpServlet {
	private EmployeeLeaveService leaveService;

	@Override
	public void init() {
	    try {
	        Connection connection = com.aurionpro.database.DBConnection.getConnection();
	        leaveService = new EmployeeLeaveService(connection);
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to initialize EmployeeLeaveService", e);
	    }
	}


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int userId = (int) req.getSession().getAttribute("userId");

        // Assuming LeaveService returns a Map now
        Map<String, Integer> remainingLeaves = leaveService.getRemainingLeaves(userId);

        req.setAttribute("remainingLeaves", remainingLeaves);
        req.getRequestDispatcher("remaining-leaves.jsp").forward(req, resp);
    }

}
