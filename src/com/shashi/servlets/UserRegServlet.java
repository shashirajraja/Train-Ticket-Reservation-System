package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.TrainException;
import com.shashi.utility.DBUtil;

public class UserRegServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			Connection con = DBUtil.getCon();
			PreparedStatement ps = con.prepareStatement("insert into customer values(?,?,?,?,?,?)");

			ps.setString(1, req.getParameter("mailid"));
			ps.setString(2, req.getParameter("pword"));
			ps.setString(3, req.getParameter("firstname"));
			ps.setString(4, req.getParameter("lastname"));
			ps.setString(5, req.getParameter("address"));
			ps.setLong(6, Long.parseLong(req.getParameter("phoneno")));
//			ps.setString(7, req.getParameter("mailid"));
			int k = ps.executeUpdate();
			if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>User Registered Successfully !</p1></div>");

			}

			/*
			 * PreparedStatement ps2 =
			 * con.prepareStatement("insert into userphoto values(?,?)"); ps2.setString(1,
			 * req.getParameter("uname")); Part filepart = req.getPart("userphoto");
			 * InputStream inputStream; inputStream = filepart.getInputStream();
			 * ps2.setBlob(2, inputStream); int l = ps2.executeUpdate(); if(l==1) { pw.
			 * println("<div class='tab'><p1 class='menu'>And User Photo updated Successfully !</p1></div>"
			 * ); } else { pw.
			 * println("<div class='tab'><p1 class='menu red'>Unable to upload photo !</p1></div>"
			 * ); }
			 */
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}
	}

}
