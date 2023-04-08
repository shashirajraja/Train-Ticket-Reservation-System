package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.TrainException;
import com.shashi.utility.DBUtil;

public class AdminCancleTrain extends HttpServlet {

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
			// String pWord = ck[1].getValue();
			if (!uName.equals("") || uName != null) {
				try {
					Connection con = DBUtil.getCon();
					PreparedStatement ps = con.prepareStatement("delete from train where tr_no=?");
					ps.setLong(1, Long.parseLong(req.getParameter("trainno")));
					int k = ps.executeUpdate();
					if (k == 1) {
						RequestDispatcher rd = req.getRequestDispatcher("CancleTrain.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu'>Train number " + req.getParameter("trainno")
								+ " has been Cancelled Successfully.</p1></div>");
					} else {
						RequestDispatcher rd = req.getRequestDispatcher("CancleTrain.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Train No." + req.getParameter("trainno")
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
