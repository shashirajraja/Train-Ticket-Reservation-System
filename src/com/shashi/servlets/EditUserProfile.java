package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.UserBean;
import com.shashi.constant.UserRole;
import com.shashi.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/edituserprofile")
public class EditUserProfile extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

		UserBean ub = TrainUtil.getCurrentCustomer(req);
		RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
		rd.include(req, res);
		pw.println("<div class='tab'>" + "		<p1 class='menu'>" + "	Hello " + TrainUtil.getCurrentUserName(req)
				+ " ! Welcome to our new NITRTC Website" + "		</p1>" + "	</div>");
		pw.println("<div class='main'><p1 class='menu'><a href='viewuserprofile'>View Profile</a></p1>&nbsp;"
				+ "<p1 class='menu'><a href='edituserprofile'>Edit Profile</a></p1>&nbsp;"
				+ "<p1 class='menu'><a href='changeuserpassword'>Change Password</a></p1>" + "</div>");
		pw.println("<div class='tab'>Profile Update</div>");
		pw.println("<div class='tab'>" + "<table><form action='updateuserprofile' method='post'>"
				+ "<tr><td>User Name :</td><td><input type='text' name='username' value='" + ub.getMailId()
				+ "' disabled></td></tr>" + "<tr><td>First Name :</td><td><input type='text' name='firstname' value='"
				+ ub.getFName() + "'></td></tr>"
				+ "<tr><td>Last Name :</td><td><input type='text' name='lastname' value='" + ub.getLName()
				+ "'></td></tr>" + "<tr><td>Address :</td><td><input type='text' name='address' value='" + ub.getAddr()
				+ "'></td></tr>" + "<tr><td>Phone No:</td><td><input type='text' name='phone' value='" + ub.getPhNo()
				+ "'></td></tr>" + "<tr><td><input type='hidden' name='mail' value='" + ub.getMailId() + "'></td></tr>"
				+ "<tr><td></td><td><input type='submit' name='submit' value='Update Profile'></td></tr>"
				+ "</form></table>" + "</div>");

	}

}
