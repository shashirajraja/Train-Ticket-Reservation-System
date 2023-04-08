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
public class UpdateTrainSchedule extends HttpServlet {
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
					PreparedStatement ps = con.prepareStatement(
							"update train set tr_no=?,tr_name=?,from_stn=?,to_stn=?,seats=?,fare=? where tr_no=?");
					ps.setLong(1, Long.parseLong(req.getParameter("trainno")));
					ps.setString(2, req.getParameter("trainname"));
					ps.setString(3, req.getParameter("fromstation"));
					ps.setString(4, req.getParameter("tostation"));
					ps.setLong(5, Long.parseLong(req.getParameter("available")));
					ps.setLong(6, Long.parseLong(req.getParameter("fare")));
					ps.setLong(7, Long.parseLong(req.getParameter("trainno")));
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateTrain.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Train Updated Successfully!</p1></div>");
					} else {
						RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateTrain.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Error in filling the train Detail</p1></div>");
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
