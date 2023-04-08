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

import com.shashi.beans.TrainBean;
import com.shashi.beans.TrainException;
import com.shashi.constant.UserRole;
import com.shashi.service.TrainService;
import com.shashi.service.impl.TrainServiceImpl;
import com.shashi.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/adminviewtrainfwd")
public class AdminViewTrainFwd extends HttpServlet {

	private TrainService trainService = new TrainServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		TrainUtil.validateUserAuthorization(req, UserRole.ADMIN);
		try {
			List<TrainBean> trains = trainService.getAllTrains();
			if (trains != null && !trains.isEmpty()) {
				RequestDispatcher rd = req.getRequestDispatcher("ViewTrains.html");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu'>Running Trains</p1></div>");
				pw.println("<div class='tab'><table><tr><th>Train Name</th><th>Train Number</th>"
						+ "<th>From Station</th><th>To Station</th><th>Seats Available</th><th>Fare (INR)</th><th>Action</th></tr>");

				for (TrainBean train : trains) {

					pw.println("" + "<tr> " + "" + "<td><a href='viewadmin?trainNo=" + train.getTr_no() + "&fromStn="
							+ train.getFrom_stn() + "&toStn=" + train.getTo_stn() + "'>" + train.getTr_name()
							+ "</a></td>" + "<td>" + train.getTr_no() + "</td>" + "<td>" + train.getFrom_stn() + "</td>"
							+ "<td>" + train.getTo_stn() + "</td>" + "<td>" + train.getSeats() + "</td>" + "<td>"
							+ train.getFare() + " RS</td>" + "<td><a href='adminupdatetrain?trainnumber="
							+ train.getTr_no() + "'>Update</a></td>" + "</tr>");
				}
				pw.println("</table></div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("ViewTrains.html");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu red'> No Running Trains</p1></div>");
			}
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());

		}

	}

}
