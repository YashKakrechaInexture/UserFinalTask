package com.inexture.Services;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class FindUserService implements FindUserInterface{
	@Override
	public int FindUser(UserBean u) {
		DaoMethods dm = new DaoMethods();
		
		return 0;
	}
}
