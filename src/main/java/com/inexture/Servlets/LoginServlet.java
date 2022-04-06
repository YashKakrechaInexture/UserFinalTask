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

import org.apache.log4j.Logger;

import com.inexture.Beans.UserBean;
import com.inexture.Services.UserService;


/**
 * It fetches the email and password from the database table and checks if this is user or admin,
 * and redirects them to their respective page.
 * @author Yash
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(LoginServlet.class);
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.debug("Inside LoginServlet");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		log.info("Got email and password from login page");
		
		UserService ls = new UserService();
		UserBean u = ls.checkUser(email,password);
		
		log.debug("Inside LoginServlet : Email and password has been checked.");
		
		RequestDispatcher rd = null;
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		if(u != null) {
			
			HttpSession session=request.getSession();  
			session.setAttribute("email",u.getEmail());  
			session.setAttribute("user", u);
			
			log.debug("Session created and UserBean set to attribute.");
			
			if(u.getType().equals("admin")) {
				
				log.info("User is admin, redirecting to admin page.");
				session.setAttribute("admin","true");
				response.sendRedirect("AdminServlet");
				
			}else if (u.getType().equals("user")){
				
				log.info("User is normal user, redirecting to user home page.");
				response.sendRedirect("homepage.jsp");
				
			}else {
				
				log.error("User found but its not user or admin");
				response.sendRedirect("index.jsp");
				
			}
			
		}else{
			
			log.info("No user found with given email and password, redirecting to login page.");
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
