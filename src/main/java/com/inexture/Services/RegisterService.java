package com.inexture.Services;

import org.apache.log4j.Logger;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;
import com.inexture.Utilities.ShaEncryption;

public class RegisterService implements RegisterInterface{
	static Logger log = Logger.getLogger(RegisterService.class);
	@Override
	public void RegisterUser(UserBean u) {
		
		log.debug("Inside Register Service.");
		
		DaoMethods dm = new DaoMethods();
		
		//checking if user already exist
		if(dm.CheckUser(u.getEmail())) {
			
			log.debug("No email found, registering to database.");
			
			//encrypting password
			ShaEncryption sha = new ShaEncryption();
			u.setPassword( sha.passwordEncryption( u.getPassword() ) );
			
			log.info("Password Encrypted.");
			
			//adding user details in table
			dm.Register(u);
			
			log.debug("User Registered.");
			
			//getting uid
			int uid = dm.GetUid(u.getEmail());
			
			//adding address in table
			if(uid>0) {

				log.debug("Adding all address in database.");
				
				//adding all addresses in table
				for(AddressBean a : u.getAddress()) {
					dm.AddAddress(a,uid);
				}
			}
		}
	}
}
