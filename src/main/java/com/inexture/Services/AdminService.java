package com.inexture.Services;

import java.util.List;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class AdminService {
	public void showUsers(List<UserBean> list) {
		DaoMethods dm = new DaoMethods();
		dm.showUserData(list);
	}
}
