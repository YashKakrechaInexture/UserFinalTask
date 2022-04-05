package com.inexture.Services;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;
import com.inexture.Utilities.ShaEncryption;
import org.apache.log4j.Logger;

public class LoginService implements LoginInterface{
	static Logger log = Logger.getLogger(LoginService.class);
	
	@Override
	public UserBean checkUser(String email,String password) {
		
		//encrypting password
		ShaEncryption sha = new ShaEncryption();
		password = sha.passwordEncryption(password);
		
		log.info("Password Encrypted.");
		
		//checking if user/admin exist
		DaoMethods dm = new DaoMethods();
		UserBean u = dm.AuthUser(email,password);
		
		log.debug("Checked User in Dao.");
		
		return u;
		
	}
}
