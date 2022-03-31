package com.inexture.Services;

import java.util.List;

import com.inexture.Beans.UserBean;

public interface AdminInterface {
	public List<UserBean> showUsers(String type);
}
