package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.HistoryBean;
import com.shashi.beans.TrainException;
import com.shashi.constant.UserRole;
import com.shashi.service.BookingService;
import com.shashi.service.impl.BookingServiceImpl;
import com.shashi.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/bookingdetails")
public class TicketBookingHistory extends HttpServlet {

	BookingService bookingService = new BookingServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);
		try {
			String customerId = TrainUtil.getCurrentUserEmail(req);
			List<HistoryBean> details = bookingService.getAllBookingsByCustomerId(customerId);
			if (details != null && !details.isEmpty()) {
				RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.html");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu'>Booked Ticket History</p1></div>");
				pw.println("<div class='tab'><table><tr><th>Transaction ID</th><th>Train Number</th>"
						+ "<th>From Station</th><th>To Station</th><th>Journey Date</th><th>Seat</th><th>Amount Paid</th></tr>");

				for (HistoryBean trans : details) {

					pw.println("" + "<tr> " + "" + "<td>" + trans.getTransId() + "</td>" + "<td>" + trans.getTr_no()
							+ "</td>" + "<td>" + trans.getFrom_stn() + "</td>" + "<td>" + trans.getTo_stn() + "</td>"
							+ "<td>" + trans.getDate() + "</td>" + "<td>" + trans.getSeats() + "</td><td>"
							+ trans.getAmount() + "</td>" + "</tr>");
				}
				pw.println("</table></div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.html");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu red'> No any ticket booked, book your first ticket now!!</p1></div>");
			}
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}

	}

}
