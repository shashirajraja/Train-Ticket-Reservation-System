package com.shashi.servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.beans.UserBean;
import com.shashi.utility.DBConnection;
@SuppressWarnings("serial")
public class AdminLogin extends HttpServlet{

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String uName = req.getParameter("uname");
		String pWord = req.getParameter("pword");
		try {
			Connection con = DBConnection.getCon();
			
			PreparedStatement ps = con.prepareStatement("select * from admin6 where uname=? and pword=?");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				UserBean ub = new UserBean();
				ub.setUName(rs.getString("uname"));
				ub.setFName(rs.getString("name"));
				//ub.setLName(rs.getString("lname"));
				ub.setPWord(rs.getString("pword"));
				//ub.setAddr(rs.getString("addr"));
				ub.setMailId(rs.getString("mail_id"));
				ub.setPhNo(Long.parseLong(rs.getString("phone_no")));
								
				Cookie ck1 = new Cookie("ckname",uName);
				res.addCookie(ck1);
				Cookie ck2 = new Cookie("ckpwd",pWord);
				res.addCookie(ck2);
				
					RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");				
					rd.include(req, res);
					pw.println("<div class='main'><p1 class='menu'>Hello, "+uName+" ! Welcome </p1></div>");
					pw.println("<div class='tab'>Hi ! Here You can Manage Train Information as per Your Requirement</div>");
					
				}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Invalid Username Or Password !</p1></div>");

			}
		}
		catch(Exception e) {}
	}
}
