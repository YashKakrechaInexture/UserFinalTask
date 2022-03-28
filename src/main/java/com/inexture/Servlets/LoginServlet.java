package com.inexture.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inexture.Beans.UserBean;
import com.inexture.Services.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserBean u = new UserBean(email,password);
		
		LoginService ls = new LoginService();
		int type = ls.checkUser(u);
		
		RequestDispatcher rd = null;
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		if(type==0) {
			//admin
			HttpSession session=request.getSession();  
			session.setAttribute("email",email);  
			session.setAttribute("admin","true");
			
			/*
			 * rd = request.getRequestDispatcher("admin.jsp"); rd.include(request,
			 * response);
			 */
			
			response.sendRedirect("AdminServlet");
			
		}else if(type==1) {
			//user
			HttpSession session=request.getSession();  
			session.setAttribute("email",email);  
			
			/*
			 * rd = request.getRequestDispatcher("homepage.jsp"); rd.include(request,
			 * response);
			 */
			response.sendRedirect("homepage.jsp");
			
		}else {
			//index
			out.print("Enter Correct Details");
			rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
