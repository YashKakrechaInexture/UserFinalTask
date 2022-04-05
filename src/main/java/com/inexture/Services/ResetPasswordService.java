package com.inexture.Services;

import org.apache.log4j.Logger;

import com.inexture.DAO.DaoMethods;
import com.inexture.Utilities.ShaEncryption;

public class ResetPasswordService implements ResetPasswordInterface{
	static Logger log = Logger.getLogger(ResetPasswordService.class);
	@Override
	public void ResetPass(String email,String password) {
		
		log.debug("Inside Reset Password Service.");
		
		//encrypting password
		ShaEncryption sha = new ShaEncryption();
		password = sha.passwordEncryption(password);
		
		log.info("Password Incrypted.");
		
		DaoMethods dm = new DaoMethods();
		dm.ChangePassword(email, password);
		
		log.info("Password changed");
	}
}
