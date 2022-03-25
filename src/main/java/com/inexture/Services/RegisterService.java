package com.inexture.Services;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class RegisterService {
	public void RegisterUser(UserBean u) {
		
		DaoMethods dm = new DaoMethods();
		
		//checking if user already exist
		if(dm.CheckUser(u.getEmail())) {
			
			//adding user details in table
			dm.Register(u);
			
			//getting uid
			int uid = dm.GetUid(u.getEmail());
			
			//adding address in table
			if(uid>0) {
				
				//adding all addresses in table
				for(AddressBean a : u.getAddress()) {
					dm.AddAddress(a,uid);
				}
			}
		}
	}
}
