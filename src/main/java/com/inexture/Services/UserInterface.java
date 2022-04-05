package com.inexture.Services;

import com.inexture.Beans.UserBean;

public interface UserInterface {
	public void RegisterUser(UserBean u);
	public void ResetPass(String email,String password);
	public void updateUser(UserBean u,String fileName);
	public void DeleteUser(int uid);
	public UserBean checkUser(String email,String password);
	public void EditProfile(UserBean u);
	public boolean CheckEmail(String email);
	public boolean FindUser(UserBean u);
}
