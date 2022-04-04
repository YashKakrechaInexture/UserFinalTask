package com.inexture.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inexture.Services.AuthEmailService;

/**
 * Servlet implementation class AuthEmailServlet
 */
@WebServlet("/AuthEmailServlet")
public class AuthEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String email = request.getParameter("email");
		
		PrintWriter out = response.getWriter();
		
		AuthEmailService aes = new AuthEmailService();
		if(!aes.CheckEmail(email)) {
			out.print("<span style=\"color:red;\">Email Already Taken.</span>");
		}else {
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
