package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.TrainException;
import com.shashi.utility.DBUtil;

@SuppressWarnings("serial")
public class AdminSearchTrain extends HttpServlet {

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if (ck != null) {
			String uName = ck[0].getValue();
//			String pWord = ck[1].getValue();
			if (!uName.equals("") || uName != null) {
				try {
					Connection con = DBUtil.getCon();
					PreparedStatement ps = con.prepareStatement("Select * from train where tr_no=?");
					ps.setLong(1, Long.parseLong(req.getParameter("trainnumber")));
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AdminSearchTrain.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu'>Searched Train Detail</p1></div>");
						pw.println("<div class='tab'>" + "<table>" + "<tr><td class='blue'>Train Name :</td><td>"
								+ rs.getString("tr_name") + "</td></tr>"
								+ "<tr><td class='blue'>Train Number :</td><td>" + rs.getLong("tr_no") + "</td></tr>"
								+ "<tr><td class='blue'>From Station :</td><td>" + rs.getString("from_Stn")
								+ "</td></tr>" + "<tr><td class='blue'>To Station :</td><td>" + rs.getString("to_Stn")
								+ "</td></tr>" + "<tr><td class='blue'>Available Seats:</td><td>"
								+ rs.getLong("seats") + "</td></tr>" + "<tr><td class='blue'>Fare (INR) :</td><td>"
								+ rs.getLong("fare") + " RS</td></tr>" + "</table>" + "</div>");
					} else {
						RequestDispatcher rd = req.getRequestDispatcher("AdminSearchTrain.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Train No." + req.getParameter("trainnumber")
								+ " is Not Available !</p1></div>");
					}
				} catch (Exception e) {
					throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
				}

			}
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}

}
