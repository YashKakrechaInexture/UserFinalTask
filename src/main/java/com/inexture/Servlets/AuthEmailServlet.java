package com.inexture.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.inexture.Services.UserService;

/**
 * This is called by ajax in registration page to check if email is already taken or not.
 * @author Yash
 *
 */
@WebServlet("/AuthEmailServlet")
public class AuthEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(AuthEmailServlet.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.debug("Inside Auth email servlet.");
		
		String email = request.getParameter("email");
		PrintWriter out = response.getWriter();
		
		UserService aes = new UserService();
		if(!aes.CheckEmail(email)) {
			log.info("Email exist in table.");
			out.print("<span style=\"color:red;\">Email Already Taken.</span>");
		}else {
			log.info("Email does not exist in table.");
			out.print("<span style=\"color:green;\">Email Available.</span>");
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
