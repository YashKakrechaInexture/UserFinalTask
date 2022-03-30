package com.inexture.Services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;

import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class UpdateService {
	public void updateUser(UserBean u) {
		
		DaoMethods dm = new DaoMethods();
		dm.UpdateWithPic(u);
		
	}
	public void updateUserWithoutPic(UserBean u) {
		DaoMethods dm = new DaoMethods();
		dm.UpdateWithoutPic(u);
	}
}
