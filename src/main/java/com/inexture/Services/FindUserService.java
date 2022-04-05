package com.inexture.Services;

import org.apache.log4j.Logger;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class FindUserService implements FindUserInterface{
	static Logger log = Logger.getLogger(FindUserService.class);
	@Override
	public boolean FindUser(UserBean u) {
		log.debug("Inside FindUser Service.");
		DaoMethods dm = new DaoMethods();
		return dm.FindUser(u);
	}
}
