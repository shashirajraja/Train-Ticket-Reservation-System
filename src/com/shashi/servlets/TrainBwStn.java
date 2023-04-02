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
public class TrainBwStn extends HttpServlet {
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
					PreparedStatement ps = con.prepareStatement("Select * from train where from_stn LIKE UPPER(?) and to_stn LIKE UPPER(?)");
					ps.setString(1, "%" + req.getParameter("fromstation") + "%");
					ps.setString(2, "%" + req.getParameter("tostation") + "%");
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
						rd.include(req, res);
						pw.println("<div class='tab'>" + "		<p1 class='menu'>" + "	Hello " + uName
								+ " ! Welcome to our new NITRTC Website" + "		</p1>" + "	</div>");
						pw.println("<div class='main'><p1 class='menu'>Trains BetWeen Station "
								+ req.getParameter("fromstation") + " and " + req.getParameter("tostation")
								+ "</p1></div>");
						pw.println("<div class='tab'><table><tr><th>Train Name</th><th>Train No</th>"
								+ "<th>From Stn</th><th>To Stn</th><th>Seats</th><th>Fare (INR)</th></tr>");
						do {
							pw.println("" + "<tr><td>" + rs.getString("tr_name") + "</td>" + "<td>"
									+ rs.getLong("tr_no") + "</td>" + "<td>" + rs.getString("from_Stn") + "</td>"
									+ "<td>" + rs.getString("to_Stn") + "</td>" + "<td>" + rs.getLong("seats")
									+ "</td>" + "<td>" + rs.getLong("fare") + " RS</td></tr>");
						} while (rs.next());
						pw.println("</table></div>");
					} else {
						RequestDispatcher rd = req.getRequestDispatcher("TrainBwStn.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>There are no trains Between "
								+ req.getParameter("fromstation") + " and " + req.getParameter("tostation")
								+ "</p1></div>");
					}
				} catch (Exception e) {
					throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
				}

			}
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}
}
