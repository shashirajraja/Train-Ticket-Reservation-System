package com.shashi.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.shashi.utility.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class FareEnq extends HttpServlet{
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
					PreparedStatement ps = con.prepareStatement("Select * from train6 where from_stn=? and to_stn=?");
					ps.setString(1,req.getParameter("fromstation"));
					ps.setString(2, req.getParameter("tostation"));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) 
					{
						RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
						rd.include(req, res);
						pw.println("<div class='tab'>" + 
								"		<p1 class='menu'>" + 
								"	Hello "+uName+" ! Welcome to our new NITRTC Website" + 
								"		</p1>" + 
								"	</div>");
						pw.println("<div class='main'><p1 class='menu'>Fare for Trains BetWeen Station "+req.getParameter("fromstation")+" and "+req.getParameter("tostation")+" is <p2 class=\"red\">RS "+rs.getLong("fare")+"</p2></p1></div>");
						pw.println("<div class='tab'><table><tr><th>Train Name</th><th>Train No</th>"
								+ "<th>From Stn</th><th>To Stn</th><th>Seats</th><th>Fare (INR)</th></tr>");
						do {
								pw.println(""
								+ "<tr><td>"+rs.getString("tr_name")+"</td>"
								+ "<td>"+rs.getLong("tr_no")+"</td>"
								+ "<td>"+rs.getString("from_Stn")+"</td>"
								+ "<td>"+rs.getString("to_Stn")+"</td>"
								+ "<td>"+rs.getLong("available")+"</td>"
								+ "<td>"+rs.getLong("fare")+" RS</td></tr>");
					}while(rs.next());
						pw.println("</table></div>");
						}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("TrainBwStn.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>There are no trains Between"+req.getParameter("fromstation")+" and "+req.getParameter("tostation")+"</p1></div>");					}
				}
				catch(Exception e) {}
				
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}
}
