package com.inexture.Services;

import com.inexture.Beans.UserBean;

public interface LoginInterface {
	public UserBean checkUser(String email,String password);
}
