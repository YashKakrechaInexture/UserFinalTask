package com.inexture.Services;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;
import com.inexture.Utilities.ShaEncryption;

public class RegisterService implements RegisterInterface{
	@Override
	public void RegisterUser(UserBean u) {
		
		DaoMethods dm = new DaoMethods();
		
		//checking if user already exist
		if(dm.CheckUser(u.getEmail())) {
			
			//encrypting password
			ShaEncryption sha = new ShaEncryption();
			u.setPassword( sha.passwordEncryption( u.getPassword() ) );
			
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
