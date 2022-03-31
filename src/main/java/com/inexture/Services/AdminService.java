package com.inexture.Services;

import java.util.ArrayList;
import java.util.List;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class AdminService implements AdminInterface {
	@Override
	public List<UserBean> showUsers(String type) {
		List<UserBean> list = new ArrayList<UserBean>();
		
		DaoMethods dm = new DaoMethods();
		dm.showUserData(list,type);
		
		return list;
	}
}