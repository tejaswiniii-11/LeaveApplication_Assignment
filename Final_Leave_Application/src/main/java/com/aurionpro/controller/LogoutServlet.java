package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Get the current session
        HttpSession session = request.getSession(false); // false => don't create if not exists

        if (session != null) {
            session.invalidate(); // Invalidate the session
        }

        // Redirect to login page
        response.sendRedirect("login.jsp"); // replace login.jsp with your login page URL
    }
}
