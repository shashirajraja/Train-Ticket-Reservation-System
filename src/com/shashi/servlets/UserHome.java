package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class UserHome extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			if(!uName.equals("")||uName!=null) {
				RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
				rd.include(req, res);
				pw.println("<div class='tab'>" + 
						"		<p1 class='menu'>" + 
						"	Hello "+uName+" ! Welcome to our new NITRTC Website" + 
						"		</p1>" + 
						"	</div>");
				pw.println("<div class='main'><p1 class='menu'>User Home</p1></div>");
				pw.println("<div class='tab'>Hello "+uName+" ! Good to See You here.<br/> Here you can Check up the train "
						+ "details, train schedule, fare Enquiry and many more information.<br/>Just go to the Side Menu Links and "
						+ "Explore the Advantages.<br/><br/>Thanks For Being Connected with us!"
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
