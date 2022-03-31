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

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.Services.UpdateService;

/**
 * Servlet implementation class UpdateServlet
 */
@MultipartConfig
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
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
		
		if(fname=="" || lname=="" || phone==0 || gender=="" || birthdate=="" || hobby=="" || que1=="" || que2=="" || que3=="" || address.size()==0) {
			out.print("Input Field is empty");
			rd = request.getRequestDispatcher("register.jsp");
			rd.include(request, response);
		}else {
			
			HttpSession session=request.getSession(false);  
			
			UserBean u = new UserBean(fname,lname,email,phone,password,gender,birthdate,hobby,que1,que2,que3,address,inputStream);
			
			UpdateService us = new UpdateService();
			
			us.updateUser(u,fileName);
			
			out.print("Updated Successfully");
			
			UserBean user = (UserBean)session.getAttribute("user");
			
			if(user.getType().equals("user")) {
				response.sendRedirect("homepage.jsp");
	        }else if(user.getType().equals("admin")){
	        	response.sendRedirect("AdminServlet");
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
