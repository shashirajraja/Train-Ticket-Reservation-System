package com.shashi.servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.utility.DBConnection;
@SuppressWarnings("serial")
public class BookTrains extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		Cookie ck[] =req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			//String pWord = ck[1].getValue();
			RequestDispatcher rd = req.getRequestDispatcher("BookTrains.html");
			rd.include(req, res);
			if(!uName.equals("")||uName != null )
			{
				long seat =Long.parseLong(req.getParameter("seats"));
				long trainNo =Long.parseLong(req.getParameter("trainnumber"));
				try {
					Connection con = DBConnection.getCon();
					PreparedStatement ps = con.prepareStatement("Select * from train6 where tr_no = ?");
					ps.setLong(1, trainNo);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
					{
						long avail =rs.getLong("available");
						if(seat>avail) {
							pw.println("<div class='tab'><p1 class='menu red'>Only "+avail+" Seats are Available in this Train!</p1></div>");
							
						}
						else if(seat<=avail) {
							avail = avail - seat;
							PreparedStatement ps1 = con.prepareStatement("update train6 set available=? where tr_no=?");
							ps1.setLong(1, avail);
							ps1.setLong(2, trainNo);
							int k= ps1.executeUpdate();
							if(k==1)
							{
								pw.println("<div class='tab'><p1 class='menu green'>"+seat+" Seats Booked Successfully!</p1></div>");
							
								
							}
							else {
								pw.println("<div class='tab'><p1 class='menu red'>Transaction Declined. Try Again !</p1></div>");
								
							}
						}
					}
					else {
						pw.println("<div class='tab'><p1 class='menu'>Invalid Train Number !</p1></div>");
						
					}
					
				}
				catch(Exception e) {}
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			pw.println("<div class='tab'><p1 class='menu'>Please Login First !</p1></div>");
			rd.include(req, res);
		}
	}

}
