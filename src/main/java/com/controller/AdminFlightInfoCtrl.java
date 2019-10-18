package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FlightDao;
import com.dao.FlightDaoImpl;
import com.dto.Flight;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.FormatUtil;

@WebServlet("/adminflightinfo")
public class AdminFlightInfoCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FlightDao flightDao = new FlightDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer flightId = FormatUtil.strToInteger(request.getParameter("flightId"));
		try {
			if (flightId == null) {
				throw new InputException("Invalid flight information.");
			} else {
				Flight flight = flightDao.getFlightById(flightId);
				if (flight == null) {
					throw new DatabaseException("Cannot get the flight information.");
				} else {
					request.setAttribute("flight", flight);
					request.getRequestDispatcher("/admin_flight.jsp").forward(request, response);
				}
			}
		} catch (InputException | FileException | DatabaseException e) {
			response.sendRedirect(request.getContextPath()+"/admin_error?exception="+ e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
