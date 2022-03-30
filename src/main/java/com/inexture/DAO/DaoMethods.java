package com.inexture.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;

public class DaoMethods {
	public boolean CheckUser(String email) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select uid from users where email=?");
			
			st.setString(1, email);
			
			rs = st.executeQuery();
			
			System.out.println("Submitted data in table");
			
			if(rs.next()) {
				return false;
			}else {
				return true;
			}
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
		
		return false;
	}
	public void Register(UserBean u) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		try {
			
			st = conn.prepareStatement("insert into users (firstname,lastname,email,phone,password,gender,birthdate,hobby,ans1,ans2,ans3,type,image)"+
			" values (?,?,?,?,?,?,?,?,?,?,?,?,?);");
			
			st.setString(1, u.getFname());
			st.setString(2, u.getLname());
			st.setString(3, u.getEmail());
			st.setLong(4, u.getPhone());
			st.setString(5, u.getPassword());
			st.setString(6, u.getGender());
			st.setString(7, u.getBirthdate());
			st.setString(8, u.getHobby());
			st.setString(9, u.getQue1());
			st.setString(10, u.getQue2());
			st.setString(11, u.getQue3());
			st.setInt(12, 1);
			st.setBlob(13, u.getInputStream());
			
			st.executeUpdate();
			
			System.out.println("Submitted data in table");
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
	public int GetUid(String email) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select uid from users where email=?");
			
			st.setString(1, email);
			
			rs = st.executeQuery();
			
			System.out.println("Submitted data in table");
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
		
		return 0;
	}
	public void AddAddress(AddressBean a,int uid) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		try {
			
			st = conn.prepareStatement("insert into addresses (uid,home,city,state,country,pincode) values (?,?,?,?,?,?);");
			
			st.setInt(1, uid);
			st.setString(2, a.getHome());
			st.setString(3, a.getCity());
			st.setString(4, a.getState());
			st.setString(5, a.getCountry());
			st.setString(6, a.getPincode());
			
			st.executeUpdate();
			
			System.out.println("Submitted data in table");
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
	public int AuthUser(UserBean u){
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select type from users where email=? and password=?;");
			
			st.setString(1, u.getEmail() );
			st.setString(2, u.getPassword() );
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
		return 2;
	}
	public void showUserData(List<UserBean> list) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select uid,firstname,lastname,email,phone,gender,birthdate,hobby from users where type=?;");
			
			//type=1 ,i.e. Users. (0=admin,1=users)
			st.setInt(1, 1);

			rs = st.executeQuery();
			
			while(rs.next()) {
				list.add( new UserBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8)) );
			}
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
	public void GetUserInfo(UserBean u) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select uid,firstname,lastname,phone,gender,birthdate,hobby,ans1,ans2,ans3,image from users where email=?;");
			
			st.setString(1, u.getEmail());

			rs = st.executeQuery();
			
			if(rs.next()) {
				u.setUid(rs.getInt("uid"));
				u.setFname(rs.getString("firstname"));
				u.setLname(rs.getString("lastname"));
				u.setPhone( Long.parseLong(rs.getString("phone")) );
				u.setGender(rs.getString("gender"));
				u.setBirthdate(rs.getString("birthdate"));
				u.setHobby(rs.getString("hobby"));
				u.setQue1(rs.getString("ans1"));
				u.setQue2(rs.getString("ans2"));
				u.setQue3(rs.getString("ans3"));
				u.setInputStream(rs.getBlob("image").getBinaryStream());
			}
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
	public void GetAddressInfo(UserBean u) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select home,city,state,country,pincode from addresses where uid=?;");
			
			st.setInt(1, u.getUid());

			rs = st.executeQuery();
			
			ArrayList<AddressBean> list = new ArrayList<AddressBean>();
			
			while(rs.next()) {
				list.add( new AddressBean( rs.getString("home"),rs.getString("city"),rs.getString("state"),rs.getString("country"),rs.getString("pincode") ) );
			}
			
			u.setAddress(list);
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
	public void UpdateWithPic(UserBean u) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("update users set firstname=?,lastname=?,email=?,phone=?,gender=?,birthdate=?,hobby=?,ans1=?,ans2=?,ans3=?,image=? where email=?");
			
			st.setString(1, u.getFname());
			st.setString(2, u.getLname());
			st.setString(3, u.getEmail());
			st.setLong(4, u.getPhone());
			st.setString(5, u.getGender());
			st.setString(6, u.getBirthdate());
			st.setString(7, u.getHobby());
			st.setString(8, u.getQue1());
			st.setString(9, u.getQue2());
			st.setString(10, u.getQue3());
			st.setBlob(11, u.getInputStream());
			st.setString(12, u.getEmail());
			
			st.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
	public void UpdateWithoutPic(UserBean u) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("update users set firstname=?,lastname=?,email=?,phone=?,gender=?,birthdate=?,hobby=?,ans1=?,ans2=?,ans3=? where email=?");
			
			st.setString(1, u.getFname());
			st.setString(2, u.getLname());
			st.setString(3, u.getEmail());
			st.setLong(4, u.getPhone());
			st.setString(5, u.getGender());
			st.setString(6, u.getBirthdate());
			st.setString(7, u.getHobby());
			st.setString(8, u.getQue1());
			st.setString(9, u.getQue2());
			st.setString(10, u.getQue3());
			st.setString(11, u.getEmail());
			
			st.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
}
