package com.inexture.Services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.DAO.AddressDaoMethods;
import com.inexture.DAO.DaoMethods;
import com.inexture.Utilities.ShaEncryption;

/**
 * User service implementation class. It implements all the user related services and calls the dao for CRUD user/address.
 * @author Yash
 *
 */
public class UserService implements UserInterface{
	static Logger log = Logger.getLogger(UserService.class);
	
	@Override
	public List<UserBean> showUsers(String type) {
		
		log.debug("Inside ShowUser service.");
		List<UserBean> list = new ArrayList<UserBean>();
		
		DaoMethods dm = new DaoMethods();
		dm.showUserData(list,type);
		
		log.debug("List is returning to Servlet.");
		
		return list;
	}
	
	@Override
	public void deleteUser(int uid) {
		
		log.debug("Inside Delete User Service");
		
		DaoMethods dm = new DaoMethods();
		dm.deleteUser(uid);
		
	}
	
	@Override
	public void updateUser(UserBean u,String fileName) {
		
		log.debug("Inside Update Service.");
		
		DaoMethods dm = new DaoMethods();
		dm.updateUserDetail(u);
		
		log.debug("User updated.");
		
		if(fileName!=null && !fileName.equals("")) {
			log.debug("Updating image.");
			dm.updateImage(u);
		}	
		
		AddressDaoMethods am = new AddressDaoMethods();
		
		List<Integer> aid = am.getAid(u.getUid());
		
		List<AddressBean> address = u.getAddress();
		
		if(aid.size()<=address.size()) {
			for(Integer i : aid) {
				log.debug("Updating addresses.");
				am.updateAddress(address.get(0), i);
				address.remove(0);
			}
			for(AddressBean a : address) {
				log.debug("Adding new addresses.");
				am.addAddress(a, u.getUid());
			}
		}else {
			for(AddressBean a : address) {
				log.debug("Updating addresses.");
				am.updateAddress(a, aid.get(0));
				aid.remove(0);
			}
			
			log.debug("Deleting extra addresses.");
			am.deleteAddress(u.getUid(), aid.size());
			
		}
			
	}
	
	@Override
	public void registerUser(UserBean u) {
		
		log.debug("Inside Register Service.");
		
		DaoMethods dm = new DaoMethods();
		AddressDaoMethods am = new AddressDaoMethods();
		//checking if user already exist
		if(dm.checkUser(u.getEmail())) {
			
			log.debug("No email found, registering to database.");
			
			//encrypting password
			ShaEncryption sha = new ShaEncryption();
			u.setPassword( sha.passwordEncryption( u.getPassword() ) );
			
			log.info("Password Encrypted.");
			
			//adding user details in table
			dm.register(u);
			
			log.debug("User Registered.");
			
			//getting uid
			int uid = dm.getUid(u.getEmail());
			
			//adding address in table
			if(uid>0) {

				log.debug("Adding all address in database.");
				
				//adding all addresses in table
				for(AddressBean a : u.getAddress()) {
					am.addAddress(a,uid);
				}
			}
		}
	}
	
	@Override
	public void resetPass(String email,String password) {
		
		log.debug("Inside Reset Password Service.");
		
		//encrypting password
		ShaEncryption sha = new ShaEncryption();
		password = sha.passwordEncryption(password);
		
		log.info("Password Incrypted.");
		
		DaoMethods dm = new DaoMethods();
		dm.changePassword(email, password);
		
		log.info("Password changed");
	}
	
	@Override
	public UserBean checkUser(String email,String password) {
		
		//encrypting password
		ShaEncryption sha = new ShaEncryption();
		password = sha.passwordEncryption(password);
		
		log.info("Password Encrypted.");
		
		//checking if user/admin exist
		DaoMethods dm = new DaoMethods();
		UserBean u = dm.authUser(email,password);
		
		log.debug("Checked User in Dao.");
		
		return u;
		
	}
	
	@Override
	public void editProfile(UserBean u) {
		
		log.debug("Inside Edit profile service");
		
		DaoMethods dm = new DaoMethods();
		dm.getUserInfo(u);
		
		AddressDaoMethods am = new AddressDaoMethods();
		am.getAddressInfo(u);
		
		this.convertToBase64Image(u);
	}
	
	@Override
	public boolean checkEmail(String email) {
		log.debug("Inside AuthEmail Service.");
		
		DaoMethods dm = new DaoMethods();
		
		return dm.checkUser(email);
		
	}
	
	@Override
	public boolean findUser(UserBean u) {
		log.debug("Inside FindUser Service.");
		DaoMethods dm = new DaoMethods();
		return dm.findUser(u);
	}
	
	@Override
	public void convertToBase64Image(UserBean u) {
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
