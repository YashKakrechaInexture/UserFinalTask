package com.inexture.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;

public class DaoMethods implements DaoInterface{
	static Logger log = Logger.getLogger(DaoMethods.class);
	@Override
	public boolean CheckUser(String email) {
		
		log.debug("Inside Dao. Checking email exist or not.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select uid from users where email=?");
			
			st.setString(1, email);
			
			rs = st.executeQuery();
			
			log.info("Query executed successfully.");
			
			if(rs.next()) {
				log.debug("User found in table.");
				return false;
			}else {
				log.debug("No User found in table.");
				return true;
			}
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			log.debug("Started closing PreparedStatement and ResultSet.");
			try{
				if(rs != null) {
					rs.close();
					log.info("ResultSet Closed.");
				}
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
		
		return false;
	}
	@Override
	public void Register(UserBean u) {
		
		log.debug("Inside Dao. Registering User.");
		
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
			
			log.info("User stored in database.");
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
	@Override
	public int GetUid(String email) {
		
		log.debug("Inside Dao. Getting Uid from email.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select uid from users where email=?");
			
			st.setString(1, email);
			
			rs = st.executeQuery();
			
			log.info("Query executed successfully.");
			
			if(rs.next()) {
				
				log.debug("Uid found.");
				
				return rs.getInt(1);
				
			}else {
				
				log.fatal("Uid not found for given email.");
				
				return 0;
			}
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
					log.info("ResultSet Closed.");
				}
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
		
		return 0;
	}
	@Override
	public void AddAddress(AddressBean a,int uid) {
		
		log.debug("Inside Dao. Storing Address in database.");
		
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
			
			log.info("Query executed successfully.");
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
	@Override
	public UserBean AuthUser(String email,String password){
		
		log.debug("Inside Dao. Authorizing user.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select * from users where email=? and password=?;");
			
			st.setString(1, email );
			st.setString(2, password );
			
			rs = st.executeQuery();
			
			log.info("Query executed successfully.");
			
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
				
				log.debug("User data stored in bean.");
				
				return u;
			}else {
				
				log.debug("No user found in database, returning null.");
				
				return null;
			}
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
					log.info("ResultSet Closed.");
				}
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
		return null;
	}
	@Override
	public void showUserData(List<UserBean> list,String type) {
		
		log.debug("Inside Dao. Creating Users List.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select uid,firstname,lastname,email,phone,gender,birthdate,hobby from users where type=?;");
			
			st.setString(1, type);

			rs = st.executeQuery();
			
			log.info("Query executed successfully.");
			
			while(rs.next()) {
				list.add( new UserBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8)) );
			}
			
			log.debug("User data stored in list.");
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
					log.info("ResultSet Closed.");
				}
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
	@Override
	public void GetUserInfo(UserBean u) {
		
		log.debug("Inside Dao. Getting User Detail from email.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select uid,firstname,lastname,phone,gender,birthdate,hobby,ans1,ans2,ans3,image from users where email=?;");
			
			st.setString(1, u.getEmail());

			rs = st.executeQuery();
			
			log.info("Query executed successfully.");
			
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
				
				log.debug("User data stored in bean.");
				
			}
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
					log.info("ResultSet Closed.");
				}
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
	@Override
	public void GetAddressInfo(UserBean u) {
		
		log.debug("Inside Dao. Getting Address list using user id.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select aid,home,city,state,country,pincode from addresses where uid=?;");
			
			st.setInt(1, u.getUid());

			rs = st.executeQuery();
			
			log.info("Query executed successfully.");
			
			ArrayList<AddressBean> list = new ArrayList<AddressBean>();
			
			while(rs.next()) {
				list.add( new AddressBean( rs.getInt("aid"),rs.getString("home"),rs.getString("city"),rs.getString("state"),rs.getString("country"),rs.getString("pincode") ) );
			}
			
			log.debug("Addresses stored in list.");
			
			u.setAddress(list);
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
					log.info("ResultSet Closed.");
				}
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				System.out.println("Exception2 : "+ep);
			}
		}
	}
	@Override
	public void UpdateImage(UserBean u) {
		
		log.debug("Inside Dao. Updating Image.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("update users set image=? where email=?");
			
			st.setBlob(1, u.getInputStream());
			st.setString(2, u.getEmail());
			
			st.executeUpdate();
			
			log.info("Query executed successfully.");
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
	@Override
	public void UpdateUserDetail(UserBean u) {
		
		log.debug("Inside Dao. Updating user details.");
		
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

			log.info("Query executed successfully.");
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
	@Override
	public List<Integer> GetAid(int uid) {
		
		log.debug("Inside Dao. Getting address id list using user's id.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("select aid from addresses where uid=?;");
			
			st.setInt(1, uid);

			rs = st.executeQuery();
			
			log.info("Query executed successfully.");
			
			List<Integer> list = new ArrayList<Integer>();
			
			while(rs.next()) {
				list.add( rs.getInt("aid") );
			}
			
			log.debug("Aid stored in list.");
			
			return list;
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
					log.info("ResultSet Closed.");
				}
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
		return null;
	}
	@Override
	public void UpdateAddress(AddressBean a,int aid) {
		
		log.debug("Inside Dao. Updating Address.");
		
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
			
			log.info("Query executed successfully.");
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
	
	@Override
	public void DeleteAddress(int uid,int length) {
		
		log.debug("Inside Dao. Deleting Address.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from addresses where uid=? order by aid desc limit ?");

			st.setInt(1, uid);
			st.setInt(2, length);

			st.executeUpdate();

			log.info("Query executed successfully.");
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
	@Override
	public void DeleteUser(int uid) {
		
		log.debug("Inside Dao. Deleting user.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from users where uid=?");

			st.setInt(1, uid);

			st.executeUpdate();

			log.info("Query executed successfully.");
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
	@Override
	public boolean FindUser(UserBean u) {
		
		log.debug("Inside Dao. Finding user for forgot password.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("select uid from users where email=? and birthdate=? and ans1=? and ans2=? and ans3=?");
			
			st.setString(1, u.getEmail());
			st.setString(2, u.getBirthdate());
			st.setString(3, u.getQue1());
			st.setString(4, u.getQue2());
			st.setString(5, u.getQue3());
			
			rs = st.executeQuery();
			
			log.info("Query executed successfully.");
			
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(rs != null) {
					rs.close();
					log.info("ResultSet Closed.");
				}
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
		
		return false;
	}
	@Override
	public void ChangePassword(String email,String Password) {
		
		log.debug("Inside Dao. Changing password.");
		
		Connection conn = DaoConnectionClass.getConnection();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("update users set password=? where email=?");
			
			st.setString(1, Password);
			st.setString(2, email);
			
			st.executeUpdate();

			log.info("Query executed successfully.");
			
		}catch(Exception e) {
			log.fatal("Something went wrong! Exception : "+e);
		}finally {
			try{
				if(st != null){
					st.close();
					log.info("PreparedStatement Closed.");
				}
			}catch(Exception ep){
				log.fatal("Something went wrong! Exception : "+ep);
			}
		}
	}
}
