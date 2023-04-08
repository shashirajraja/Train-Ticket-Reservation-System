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

public class AdminAddTrain extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
			//String pWord = ck[1].getValue();
			if (!uName.equals("") || uName != null) {
				try {
					Connection con = DBUtil.getCon();
					PreparedStatement ps = con.prepareStatement("insert into train values(?,?,?,?,?,?)");
					ps.setLong(1, Long.parseLong(req.getParameter("trainno")));
					ps.setString(2, req.getParameter("trainname").toUpperCase());
					ps.setString(3, req.getParameter("fromstation").toUpperCase());
					ps.setString(4, req.getParameter("tostation").toUpperCase());
					ps.setLong(5, Long.parseLong(req.getParameter("available")));
					ps.setDouble(6, Double.parseDouble(req.getParameter("fare")));
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Train Added Successfully!</p1></div>");
					} else {
						RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
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
