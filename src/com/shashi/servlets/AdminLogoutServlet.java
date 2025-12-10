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
@WebServlet("/adminlogout")
public class AdminLogoutServlet extends HttpServlet {

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		if (TrainUtil.isLoggedIn(req, UserRole.ADMIN)) {
			TrainUtil.logout(res);
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>You have been successfully logged out !</p1></div>");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>You are Already Logged Out !</p1></div>");
		}
	}
}
