package com.inexture.Services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class EditService {
	public void EditProfile(UserBean u) {
		DaoMethods dm = new DaoMethods();
		dm.GetUserInfo(u);
		dm.GetAddressInfo(u);
		
		InputStream inputStream = u.getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			
			byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	         
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);                  
	        }
	         
	        byte[] imageBytes = outputStream.toByteArray();
	        
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
	}
}
