package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.TrainException;
import com.shashi.beans.UserBean;
import com.shashi.constant.UserRole;
import com.shashi.service.UserService;
import com.shashi.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/userreg")
public class UserRegServlet extends HttpServlet {

	private UserService userService = new UserServiceImpl(UserRole.CUSTOMER);

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			UserBean user = new UserBean();
			user.setMailId(req.getParameter("mailid"));
			user.setPWord(req.getParameter("pword"));
			user.setFName(req.getParameter("firstname"));
			user.setLName(req.getParameter("lastname"));
			user.setAddr(req.getParameter("address"));
			user.setPhNo(Long.parseLong(req.getParameter("phoneno")));

			String message = userService.registerUser(user);
			if ("SUCCESS".equalsIgnoreCase(message)) {
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>User Registered Successfully !</p1></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("UserRegister.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>" + message + "</p1></div>");

			}

		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}
	}

}
