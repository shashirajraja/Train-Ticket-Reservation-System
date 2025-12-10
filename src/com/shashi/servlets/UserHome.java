package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.shashi.constant.UserRole;
import com.shashi.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/userhome")
public class UserHome extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);
		RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
		rd.include(req, res);
		pw.println("<div class='tab'>" + "		<p1 class='menu'>" + "	Hello " + TrainUtil.getCurrentUserName(req)
				+ " ! Welcome to our new NITRTC Website" + "		</p1>" + "	</div>");
		pw.println("<div class='main'><p1 class='menu'>User Home</p1></div>");
		pw.println("<div class='tab'>Hello " + TrainUtil.getCurrentUserName(req)
				+ " ! Good to See You here.<br/> Here you can Check up the train "
				+ "details, train schedule, fare Enquiry and many more information.<br/>Just go to the Side Menu Links and "
				+ "Explore the Advantages.<br/><br/>Thanks For Being Connected with us!" + "</div>");

	}

}
