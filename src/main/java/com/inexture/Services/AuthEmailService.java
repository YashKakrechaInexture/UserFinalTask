package com.inexture.Services;

import org.apache.log4j.Logger;

import com.inexture.DAO.DaoMethods;

public class AuthEmailService implements AuthEmailInterface{
	static Logger log = Logger.getLogger(AuthEmailService.class);
	@Override
	public boolean CheckEmail(String email) {
		log.debug("Inside AuthEmail Service.");
		
		DaoMethods dm = new DaoMethods();
		
		return dm.CheckUser(email);
		
	}
}
