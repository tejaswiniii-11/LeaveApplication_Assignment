package com.aurionpro.controller;

import com.aurionpro.dao.AdminLeaveDao;
import com.aurionpro.model.AdminLeaveDTO;
import com.aurionpro.service.AdminLeaveService;
import com.aurionpro.service.EmployeeLeaveService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/adminLeave")
public class AdminLeaveServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminLeaveService leaveService;

    
    @Override
    public void init() {
    	 try {
 	        Connection connection = com.aurionpro.database.DBConnection.getConnection();
 	        leaveService = new AdminLeaveService(connection);
 	    } catch (Exception e) {
 	        throw new RuntimeException("Failed to initialize EmployeeLeaveService", e);
 	    }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      
        String action = request.getParameter("action");
        List<AdminLeaveDTO> leaves;

        try {
            if (action == null || action.equals("all")) {
                leaves = leaveService.viewAllLeaves();
            } else if (action.equalsIgnoreCase("pending") ||
                       action.equalsIgnoreCase("approved") ||
                       action.equalsIgnoreCase("rejected")) {
                leaves = leaveService.viewLeavesByStatus(action.toUpperCase());
            } else if (action.equalsIgnoreCase("approve") || action.equalsIgnoreCase("reject")) {
                // Get leaveId to approve/reject
                int leaveId = Integer.parseInt(request.getParameter("leaveId"));
                if (action.equalsIgnoreCase("approve")) {
                    leaveService.approveLeave(leaveId);
                } else {
                    leaveService.rejectLeave(leaveId);
                }
               
                leaves = leaveService.viewLeavesByStatus("PENDING");
            } else {
                
                leaves = leaveService.viewAllLeaves();
            }

         
            request.setAttribute("leaves", leaves);

         
            RequestDispatcher dispatcher = request.getRequestDispatcher("leaves-list.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        doGet(request, response);
    }
}

