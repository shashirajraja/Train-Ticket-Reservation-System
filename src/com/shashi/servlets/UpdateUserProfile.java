package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;

import com.shashi.beans.UserBean;
import com.shashi.utility.DBConnection;
@SuppressWarnings("serial")
public class UpdateUserProfile extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			String pWord = ck[1].getValue();
			if(!uName.equals("")||uName!=null) {
				ServletContext sct = req.getServletContext();
				UserBean ub = (UserBean)sct.getAttribute("ubean"); 
				String u_Name = req.getParameter("username");
				String fName = req.getParameter("firstname");
				String lName = req.getParameter("lastname");
				String addR = req.getParameter("address");
				long phNo = Long.parseLong(req.getParameter("phone"));
				String mailId = req.getParameter("mail");
				try {
				Connection con = DBConnection.getCon();//Get photo from userphoto sql table created
				PreparedStatement ps = con.prepareStatement("update register set uname=?,fname=?,lname=?,addr=?,phno=?,mailid=? where uname=? and pword=?");
				ps.setString(1, u_Name);
				ps.setString(2, fName);
				ps.setString(3, lName);
				ps.setString(4, addR);
				ps.setLong(5, phNo);
				ps.setString(6,mailId);
				ps.setString(7, uName);
				ps.setString(8, pWord);
				int k= ps.executeUpdate();
				if(k==1) {
					ub.setUName(u_Name);
					ub.setLName(lName);
					ub.setFName(fName);
					ub.setMailId(mailId);
					ub.setAddr(addR);
					ub.setPhNo(phNo);
					RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
					rd.include(req, res);
					pw.println("<div class='tab'>" + 
							"		<p1 class='menu'>" + 
							"	Hello "+uName+" ! Welcome to our new NITRTC Website" + 
							"		</p1>" + 
							"	</div>");
					pw.println("<div class='main'><p1 class='menu'><a href='viewuserprofile'>view Profile</a></p1>"
							+ "<p1 class='menu'><a href='edituserprofile'>Edit Profile</a></p1>"
							+ "<p1 class='menu'><a href='changeuserpassword'>Change Password</a></p1>"
							+ "</div>");
					pw.println("<div class='tab'>Your Profile has Been Successfully Updated</div>");
				}
				else {
					RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
					rd.include(req, res);
					pw.println("<div class='main'><p1 class='menu'><a href='viewuserprofile'>view Profile</a></p1>"
							+ "<p1 class='menu'><a href='edituserprofile'>Edit Profile</a></p1>"
							+ "<p1 class='menu'><a href='changeuserpassword'>Change Password</a></p1>"
							+ "</div>");
					pw.println("<div class='tab'>Please Enter the valid Information</div>");
				}
				}
				catch(Exception e) {}
				}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}

}
