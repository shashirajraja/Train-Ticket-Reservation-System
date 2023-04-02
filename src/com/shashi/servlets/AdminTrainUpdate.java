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
public class AdminTrainUpdate extends HttpServlet {

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
					long trNo = Long.parseLong(req.getParameter("trainnumber"));
					PreparedStatement ps = con.prepareStatement("select * from train where tr_no=?");
					ps.setLong(1, trNo);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
						rd.include(req, res);
						pw.println("<div class='tab'>Train Schedule Update</div>");
						pw.println("<div class='tab'>" + "<table><form action='updatetrainschedule' method='post'>"
								+ "<tr><td>Train No :</td><td><input type='text' name='trainno' value='"
								+ rs.getLong("tr_no") + "'></td></tr>"
								+ "<tr><td>Train Name :</td><td><input type='text' name='trainname' value='"
								+ rs.getString("tr_name") + "'></td></tr>"
								+ "<tr><td>From Station :</td><td><input type='text' name='fromstation' value='"
								+ rs.getString("from_stn") + "'></td></tr>"
								+ "<tr><td>To Station :</td><td><input type='text' name='tostation' value='"
								+ rs.getString("to_stn") + "'></td></tr>"
								+ "<tr><td>Available seats:</td><td><input type='text' name='available' value='"
								+ rs.getLong("seats") + "'></td></tr>"
								+ "<tr><td>Fare (INR) :</td><td><input type='text' name='fare' value='"
								+ rs.getLong("fare") + "'></td></tr>"
								+ "<tr><td></td><td><input type='submit' name='submit' value='Update Train Schedule'></td></tr>"
								+ "</form></table>" + "</div>");
					} else {
						RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateTrain.html");
						rd.include(req, res);
						pw.println("<div class='tab'>Train Not Available</div>");
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
