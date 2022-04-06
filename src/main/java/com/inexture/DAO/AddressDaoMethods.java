package com.inexture.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;

/**
 * Address Dao Implementation class. Implements the methods related to create, update, read, delete address's data from database table.
 * @author Yash
 *
 */
public class AddressDaoMethods implements AddressDaoInterface{
	static Logger log = Logger.getLogger(DaoMethods.class);
	
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
}
