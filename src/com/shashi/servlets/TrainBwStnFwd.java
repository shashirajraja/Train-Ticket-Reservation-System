package com.shashi.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.shashi.constant.UserRole;
import com.shashi.utility.TrainUtil;

@SuppressWarnings("serial")
@WebServlet("/trainbwstnfwd")
public class TrainBwStnFwd extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");

		TrainUtil.validateUserAuthorization(req, UserRole.CUSTOMER);

		RequestDispatcher rd = req.getRequestDispatcher("TrainBwStn.html");
		rd.forward(req, res);

	}

}
