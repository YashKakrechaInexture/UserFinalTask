package com.inexture.Services;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;
import com.inexture.Utilities.ShaEncryption;
import org.apache.logging.log4j.*;

import org.apache.log4j.PropertyConfigurator;

public class LoginService implements LoginInterface{
	static Logger log = LogManager.getLogger(LoginService.class);

	@Override
	public UserBean checkUser(String email,String password) {
		PropertyConfigurator.configure("log4j.properties");
		
		log.info("My Info Login service");
		
		//encrypting password
		ShaEncryption sha = new ShaEncryption();
		password = sha.passwordEncryption(password);
		
		//checking if user/admin exist
		DaoMethods dm = new DaoMethods();
		UserBean u = dm.AuthUser(email,password);
		
		
		return u;
		
	}
}
