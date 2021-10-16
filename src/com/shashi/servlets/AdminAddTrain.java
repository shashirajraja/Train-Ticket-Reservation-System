package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.utility.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class AdminAddTrain extends HttpServlet{

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			String pWord = ck[1].getValue();
			if(!uName.equals("")||uName!=null) {
				try {
					Connection con = DBConnection.getCon();
					PreparedStatement ps = con.prepareStatement("insert into train6 values(?,?,?,?,?,?)");
					ps.setLong(1, Long.parseLong(req.getParameter("trainno")));
					ps.setString(2,req.getParameter("trainname"));
					ps.setString(3, req.getParameter("fromstation"));
					ps.setString(4, req.getParameter("tostation"));
					ps.setLong(5, Long.parseLong(req.getParameter("available")));
					ps.setLong(6,Long.parseLong(req.getParameter("fare")));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Train Added Successfully!</p1></div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Error in filling the train Detail</p1></div>");
					}
				}
				catch(Exception e) {}				
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}

}
