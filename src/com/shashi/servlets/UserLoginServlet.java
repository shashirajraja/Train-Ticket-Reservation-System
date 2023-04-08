package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.constant.ResponseCode;
import com.shashi.constant.UserRole;
import com.shashi.utility.TrainUtil;

@WebServlet("/userlogin")
public class UserLoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String uName = req.getParameter("uname");
		String pWord = req.getParameter("pword");

		String responseMsg = TrainUtil.login(req, res, UserRole.CUSTOMER, uName, pWord);
		if (ResponseCode.SUCCESS.toString().equalsIgnoreCase(responseMsg)) {
			RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
			rd.include(req, res);
			pw.println("<div class='main'><p1 class='menu'>Hello " + uName
					+ " ! Welcome to our new NITRTC Website</p1></div>");
			pw.println("<div class='tab'>Hello " + uName
					+ " ! Good to See You here.<br/> Here you can Check up the train "
					+ "details and train schedule,fare Enquiry and many more information.<br/>Just go to the Side Menu Links and "
					+ "Explore the Advantages.<br/><br/>Thanks For Being Connected with us!" + "</div>");

		} else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);

			pw.println("<div class='tab'><p1 class='menu'>" + responseMsg + "</p1></div>");

		}

	}

}
