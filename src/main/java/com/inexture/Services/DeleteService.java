package com.inexture.Services;

import com.inexture.DAO.DaoMethods;

public class DeleteService implements DeleteInterface{
	@Override
	public void DeleteAddress(int aid) {
		
		DaoMethods dm = new DaoMethods();
		dm.DeleteAddress(aid);
		
	}
	@Override
	public void DeleteUser(int uid) {
		
		DaoMethods dm = new DaoMethods();
		dm.DeleteUser(uid);
		
	}
}
