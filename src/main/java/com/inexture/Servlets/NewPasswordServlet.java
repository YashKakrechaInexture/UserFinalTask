package com.inexture.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.inexture.Services.ResetPasswordService;

/**
 * Servlet implementation class NewPasswordServlet
 */
@WebServlet("/NewPasswordServlet")
public class NewPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(NewPasswordServlet.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.debug("Inside New Password Servlet.");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		if(password1.equals(password2)) {
			
			log.debug("Password is same, reseting password.");
			
			ResetPasswordService rps = new ResetPasswordService();
			rps.ResetPass(email, password1);
			
			out.print("<p>Password changed.</p>");
			
			log.debug("Redirecting to login page.");
			request.getRequestDispatcher("index.jsp").include(request, response);
		}else {
			log.debug("Password not matched, redirecting to new password page.");
			out.print("<p>Password not matched.</p>");
			request.getRequestDispatcher("newPassword.jsp").include(request, response);
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
