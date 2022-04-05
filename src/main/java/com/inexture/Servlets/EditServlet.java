package com.inexture.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.inexture.Beans.UserBean;
import com.inexture.Services.UserService;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(EditServlet.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.debug("Inside Edit Servlet.");
		
		HttpSession session = request.getSession(false);  
		
		if(session != null) {
			
			log.debug("Session not null.");
			
			String email = request.getParameter("email");
			
			log.debug("Get email.");
			
			UserBean u = new UserBean(email);
			
			UserService es = new UserService();
			es.EditProfile(u);
			
			log.debug("Setting user bean to request attribute.");
			
	        request.setAttribute("user", u);
	        
	        log.debug("Redirecting to edit jsp page.");
			
	        request.getRequestDispatcher("register.jsp").forward(request, response);
	        
		}else {
			log.debug("Session is null, redirecting to login page.");
			
			response.sendRedirect("index.html");
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
