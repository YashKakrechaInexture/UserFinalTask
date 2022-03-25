package com.inexture.Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.Services.RegisterService;

/**
 * Servlet implementation class RegisterServlet
 */
@MultipartConfig
public class RegisterServlet extends HttpServlet {
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
		String email = request.getParameter("email");
		String sphone = request.getParameter("phone");
		long phone = 0;
		try {
		phone = Integer.parseInt(sphone);
		}catch(Exception e) {
			out.print("phone : "+sphone);
			out.print("phone : "+phone);
			out.print("Exception : "+e);
		}
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String gender = request.getParameter("gender");
		String birthdate = request.getParameter("birthdate");
		String hobbyArray[] = request.getParameterValues("hobby");
		String hobby = String.join(",", hobbyArray);
		Part filePart = request.getPart("profilepic");
		InputStream inputStream = null;
		if(filePart != null) {
			inputStream = filePart.getInputStream();
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
		
		UserBean u = new UserBean(fname,lname,email,phone,password1,password2,gender,birthdate,hobby,que1,que2,que3,address,inputStream);
		
		HttpSession session=request.getSession(false);  
		
		if(session==null || session.getAttribute("email")==null) {
			RegisterService rs = new RegisterService();
			rs.RegisterUser(u);
			
			response.sendRedirect("index.jsp");
		}else {
			response.sendRedirect("register.jsp");
		}
		
		
//		u.setFname(fname);
//		u.setLname(lname);
//		u.setEmail(email);
//		u.setPhone(phone);
//		u.setPassword1(password1);
//		u.setPassword2(password2);
//		u.setGender(gender);
//		u.setBirthdate(birthdate);
//		u.setHobby(hobby);
//		u.setQue1(que1);
//		u.setQue2(que2);
//		u.setQue3(que3);
//		u.setAddress(address);
//		u.setInputStream(inputStream);
		
//		out.print("<br>Fname : "+fname);
//		out.print("<br>Lname : "+lname);
//		out.print("<br>email : "+email);
//		out.print("<br>sphone : "+sphone);
//		out.print("<br>phone : "+phone);
//		out.print("<br>password1 : "+password1);
//		out.print("<br>password2 : "+password2);
//		out.print("<br>gender : "+gender);
//		out.print("<br>birthdate : "+birthdate);
//		out.print("<br>hobby : "+hobby);
//		out.print("<br>que1 : "+que1);
//		out.print("<br>que2 : "+que2);
//		out.print("<br>que3 : "+que3);
//		out.print("<br>home : "+home);
//		out.print("<br>city : "+city);
//		out.print("<br>state : "+state);
//		out.print("<br>country : "+country);
//		out.print("<br>pincode : "+pincode);
//		out.print("<br>part : "+filePart);
//		out.print("<br>input stream : "+inputStream);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}