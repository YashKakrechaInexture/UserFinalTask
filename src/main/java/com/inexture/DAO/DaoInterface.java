package com.inexture.DAO;

import java.util.List;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;

public interface DaoInterface {
	public boolean CheckUser(String email);
	public void Register(UserBean u);
	public int GetUid(String email);
	public void AddAddress(AddressBean a,int uid);
	public UserBean AuthUser(String email,String password);
	public void showUserData(List<UserBean> list,String type);
	public void GetUserInfo(UserBean u);
	public void GetAddressInfo(UserBean u);
	public void UpdateImage(UserBean u);
	public void UpdateUserDetail(UserBean u);
	public List<Integer> GetAid(int uid);
	public void UpdateAddress(AddressBean a,int aid);
	public void DeleteAddress(int aid);
	public void DeleteUser(int uid);
	public boolean FindUser(UserBean u);
	public void ChangePassword(String email,String Password);
}
