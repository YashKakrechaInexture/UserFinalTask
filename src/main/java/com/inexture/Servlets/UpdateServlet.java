package com.inexture.Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.Services.UpdateService;
import com.inexture.Utilities.Validation;

/**
 * Servlet implementation class UpdateServlet
 */
@MultipartConfig
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(UpdateServlet.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.debug("Inside Update Servlet.");
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		String suid = request.getParameter("uid");
		int uid = Integer.parseInt(suid);
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String sphone = request.getParameter("phone");
		String email = request.getParameter("email");
		long phone = 0;
		try {
			phone = Integer.parseInt(sphone);
			
		}catch(Exception e) {
			out.print("phone : "+sphone);
			out.print("phone : "+phone);
			out.print("Exception : "+e);
			log.warn("Phone is not a number");
		}
		String password = "";
		String gender = request.getParameter("gender");
		String birthdate = request.getParameter("birthdate");
		String hobbyArray[] = request.getParameterValues("hobby");
		String hobby = String.join(",", hobbyArray);
		Part filePart = null;
		InputStream inputStream = null;
		String fileName = null;
		try {
			filePart = request.getPart("profilepic");
			fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
			if(!fileName.equals("")){
				inputStream = filePart.getInputStream();
			}
		}catch(Exception e) {
			log.error("Something went wrong! Exception : "+e);
		}
		
		String que1 = request.getParameter("que1");
		String que2 = request.getParameter("que2");
		String que3 = request.getParameter("que3");
		
		String home[] = request.getParameterValues("home");
		String city[] = request.getParameterValues("city");
		String state[] = request.getParameterValues("state");
		String country[] = request.getParameterValues("country");
		String pincode[] = request.getParameterValues("pincode");
		
		ArrayList<AddressBean> address = new ArrayList<AddressBean>();
		for(int i=0 ; i<home.length ; i++) {
			AddressBean a = new AddressBean(home[i],city[i],state[i],country[i],pincode[i]);
			address.add(a);
		}
		
		RequestDispatcher rd = null;
		
		UserBean u = new UserBean(fname,lname,email,phone,password,gender,birthdate,hobby,que1,que2,que3,address,inputStream);
		
		if(!Validation.validate(u)) {
			log.debug("Validation failed.");
			out.print("Input Field is empty");
			rd = request.getRequestDispatcher("register.jsp");
			rd.include(request, response);
		}else {
			
			log.debug("Validation passed, updating User data.");
			
			HttpSession session=request.getSession(false);  
			
			if(session!=null) {
				
				log.debug("Session is not null, updating user.");
				
				u.setUid(uid);
				UpdateService us = new UpdateService();
				
				us.updateUser(u,fileName);
				
				out.print("Updated Successfully");
				
				UserBean user = (UserBean)session.getAttribute("user");
				
				if(user.getType().equals("user")) {
					log.debug("Session is active and type is user. Redirecting to homepage.");
					response.sendRedirect("homepage.jsp");
		        }else if(user.getType().equals("admin")){
		        	log.debug("Session is active and type is admin. Redirecting to admin servlet.");
					response.sendRedirect("AdminServlet");
		        }else {
		        	log.error("Session is active but no user or admin found.");
		        }
			}else {
				log.warn("Session is null, redirecting to login page.");
				response.sendRedirect("index.jsp");
			}
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
