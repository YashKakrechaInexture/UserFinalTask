package com.inexture.Services;

import java.util.List;

import org.apache.log4j.Logger;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.DAO.DaoMethods;

public class UpdateService implements UpdateInterface {
	static Logger log = Logger.getLogger(UpdateService.class);
	@Override
	public void updateUser(UserBean u,String fileName) {
		
		log.debug("Inside Update Service.");
		
		DaoMethods dm = new DaoMethods();
		dm.UpdateUserDetail(u);
		
		log.debug("User updated.");
		
		if(!fileName.equals("")) {
			log.debug("Updating image.");
			dm.UpdateImage(u);
		}	
		
		List<Integer> aid = dm.GetAid(u.getUid());
		
		List<AddressBean> address = u.getAddress();
		
		if(aid.size()<=address.size()) {
			for(Integer i : aid) {
				log.debug("Updating addresses.");
				dm.UpdateAddress(address.get(0), i);
				address.remove(0);
			}
			for(AddressBean a : address) {
				log.debug("Adding new addresses.");
				dm.AddAddress(a, u.getUid());
			}
		}else {
			for(AddressBean a : address) {
				log.debug("Updating addresses.");
				dm.UpdateAddress(a, aid.get(0));
				aid.remove(0);
			}
			
			log.debug("Deleting extra addresses.");
			dm.DeleteAddress(u.getUid(), aid.size());
			
		}
			
	}
}
