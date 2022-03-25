package com.inexture.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoConnectionClass {
	private static Connection conn = null;
	private DaoConnectionClass() {}
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String pass = "Yash@1234";
			String url = "jdbc:mysql://localhost:3306/userfinaltask";
			
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connection Created");
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
}
