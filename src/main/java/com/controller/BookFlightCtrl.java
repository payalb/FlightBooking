package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookingDao;
import com.dao.BookingDaoImpl;
import com.dao.FlightSeatDao;
import com.dao.FlightSeatDaoImpl;
import com.dto.Booking;
import com.dto.BookingStatus;
import com.dto.FlightClass;
import com.dto.FlightSeat;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.FormatUtil;

@WebServlet("/booking")
public class BookFlightCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BookingDao bookingDao = new BookingDaoImpl();
	FlightSeatDao flightSeatDao = new FlightSeatDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("passengerId") == null) {
			response.sendRedirect(request.getContextPath() + "/");
		}

		Integer passengerId = FormatUtil.objToInteger(session.getAttribute("passengerId"));
		if (passengerId == null) {
			response.sendRedirect(request.getContextPath() + "/");
		}

		Integer flightId = FormatUtil.strToInteger(request.getParameter("flightId"));
		Integer business = FormatUtil.strToInteger(request.getParameter("business"), 0);
		Integer busiBaggage = FormatUtil.strToInteger(request.getParameter("busiBaggage"));
		Integer firstClass = FormatUtil.strToInteger(request.getParameter("firstClass"), 0);
		Integer firstBaggage = FormatUtil.strToInteger(request.getParameter("firstBaggage"));
		Integer economy = FormatUtil.strToInteger(request.getParameter("economy"), 0);
		Integer econoBaggage = FormatUtil.strToInteger(request.getParameter("econoBaggage"));

		Integer businessLeft = FormatUtil.strToInteger(request.getParameter("businessLeft"));
		Integer firstLeft = FormatUtil.strToInteger(request.getParameter("firstLeft"));
		Integer economyLeft = FormatUtil.strToInteger(request.getParameter("economyLeft"));
		Integer oldVersion = FormatUtil.strToInteger(request.getParameter("oldVersion"));

		try {
			if (flightId == null || busiBaggage == null || businessLeft == null 
					|| firstLeft == null || economyLeft == null || oldVersion == null) {
				throw new InputException("Invalid input information during booking.");
			} else {
				if (business == 0 && firstClass == 0 && economy == 0) {
					throw new InputException("Invalid input, please input number of ticket to book.");
				}
			}

			for (int i = 0; i < business; i++) {
				Booking booking = new Booking(passengerId, flightId, Integer.parseInt(1 + "" + businessLeft--),
						busiBaggage, FlightClass.BUSINESSCLASS, BookingStatus.RESERVED);
				int bookingId = bookingDao.BookingFlight(booking);
				if (bookingId <= 0) {
					throw new DatabaseException("Cannot insert booking information.");
				}
				FlightSeat seat = new FlightSeat(flightId, businessLeft, firstLeft, economyLeft, oldVersion++);
				int row = flightSeatDao.updateFlightSeat(seat);
				if (row <= 0) {
					throw new DatabaseException("Cannot update flight seat information.");
				}
			}

			for (int i = 0; i < firstClass; i++) {
				Booking booking = new Booking(passengerId, flightId, Integer.parseInt(2 + "" + firstLeft--),
						firstBaggage, FlightClass.FIRSTCLASS, BookingStatus.RESERVED);
				int bookingId = bookingDao.BookingFlight(booking);
				if (bookingId <= 0) {
					throw new DatabaseException("Cannot insert booking information.");
				}
				FlightSeat seat = new FlightSeat(flightId, businessLeft, firstLeft, economyLeft, oldVersion++);
				int row = flightSeatDao.updateFlightSeat(seat);
				if (row <= 0) {
					throw new DatabaseException("Cannot update flight seat information.");
				}
			}

			for (int i = 0; i < economy; i++) {
				Booking booking = new Booking(passengerId, flightId, Integer.parseInt(3 + "" + economyLeft--),
						econoBaggage, FlightClass.ECONOMYCLASS, BookingStatus.RESERVED);
				int bookingId = bookingDao.BookingFlight(booking);
				if (bookingId <= 0) {
					throw new DatabaseException("Cannot insert booking information.");
				}
				FlightSeat seat = new FlightSeat(flightId, businessLeft, firstLeft, economyLeft, oldVersion++);
				int row = flightSeatDao.updateFlightSeat(seat);
				if (row <= 0) {
					throw new DatabaseException("Cannot update flight seat information.");
				}
			}
			response.sendRedirect(request.getContextPath() + "/passenger-history");
		} catch (InputException | DatabaseException | FileException e) {
			response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
		}
	}

}
