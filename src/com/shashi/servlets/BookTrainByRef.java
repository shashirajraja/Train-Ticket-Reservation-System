package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.constant.UserRole;
import com.shashi.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/booktrainbyref")
public class BookTrainByRef extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

		String emailId = TrainUtil.getCurrentUserEmail(req);
		long trainNo = Long.parseLong(req.getParameter("trainNo"));
		int seat = 1;
		String fromStn = req.getParameter("fromStn");
		String toStn = req.getParameter("toStn");
		RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.html");
		rd.include(req, res);
		pw.println("<div class='main'><p1 class='menu'>Your Ticket Booking Information</p1></div>");

		pw.println("<div class='tab'>" + "<form action='payment' method='post'>" + "<table>"
				+ "<tr><td>USER ID:</td><td>" + emailId + "</td>" + "<td>Train NO:</td><td>" + trainNo + "</td></tr>"
				+ "<tr><td>From Station:</td><td>" + fromStn + "</td>" + "<td>To Station :</td><td>" + toStn + "</tr>"
				+ "<tr><td>Journey Date:</td><td>" + "<input type='hidden' name='trainnumber' value='" + trainNo + "'>"
				+ "<input type='date' name='journeydate' value='" + LocalDate.now() + "'></td>"
				+ "<td>No of Seats:</td><td><input type='number' name='seats' value='" + seat + "'></td></tr>"
				+ "<tr><td>Select Class</td><td>" + "<select name='class'  required>"
				+ "<option value='Sleeper(SL)'>Sleeper(SL)</option><option value='Second Sitting(2S)'>Second Sitting(2S)</option><option value='AC First Class(1A)'>AC First Class(1A)</option><option value='AC 2 Tier(2A)'>AC 2 Tier(2A)</option>"
				+ "</select>" + "</td>" + "<td>Berth Preference</td><td>"
				+ "<select name='berth'><option value='NO'>No Preference</option><option value='LB'>Lower Berth(LB)</option><option value='UB'>Lower Berth(UB)</option><option value='C'>Cabin</option></select>"
				+ "</td></tr>" + "</table></div>"
				+ "<div class='tab'><p1 class='menu'><input type='submit'value='Pay And Book'></p1></div>" + "</form>");

	}

}