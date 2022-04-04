package com.inexture.Utilities;

import java.util.ArrayList;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;

public class Validation {
	public static boolean validate(UserBean u) {
		
		String fname = u.getFname();
		String lname = u.getLname();
		String email = u.getEmail();
		long phone = u.getPhone();
		String gender = u.getGender();
		String birthdate = u.getBirthdate();
		String hobby = u.getHobby();
		String que1 = u.getQue1();
		String que2 = u.getQue2();
		String que3 = u.getQue3();
		ArrayList<AddressBean> address = u.getAddress();
		
		if(fname.equals("") || fname.length()>255) {
			return false;
		}else if(lname.equals("") || lname.length()>255) {
			return false;
		}else if(email.equals("") || email.length()>255) {
			return false;
		}else if(phone == 0) {
			return false;
		}else if(gender.equals("") || gender.length()>255) {
			return false;
		}else if(birthdate.equals("") || birthdate.length()>255) {
			return false;
		}else if(hobby.equals("") || hobby.length()>255) {
			return false;
		}else if(que1.equals("") || que1.length()>255) {
			return false;
		}else if(que2.equals("") || que2.length()>255) {
			return false;
		}else if(que3.equals("") || que3.length()>255) {
			return false;
		}else if(address.size()<1) {
			return false;
		}
		else {
			for(AddressBean a : address) {
				
				String home = a.getHome();
				String city = a.getCity();
				String state = a.getState();
				String country = a.getCountry();
				String pincode = a.getPincode();
				
				if(home.equals(null) || home.length()>255) {
					return false;
				}else if(city.equals(null) || city.length()>255) {
					return false;
				}else if(state.equals(null) || state.length()>255) {
					return false;
				}else if(country.equals(null) || country.length()>255) {
					return false;
				}else if(pincode.equals(null) || pincode.length()>255) {
					return false;
				}
			}
			return true;
			
		}
	}
}
