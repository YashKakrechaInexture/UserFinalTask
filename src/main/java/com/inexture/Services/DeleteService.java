package com.inexture.Services;

import org.apache.log4j.Logger;

import com.inexture.DAO.DaoMethods;

public class DeleteService implements DeleteInterface{
	static Logger log = Logger.getLogger(DeleteService.class);
	@Override
	public void DeleteUser(int uid) {
		
		log.debug("Inside Delete User Service");
		
		DaoMethods dm = new DaoMethods();
		dm.DeleteUser(uid);
		
	}
}
