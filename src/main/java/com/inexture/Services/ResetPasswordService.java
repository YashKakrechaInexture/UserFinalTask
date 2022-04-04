package com.inexture.Services;

import com.inexture.DAO.DaoMethods;
import com.inexture.Utilities.ShaEncryption;

public class ResetPasswordService implements ResetPasswordInterface{
	@Override
	public void ResetPass(String email,String password) {
		//encrypting password
		ShaEncryption sha = new ShaEncryption();
		password = sha.passwordEncryption(password);
		
		DaoMethods dm = new DaoMethods();
		dm.ChangePassword(email, password);
	}
}
