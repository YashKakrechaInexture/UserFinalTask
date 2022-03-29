package com.inexture.Servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;
import com.inexture.Services.EditService;

/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);  
		
		if(session != null) {
			String email = (String)session.getAttribute("email");
			
			UserBean u = new UserBean(email);
			
			DaoMethods dm = new DaoMethods();
			dm.GetUserInfo(u);
			
			InputStream inputStream = u.getInputStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try {
				
				byte[] buffer = new byte[4096];
		        int bytesRead = -1;
		         
		        while ((bytesRead = inputStream.read(buffer)) != -1) {
		            outputStream.write(buffer, 0, bytesRead);                  
		        }
		         
		        byte[] imageBytes = outputStream.toByteArray();
		        
		        outputStream.write(imageBytes);
		        
		        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		        
		        u.setBase64Image(base64Image);
		        
			}catch(Exception e) {
				System.out.print("Exception : "+e);
			}finally {
				
				try {
					
					if(inputStream != null) {
						inputStream.close();    
					}
					if(outputStream != null) {
						outputStream.close();    
					}

				}catch(Exception ex) {
					System.out.print("Exception : "+ex);
				}
			}
			
			
			//ArrayList<AddressBean> address = new ArrayList<AddressBean>();
			
		}else {
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
