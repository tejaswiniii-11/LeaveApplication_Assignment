package com.aurionpro.controller;

import com.aurionpro.model.EmployeeLeave;
import com.aurionpro.service.LeaveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/viewLeaves")
public class ViewLeavesServlet extends HttpServlet {
    private LeaveService leaveService;

    @Override
    public void init() {
    	try {
            Connection connection = com.aurionpro.database.DBConnection.getConnection();
            leaveService = new LeaveService(connection);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize LeaveService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int userId = (int) req.getSession().getAttribute("userId");
        List<EmployeeLeave> leaves = leaveService.getLeavesByEmployeeId(userId);

        req.setAttribute("leaves", leaves);
        req.getRequestDispatcher("view-leaves.jsp").forward(req, resp);
    }
}
