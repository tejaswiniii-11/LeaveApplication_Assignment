package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.aurionpro.model.UserLogin;
import com.aurionpro.service.UserLoginService;



@WebServlet("/LoginUser")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final UserLoginService userService = new UserLoginService();
       
   
    public UserLoginController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");

		

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        UserLogin user = new UserLogin(username, password, role);
        boolean loginTheUser = userService.loginUser(user);

        if (loginTheUser) {
            
           
            RequestDispatcher dispatcher;
            if ("employee".equalsIgnoreCase(role)) {
                dispatcher = request.getRequestDispatcher("Employee.jsp");
            } else if ("admin".equalsIgnoreCase(role)) {
                dispatcher = request.getRequestDispatcher("Admin.jsp");
            } else {
               
                request.setAttribute("error", "Invalid role");
                dispatcher = request.getRequestDispatcher("UserLogin.jsp");
            }
            dispatcher.forward(request, response);
        }	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
