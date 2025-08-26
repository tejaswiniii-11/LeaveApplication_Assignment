package com.aurionpro.controller;

import com.aurionpro.service.AdminLeaveService;
import com.aurionpro.service.LeaveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

@WebServlet("/applyLeave")
public class ApplyLeaveServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LeaveService leaveService;

    @Override
    public void init() {
    	 try {
  	        Connection connection = com.aurionpro.database.DBConnection.getConnection();
  	        leaveService = new LeaveService(connection);
  	    } catch (Exception e) {
  	        throw new RuntimeException("Failed to initialize EmployeeLeaveService", e);
  	    }

       
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");

        try {
            int leaveTypeId = Integer.parseInt(req.getParameter("leaveTypeId"));
            Date startDate = Date.valueOf(req.getParameter("startDate")); // yyyy-MM-dd
            Date endDate = Date.valueOf(req.getParameter("endDate"));

            boolean success = leaveService.applyLeave(userId, leaveTypeId, startDate, endDate);

            if (success) {
                req.setAttribute("message", "Leave applied successfully!");
            } else {
                req.setAttribute("errorMessage", "Leave application failed. Please try again.");
            }
        } catch (IllegalArgumentException e) {
            req.setAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }

        req.getRequestDispatcher("apply-leave.jsp").forward(req, resp);
    }
}
