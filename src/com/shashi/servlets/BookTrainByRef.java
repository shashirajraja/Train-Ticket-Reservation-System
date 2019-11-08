package com.shashi.servlets;
import java.io.*;
import java.time.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class BookTrainByRef extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		Cookie ck[] =req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			//String pWord = ck[1].getValue();
			
			if(!uName.equals("")||uName != null )
			{
				long trainNo =Long.parseLong(req.getParameter("trainNo"));
				int seat = 1;
				String fromStn = req.getParameter("fromStn");
				String toStn = req.getParameter("toStn");
				RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.html");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu'>Your Ticket Booking Information</p1></div>");
				pw.println("<div class='tab'><form action='booktrains' method='post'>"
						+ "<table>"
						+ "<tr><td>USER ID:</td><td>"+uName+"</td>"
						+ "<td>Train NO:</td><td>"+trainNo+"</td></tr>"
						+ "<tr><td>From Station:</td><td>"+fromStn+"</td>"
						+ "<td>To Station :</td><td>"+toStn+"</tr>"
						+ "<tr><td>Journey Date:</td><td>"
						+ "<input type='hidden' name='trainnumber' value='"+trainNo+"'>"
						+ "<input type='date' name='journeydate' value='"+LocalDate.now()+"'</td>"
						+ "<td>No of Seats:</td><td><input type='text' name='seats' value='"+seat+"'</td></tr>"
						+ "</table></div>"
						+ "<div class='tab'><p1 class='menu'><input type='submit'value='Pay And Book'></p1></div>"
						+ "</form>");

			}
		}
		else {
			RequestDispatcher rd =req.getRequestDispatcher("UserLogin.html");
			pw.println("<div class='tab'><p1 class='menu'>Please Login First!</p1></div>");
		}
	}
}