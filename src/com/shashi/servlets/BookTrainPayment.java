package com.shashi.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.constant.UserRole;
import com.shashi.utility.TrainUtil;

@WebServlet("/payment")
public class BookTrainPayment extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

		int seat = Integer.parseInt(req.getParameter("seats"));
		String trainNo = req.getParameter("trainnumber");
		String journeyDate = req.getParameter("journeydate");
		String seatClass = req.getParameter("class");
		ServletContext sct = req.getServletContext();
		sct.setAttribute("seats", seat);
		sct.setAttribute("trainnumber", trainNo);
		sct.setAttribute("journeydate", journeyDate);
		sct.setAttribute("class", seatClass);
		RequestDispatcher rd = req.getRequestDispatcher("Payment.html");
		rd.forward(req, res);

	}
}
