package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.TrainException;
import com.shashi.beans.UserBean;
import com.shashi.utility.DBUtil;

@SuppressWarnings("serial")
public class AdminLogin extends HttpServlet {

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String uName = req.getParameter("uname");
		String pWord = req.getParameter("pword");
		try {
			Connection con = DBUtil.getCon();

			PreparedStatement ps = con.prepareStatement("select * from admin where mailid=? and pword=?");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Valid ");
				UserBean ub = new UserBean();
//				ub.setUName(rs.getString("uname"));
				ub.setFName(rs.getString("fname"));
				ub.setLName(rs.getString("lname"));
				ub.setPWord(rs.getString("pword"));
				ub.setAddr(rs.getString("addr"));
				ub.setMailId(rs.getString("mailid"));
				ub.setPhNo(Long.parseLong(rs.getString("phno")));

				Cookie ck1 = new Cookie("ckname", uName);
				res.addCookie(ck1);
				Cookie ck2 = new Cookie("ckpwd", pWord);
				res.addCookie(ck2);

				RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu'>Hello, " + uName + " ! Welcome </p1></div>");
				pw.println("<div class='tab'>Hi ! Here You can Manage Train Information as per Your Requirement</div>");

			} else {
				System.out.println("Invalid ");
				RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Invalid Username Or Password !</p1></div>");

			}
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}
	}
}
