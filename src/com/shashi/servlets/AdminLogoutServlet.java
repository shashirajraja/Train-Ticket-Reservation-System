package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class AdminLogoutServlet extends HttpServlet{

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			Cookie ck1 = new Cookie("ckname","");
			ck1.setMaxAge(0);
			res.addCookie(ck1);
			Cookie ck2 = new Cookie("ckpwd","");
			ck2.setMaxAge(0);
			res.addCookie(ck2);
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>You have been successfully logged out !</p1></div>");
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>You are Already Logged Out !</p1></div>");
		}
	}
}
