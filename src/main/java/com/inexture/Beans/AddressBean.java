package com.inexture.Beans;

public class AddressBean {
	private int aid;
	private String home;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
	public AddressBean(int aid,String home, String city, String state, String country, String pincode) {
		this.aid = aid;
		this.home = home;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	public AddressBean(String home, String city, String state, String country, String pincode) {
		this.home = home;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public int getAid() {
		return this.aid;
	}
	public String getHome() {
		return this.home;
	}
	public String getCity() {
		return this.city;
	}
	public String getState() {
		return this.state;
	}
	public String getCountry() {
		return this.country;
	}
	public String getPincode() {
		return this.pincode;
	}
}
