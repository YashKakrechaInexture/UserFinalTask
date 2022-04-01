package com.inexture.Services;

import java.util.List;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class UpdateService implements UpdateInterface {
	@Override
	public void updateUser(UserBean u,String fileName) {
		
		DaoMethods dm = new DaoMethods();
		dm.UpdateUserDetail(u);
		
		if(!fileName.equals("")) {
			dm.UpdateImage(u);
		}	
		
		List<Integer> aid = dm.GetAid(u.getUid());
		
		List<AddressBean> address = u.getAddress();
		
		if(aid.size()<=address.size()) {
			for(Integer i : aid) {
				dm.UpdateAddress(address.get(0), i);
				address.remove(0);
			}
			for(AddressBean a : address) {
				dm.AddAddress(a, u.getUid());
			}
		}else {
			for(AddressBean a : address) {
				dm.UpdateAddress(a, aid.get(0));
				aid.remove(0);
			}
			for(Integer i : aid) {
				dm.DeleteAddress(i);
			}
		}
			
	}
}
