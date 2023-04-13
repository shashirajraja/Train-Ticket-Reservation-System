package com.shashi.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shashi.beans.TrainException;
import com.shashi.beans.UserBean;
import com.shashi.constant.ResponseCode;
import com.shashi.constant.UserRole;
import com.shashi.service.UserService;
import com.shashi.utility.DBUtil;

public class UserServiceImpl implements UserService {

	private final String TABLE_NAME;

	public UserServiceImpl(UserRole userRole) {
		if (userRole == null) {
			userRole = UserRole.CUSTOMER;
		}
		this.TABLE_NAME = userRole.toString();
	}

	@Override
	public UserBean getUserByEmailId(String customerEmailId) throws TrainException {
		UserBean customer = null;
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE MAILID=?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, customerEmailId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new UserBean();
				customer.setFName(rs.getString("fname"));
				customer.setLName(rs.getString("lname"));
				customer.setAddr(rs.getString("addr"));
				customer.setMailId(rs.getString("mailid"));
				customer.setPhNo(rs.getLong("phno"));
			} else {
				throw new TrainException(ResponseCode.NO_CONTENT);
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new TrainException(e.getMessage());
		}
		return customer;
	}

	@Override
	public List<UserBean> getAllUsers() throws TrainException {
		List<UserBean> customers = null;
		String query = "SELECT * FROM  " + TABLE_NAME;
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			customers = new ArrayList<UserBean>();
			while (rs.next()) {
				UserBean customer = new UserBean();
				customer.setFName(rs.getString("fname"));
				customer.setLName(rs.getString("lname"));
				customer.setAddr(rs.getString("addr"));
				customer.setMailId(rs.getString("mailid"));
				customer.setPhNo(rs.getLong("phno"));
				customers.add(customer);
			}

			if (customers.isEmpty()) {
				throw new TrainException(ResponseCode.NO_CONTENT);
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new TrainException(e.getMessage());
		}
		return customers;
	}

	@Override
	public String updateUser(UserBean customer) {
		String responseCode = ResponseCode.FAILURE.toString();
		String query = "UPDATE  " + TABLE_NAME + " SET FNAME=?,LNAME=?,ADDR=?,PHNO=? WHERE MAILID=?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, customer.getFName());
			ps.setString(2, customer.getLName());
			ps.setString(3, customer.getAddr());
			ps.setLong(4, customer.getPhNo());
			ps.setString(5, customer.getMailId());
			int response = ps.executeUpdate();
			if (response > 0) {
				responseCode = ResponseCode.SUCCESS.toString();
			}
			ps.close();
		} catch (SQLException | TrainException e) {
			responseCode += " : " + e.getMessage();
		}
		return responseCode;
	}

	@Override
	public String deleteUser(UserBean customer) {
		String responseCode = ResponseCode.FAILURE.toString();
		String query = "DELETE FROM " + TABLE_NAME + " WHERE MAILID=?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, customer.getMailId());

			int response = ps.executeUpdate();
			if (response > 0) {
				responseCode = ResponseCode.SUCCESS.toString();
			}
			ps.close();
		} catch (SQLException | TrainException e) {
			responseCode += " : " + e.getMessage();
		}
		return responseCode;
	}

	@Override
	public String registerUser(UserBean customer) {
		String responseCode = ResponseCode.FAILURE.toString();
		String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?,?,?)";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, customer.getMailId());
			ps.setString(2, customer.getPWord());
			ps.setString(3, customer.getFName());
			ps.setString(4, customer.getLName());
			ps.setString(5, customer.getAddr());
			ps.setLong(6, customer.getPhNo());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				responseCode = ResponseCode.SUCCESS.toString();
			}
			ps.close();
		} catch (SQLException | TrainException e) {
			if (e.getMessage().toUpperCase().contains("ORA-00001")) {
				responseCode += " : " + "User With Id: " + customer.getMailId() + " is already registered ";
			} else {
				responseCode += " : " + e.getMessage();
			}
		}
		return responseCode;
	}

	@Override
	public UserBean loginUser(String username, String password) throws TrainException {
		UserBean customer = null;
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE MAILID=? AND PWORD=?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new UserBean();
				customer.setFName(rs.getString("fname"));
				customer.setLName(rs.getString("lname"));
				customer.setAddr(rs.getString("addr"));
				customer.setMailId(rs.getString("mailid"));
				customer.setPhNo(rs.getLong("phno"));
				customer.setPWord(rs.getString("pword"));
			} else {
				throw new TrainException(ResponseCode.UNAUTHORIZED);
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new TrainException(e.getMessage());
		}
		return customer;
	}

}
