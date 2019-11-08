package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.beans.UserBean;
@SuppressWarnings("serial")
public class ChangeUserPassword extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
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
						pw.println("<div class='tab'>Password Change</div>");
						pw.println("<div class='tab'>"
								+ "<table><form action='changeuserpwd' method='post'>"
								+ "<tr><td>User Name :</td><td><input type='text' name='username' ></td></tr>"
								+ "<tr><td>Old Password :</td><td><input type='password' name='oldpassword'></td></tr>"
								+ "<tr><td>New Password :</td><td><input type='password' name='newpassword'></td></tr>"
								+ "<tr><td></td><td><input type='submit' name='submit' value='Change Password'></td></tr>"
								+ "</form></table>"
								+ "</div>");
				
				
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}

}
