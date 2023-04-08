package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.TrainException;
import com.shashi.constant.ResponseCode;

public class ErrorHandlerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

		// Fetch the exceptions
		Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
		String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
		String errorMessage = ResponseCode.INTERNAL_SERVER_ERROR.getMessage();
		String errorCode = ResponseCode.INTERNAL_SERVER_ERROR.name();

		if (statusCode == null)
			statusCode = 0;
		Optional<ResponseCode> errorCodes = ResponseCode.getMessageByStatusCode(statusCode);
		if (errorCodes.isPresent()) {
			errorMessage = errorCodes.get().getMessage();
			errorCode = errorCodes.get().name();
		}

		if (throwable != null && throwable instanceof TrainException) {
			TrainException trainException = (TrainException) throwable;
			if (trainException != null) {
				errorMessage = trainException.getMessage();
				statusCode = trainException.getStatusCode();
				errorCode = trainException.getErrorCode();
				trainException.printStackTrace();
			}
		} else if (throwable != null) {
			errorMessage = throwable.getMessage();
			errorCode = throwable.getLocalizedMessage();
		}

		System.out.println("======ERROR TRIGGERED========");
		System.out.println("Servlet Name: " + servletName);
		System.out.println("Request URI: " + requestUri);
		System.out.println("Status Code: " + statusCode);
		System.out.println("Error Code: " + errorCode);
		System.out.println("Error Message: " + errorMessage);
		System.out.println("=============================");

		if (statusCode == 401) {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>" + errorMessage + "</p1></div>");

		} else {
			RequestDispatcher rd = req.getRequestDispatcher("error.html");
			rd.include(req, res);
			pw.println("<div style='margin-top:20%; text-align:center;'>\r\n"
					+ "	<p class=\"menu\" style='color:red'>" + errorCode + "</p><br>\r\n" + "	<p class=\"menu\">"
					+ errorMessage + "</p>\r\n" + "  </div>");

		}

	}

}
