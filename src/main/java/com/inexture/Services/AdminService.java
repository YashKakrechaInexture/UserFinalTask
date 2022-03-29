package com.inexture.Services;

import java.util.ArrayList;
import java.util.List;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class AdminService {
	public List<UserBean> showUsers() {
		List<UserBean> list = new ArrayList<UserBean>();
		
		DaoMethods dm = new DaoMethods();
		dm.showUserData(list);
		
		return list;
	}
}