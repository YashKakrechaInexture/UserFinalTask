package com.inexture.Services;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;
import com.inexture.Utilities.ShaEncryption;

public class LoginService {
	public int checkUser(UserBean u) {
		
		//encrypting password
		ShaEncryption sha = new ShaEncryption();
		u.setPassword( sha.passwordEncryption( u.getPassword() ) );
		
		//checking if user/admin exist
		DaoMethods dm = new DaoMethods();
		int type = dm.AuthUser(u);
		
		//returns type : 0-user/1-admin
		return type;
	}
}
