package com.inexture.Services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;

import org.apache.log4j.Logger;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class EditService implements EditInterface{
	static Logger log = Logger.getLogger(EditService.class);
	@Override
	public void EditProfile(UserBean u) {
		
		log.debug("Inside Edit profile service");
		
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
	        
	        log.debug("Converted image to base64image");
	        
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			
			try {
				
				if(inputStream != null) {
					log.debug("InputStream closed.");
					inputStream.close();    
				}
				if(outputStream != null) {
					log.debug("OutputStream closed.");
					outputStream.close();    
				}

			}catch(Exception ex) {
				log.fatal("Something went wrong! Exception : "+ex);
			}
		}
	}
}
