package com.inexture.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.inexture.Beans.UserBean;
import com.inexture.Services.UserService;

/**
 * It finds the user based on given email, birthdate and security answers from reset password page.
 * If user found then redirects to new password input page. 
 * @author Yash
 *
 */
@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(ResetPasswordServlet.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.debug("Inside Reset Password Servlet.");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String birthdate = request.getParameter("birthdate");
		
		String que1 = request.getParameter("que1");
		String que2 = request.getParameter("que2");
		String que3 = request.getParameter("que3");
		
		UserBean u = new UserBean(email,birthdate,que1,que2,que3);
		
		log.debug("Got data and set in userbean.");
		
		UserService fu = new UserService();
		
		if(fu.FindUser(u)) {
			log.debug("User found, redirecting to new password page.");
			request.setAttribute("email", email);
			request.getRequestDispatcher("newPassword.jsp").forward(request, response);
		}else {
			log.debug("No user found, redirecting to reset password page.");
			out.print("No user found");
			request.getRequestDispatcher("resetPassword.jsp").include(request, response);
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
