package com.shashi.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.shashi.beans.TrainException;
import com.shashi.constant.ResponseCode;

public class DBUtil {
	private static Connection con;

	static {

		ResourceBundle rb = ResourceBundle.getBundle("application");

		try {
			Class.forName(rb.getString("driverName"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(rb.getString("connectionString"));
			System.out.println(rb.getString("username"));
			System.out.println(rb.getString("password"));
			con = DriverManager.getConnection(rb.getString("connectionString"), rb.getString("username"),
					rb.getString("password"));
			System.out.println("Connection Success!!");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws TrainException {
		if (con == null)
			throw new TrainException(ResponseCode.DATABASE_CONNECTION_FAILURE);
		return con;
	}
}
