package com.inexture.Services;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class UpdateService implements UpdateInterface {
	@Override
	public void updateUser(UserBean u,String fileName) {
		
		DaoMethods dm = new DaoMethods();
		dm.UpdateUserDetail(u);
		
		if(!fileName.equals("")) {
			dm.UpdateImage(u);
		}	
	}
}
