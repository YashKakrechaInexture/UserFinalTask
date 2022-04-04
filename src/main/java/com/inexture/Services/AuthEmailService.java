package com.inexture.Services;

import com.inexture.DAO.DaoMethods;

public class AuthEmailService implements AuthEmailInterface{
	@Override
	public boolean CheckEmail(String email) {
		DaoMethods dm = new DaoMethods();
		return dm.CheckUser(email);
	}
}
