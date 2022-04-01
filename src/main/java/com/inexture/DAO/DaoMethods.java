package com.inexture.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;

public class DaoMethods implements DaoInterface{
	@Override
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
				if(rs != null) {
					rs.close();
				}
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
		
		return false;
	}
	@Override
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
			st.setString(12, "user");
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
	@Override
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
				if(rs != null) {
					rs.close();
				}
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
		
		return 0;
	}
	@Override
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
	@Override
	public UserBean AuthUser(String email,String password){
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select * from users where email=? and password=?;");
			
			st.setString(1, email );
			st.setString(2, password );
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				UserBean u = new UserBean(email);
				u.setUid(rs.getInt("uid"));
				u.setFname(rs.getString("firstname"));
				u.setLname(rs.getString("lastname"));
				u.setEmail(rs.getString("email"));
				u.setPhone(rs.getInt("phone"));
				u.setGender(rs.getString("gender"));
				u.setBirthdate(rs.getString("birthdate"));
				u.setHobby(rs.getString("hobby"));
				u.setQue1(rs.getString("ans1"));
				u.setQue2(rs.getString("ans2"));
				u.setQue3(rs.getString("ans3"));
				u.setType(rs.getString("type"));
				u.setInputStream(rs.getBlob("image").getBinaryStream());
				
				return u;
			}else {
				return null;
			}
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
				}
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
		return null;
	}
	@Override
	public void showUserData(List<UserBean> list,String type) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select uid,firstname,lastname,email,phone,gender,birthdate,hobby from users where type=?;");
			
			st.setString(1, type);

			rs = st.executeQuery();
			
			while(rs.next()) {
				list.add( new UserBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8)) );
			}
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
				}
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
	@Override
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
				if(rs != null) {
					rs.close();
				}
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
	@Override
	public void GetAddressInfo(UserBean u) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select aid,home,city,state,country,pincode from addresses where uid=?;");
			
			st.setInt(1, u.getUid());

			rs = st.executeQuery();
			
			ArrayList<AddressBean> list = new ArrayList<AddressBean>();
			
			while(rs.next()) {
				list.add( new AddressBean( rs.getInt("aid"),rs.getString("home"),rs.getString("city"),rs.getString("state"),rs.getString("country"),rs.getString("pincode") ) );
			}
			
			u.setAddress(list);
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
				}
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
	@Override
	public void UpdateImage(UserBean u) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("update users set image=? where email=?");
			
			st.setBlob(1, u.getInputStream());
			st.setString(2, u.getEmail());
			
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
	@Override
	public void UpdateUserDetail(UserBean u) {
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
	@Override
	public List<Integer> GetAid(int uid) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select aid from addresses where uid=?;");
			
			st.setInt(1, uid);

			rs = st.executeQuery();
			
			List<Integer> list = new ArrayList<Integer>();
			
			while(rs.next()) {
				list.add( rs.getInt("aid") );
			}
			
			return list;
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
				}
				if(st != null){
					st.close();
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
		return null;
	}
	@Override
	public void UpdateAddress(AddressBean a,int aid) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("update addresses set home=?,city=?,state=?,country=?,pincode=? where aid=?;");
			
			st.setString(1, a.getHome());
			st.setString(2, a.getCity());
			st.setString(3, a.getState());
			st.setString(4, a.getCountry());
			st.setString(5, a.getPincode());
			st.setInt(6, aid);
			
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
	
	@Override
	public void DeleteAddress(int aid) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from addresses where aid=?");

			st.setInt(1, aid);

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
	@Override
	public void DeleteUser(int uid) {
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from users where uid=?");

			st.setInt(1, uid);

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
