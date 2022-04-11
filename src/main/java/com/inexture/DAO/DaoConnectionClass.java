package com.inexture.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;


/**
 * Dao connection singleton class. Returns single instance of the database connection.
 * @author Yash
 *
 */
public final class DaoConnectionClass {
	
	
	/**
	 * Stores Connection of database
	 */
	private static Connection conn = null;
	
	/**
	 * Private constructor, to avoid creating object.
	 */
	private DaoConnectionClass() {}
	
	/**
	 * Logger
	 */
	static final Logger LOG = Logger.getLogger(DaoConnectionClass.class);
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String pass = "Yash@1234";
			String url = "jdbc:mysql://localhost:3306/userfinaltask";
			
			conn = DriverManager.getConnection(url,user,pass);
			
			LOG.info("Database connection initialized.");
			
		}catch(Exception e) {
			LOG.fatal("Cannot create Database connection. Exception : "+e);
		}
	}
	
	
	/**
	 * Returns a single instance of the mysql database 
	 * @return conn - Stores Connection of database
	 */
	public static Connection getConnection() {
		LOG.debug("Returned Database connection");
		return conn;
	}
}
