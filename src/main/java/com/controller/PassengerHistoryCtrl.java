package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookingDao;
import com.dao.BookingDaoImpl;
import com.dao.FlightDao;
import com.dao.FlightDaoImpl;
import com.dto.Booking;
import com.dto.Flight;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.FormatUtil;

@WebServlet("/passenger-history")
public class PassengerHistoryCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BookingDao bookingDao = new BookingDaoImpl();
	FlightDao flightDao = new FlightDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(request.getContextPath()+"/");
		}
		Integer passengerId = FormatUtil.objToInteger(session.getAttribute("passengerId"));
		if (passengerId != null) {
			try {
				List<Booking> bookingHistory = bookingDao.BookingHistoryByPassengerId(passengerId);
				Map<Booking, Flight> map = new HashMap<>();
				for (Booking booking : bookingHistory) {
					Flight flight = flightDao.getFlightById(booking.getFlightId());
					if (flight == null)
						throw new DatabaseException("Cannot get the flight information");
					map.put(booking, flight);
				}
				request.setAttribute("bookingHistory", map);
				request.getRequestDispatcher("/history.jsp").forward(request, response);
			} catch (FileException | DatabaseException e) {
				response.sendRedirect(request.getContextPath()+"/error?exception="+e.getMessage());
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
