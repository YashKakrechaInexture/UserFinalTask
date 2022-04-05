package com.inexture.Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class AdminService implements AdminInterface {
	static Logger log = Logger.getLogger(AdminService.class);
	@Override
	public List<UserBean> showUsers(String type) {
		
		log.debug("Inside ShowUser service.");
		List<UserBean> list = new ArrayList<UserBean>();
		
		DaoMethods dm = new DaoMethods();
		dm.showUserData(list,type);
		
		log.debug("List is returning to Servlet.");
		
		return list;
	}
}