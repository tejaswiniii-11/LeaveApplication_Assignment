package com.aurionpro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.model.EmployeeLeave;
import com.aurionpro.service.EmployeeLeaveService;

@WebServlet("/AdminLeaveServlet")
public class AdminLeaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EmployeeLeaveService leaveService = new EmployeeLeaveService();

    public AdminLeaveServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Distinguish between showing all leaves and pending leaves by "action" parameter
        String action = request.getParameter("action");
        List<EmployeeLeave> leaveList;

        if ("pendingLeaves".equalsIgnoreCase(action)) {
            // Show only pending leaves for "Action For Leaves"
            leaveList = leaveService.getLeavesByStatus("Pending");
        } else {
            // Default: show all leaves (for "View Leaves")
            leaveList = leaveService.getAllLeaves();
        }

        request.setAttribute("leaveList", leaveList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // On POST, admin approves/rejects leave
        String empId = request.getParameter("empId");
        String action = request.getParameter("action"); // "approve" or "reject"

        String status = null;
        if ("approve".equals(action)) {
            status = "Approved";
        } else if ("reject".equals(action)) {
            status = "Rejected";
        }

        if (empId != null && status != null) {
            boolean success = leaveService.updateLeaveStatus(empId, status);
            if (success) {
                request.setAttribute("message", "Leave application status updated to " + status + ".");
            } else {
                request.setAttribute("error", "Failed to update leave status.");
            }
        } else {
            request.setAttribute("error", "Invalid request parameters.");
        }

        // refresh leave summary after update
        List<EmployeeLeave> leaveList = leaveService.getAllLeaves();
        request.setAttribute("leaveList", leaveList);

        System.out.println("Rows fetched for admin: " + leaveList.size());

        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
        dispatcher.forward(request, response);
    }

}
