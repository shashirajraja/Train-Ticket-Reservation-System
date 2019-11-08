package com.shashi.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {
	private static Connection con;
	private DBConnection() {};
	static {
		
		ResourceBundle rb = ResourceBundle.getBundle("com.shashi.utility.database");
		
		try {
			Class.forName(rb.getString("driverName"));
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			con = DriverManager.getConnection(rb.getString("connectionString"),rb.getString("username"),rb.getString("password"));
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	public static Connection getCon()
	{
		return con;
	}
}
