package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.TrainException;
import com.shashi.beans.UserBean;
import com.shashi.utility.DBUtil;

@SuppressWarnings("serial")
public class UpdateUserProfile extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if (ck != null) {
			String uName = ck[0].getValue();
			//String pWord = ck[1].getValue();
			if (!uName.equals("") || uName != null) {
				ServletContext sct = req.getServletContext();
				UserBean ub = (UserBean) sct.getAttribute("ubean");
				String u_Name = req.getParameter("username");
				String fName = req.getParameter("firstname");
				String lName = req.getParameter("lastname");
				String addR = req.getParameter("address");
				long phNo = Long.parseLong(req.getParameter("phone"));
				String mailId = req.getParameter("mail");
				try {
					Connection con = DBUtil.getCon();// Get photo from userphoto sql table created
					PreparedStatement ps = con.prepareStatement(
							"update customer set fname=?,lname=?,addr=?,phno=? where mailid=?");
//					ps.setString(1, u_Name);
					ps.setString(1, fName);
					ps.setString(2, lName);
					ps.setString(3, addR);
					ps.setLong(4, phNo);
					ps.setString(5, mailId);
//					ps.setString(7, uName);
					//ps.setString(6, pWord);
					int k = ps.executeUpdate();
					if (k == 1) {
//						ub.setUName(u_Name);
						ub.setLName(lName);
						ub.setFName(fName);
						ub.setMailId(mailId);
						ub.setAddr(addR);
						ub.setPhNo(phNo);
						RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
						rd.include(req, res);
						pw.println("<div class='tab'>" + "		<p1 class='menu'>" + "	Hello " + uName
								+ " ! Welcome to our new NITRTC Website" + "		</p1>" + "	</div>");
						pw.println("<div class='main'><p1 class='menu'><a href='viewuserprofile'>view Profile</a></p1>"
								+ "<p1 class='menu'><a href='edituserprofile'>Edit Profile</a></p1>"
								+ "<p1 class='menu'><a href='changeuserpassword'>Change Password</a></p1>" + "</div>");
						pw.println("<div class='tab'>Your Profile has Been Successfully Updated</div>");
					} else {
						RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu'><a href='viewuserprofile'>view Profile</a></p1>"
								+ "<p1 class='menu'><a href='edituserprofile'>Edit Profile</a></p1>"
								+ "<p1 class='menu'><a href='changeuserpassword'>Change Password</a></p1>" + "</div>");
						pw.println("<div class='tab'>Please Enter the valid Information</div>");
					}
				} catch (Exception e) {
					throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());

				}
			}
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}

}
