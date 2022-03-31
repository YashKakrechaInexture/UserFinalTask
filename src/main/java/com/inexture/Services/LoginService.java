package com.inexture.Services;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;
import com.inexture.Utilities.ShaEncryption;

public class LoginService implements LoginInterface{
	@Override
	public UserBean checkUser(String email,String password) {
		
		//encrypting password
		ShaEncryption sha = new ShaEncryption();
		password = sha.passwordEncryption(password);
		
		//checking if user/admin exist
		DaoMethods dm = new DaoMethods();
		UserBean u = dm.AuthUser(email,password);
		
		
		return u;
		
	}
}
