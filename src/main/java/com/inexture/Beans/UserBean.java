package com.inexture.Beans;

import java.io.InputStream;
import java.util.ArrayList;

public class UserBean {
	private String fname;
	private String lname;
	private String email;
	private long phone;
	private String password1;
	private String password2;
	private String gender;
	private String birthdate;
	private String hobby;
	private String que1;
	private String que2;
	private String que3;
	private ArrayList<AddressBean> address;
	private InputStream inputStream;
	
	public UserBean(String fname,String lname,String email,long phone,String password1,String password2,String gender,String birthdate,String hobby,String que1,String que2,String que3,ArrayList<AddressBean> address,InputStream inputStream){
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.password1 = password1;
		this.password2 = password2;
		this.gender = gender;
		this.birthdate = birthdate;
		this.hobby = hobby;
		this.que1 = que1;
		this.que2 = que2;
		this.que3 = que3;
		this.address = address;
		this.inputStream = inputStream;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public void setQue1(String que1) {
		this.que1 = que1;
	}
	public void setQue2(String que2) {
		this.que2 = que2;
	}
	public void setQue3(String que3) {
		this.que3 = que3;
	}
	public void setAddress(ArrayList<AddressBean> address) {
		this.address = address;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String getFname() {
		return this.fname;
	}
	public String getLname() {
		return this.lname;
	}
	public String getEmail() {
		return this.email;
	}
	public long getPhone() {
		return this.phone;
	}
	public String getPassword1() {
		return this.password1;
	}
	public String getPassword2() {
		return this.password2;
	}
	public String getGender() {
		return this.gender;
	}
	public String getBirthdate() {
		return this.birthdate;
	}
	public String getHobby() {
		return this.hobby ;
	}
	public String getQue1() {
		return this.que1;
	}
	public String getQue2() {
		return this.que2;
	}
	public String getQue3() {
		return this.que3;
	}
	public ArrayList<AddressBean> getAddress() {
		return this.address;
	}
	public InputStream getInputStream() {
		return this.inputStream;
	}
}
