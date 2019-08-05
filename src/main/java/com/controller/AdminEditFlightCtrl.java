package com.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

@WebServlet("/admineditflight")
public class AdminEditFlightCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FlightDao flightDao = new FlightDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer flightId = FormatUtil.strToInteger(request.getParameter("flightId"));
		String deptCity = request.getParameter("deptCity");
		String arrCity = request.getParameter("arrCity");
		LocalDate deptDate = FormatUtil.strToLocalDate(request.getParameter("deptDate"));
		LocalTime deptTime = FormatUtil.strToLocalTime(request.getParameter("deptTime"));
		LocalDate arrDate = FormatUtil.strToLocalDate(request.getParameter("arrDate"));
		LocalTime arrTime = FormatUtil.strToLocalTime(request.getParameter("arrTime"));
		try {
			if (flightId != null && deptCity != null && arrCity != null && deptDate != null && deptTime != null
					&& arrDate != null && arrTime != null) {
				if (!LocalDateTime.of(deptDate, deptTime).isBefore(LocalDateTime.of(arrDate, arrTime))) {
					throw new InputException("Invalid date and time information during adding flight.");
				}
				Flight flight = new Flight(flightId, LocalDateTime.of(deptDate, deptTime),
						LocalDateTime.of(arrDate, arrTime), deptCity, arrCity);
				int row = flightDao.updateFlight(flight);
				if (row <= 0) {
					throw new DatabaseException("Cannot update the flight information.");
				} else {
					response.sendRedirect(request.getContextPath() + "/admin_index");
				}
			} else {
				throw new InputException("Invalid input information during flight editing.");
			}
		} catch (DatabaseException | InputException | FileException e) {
			response.sendRedirect(request.getContextPath() 
					+ "/admin_error?exception=" + e.getMessage());
		}
	}

}
