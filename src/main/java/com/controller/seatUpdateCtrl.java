package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.dao.FlightSeatDao;
import com.dao.FlightSeatDaoImpl;
import com.dao.SeatDao;
import com.dao.SeatDaoImpl;
import com.dto.Booking;
import com.dto.Flight;
import com.dto.FlightClass;
import com.dto.Seat;
import com.dto.SeatStatus;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.FormatUtil;

@WebServlet("/seatUpdate")
public class seatUpdateCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SeatDao seatDao = new SeatDaoImpl();
	BookingDao bookingDao = new BookingDaoImpl();
	FlightDao flightDao = new FlightDaoImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		FlightClass bookedClass = (FlightClass) session.getAttribute("class");
		if (session == null || session.getAttribute("passengerId") == null || session.getAttribute("book") == null )
			response.sendRedirect(request.getContextPath() + "/");
		Integer passengerId = FormatUtil.objToInteger(session.getAttribute("passengerId"));
		if (passengerId == null) {
			response.sendRedirect(request.getContextPath() + "/");
		}
		FlightClass pClass = null;
		if( session.getAttribute("pClass") != null )
			pClass = (FlightClass) session.getAttribute("pClass");
		Booking book = (Booking) session.getAttribute("book");
		
		String seatStr = request.getParameter("seats");
		String[] seatArr = seatStr.split("_");
		try {
			if( seatArr.length != 1 )
				throw new InputException("Tickets class and counts mismatch");
			String targetSeat = seatArr[0];
			if( targetSeat.charAt(0) == 'F' && bookedClass != FlightClass.FIRSTCLASS ) {
				throw new InputException("Tickets class and counts mismatch");
			} else if( targetSeat.charAt(0) == 'B' && bookedClass != FlightClass.BUSINESSCLASS ) {
				throw new InputException("Tickets class and counts mismatch");
			} else if( targetSeat.charAt(0) == 'E' && bookedClass != FlightClass.ECONOMYCLASS ) {
				throw new InputException("Tickets class and counts mismatch");
			}
			String[] seatInfo = targetSeat.split("-");
			String seatId = seatInfo[2];
			book.setSeatNumber(seatId);
			book.setBaggage(1);
			book.setFlightClass(bookedClass);
			Seat seat = seatDao.getSeatById(seatId, book.getFlightId());
			if ( seat.getSeatStatus() == SeatStatus.UNAVAILABLE) {
				throw new DatabaseException("Cannot update seat information.");
			}
			session.setAttribute("book", book);
			float[] prices = flightDao.getPrice(book.getFlightId());
			int current = -1;
			if( bookedClass == FlightClass.FIRSTCLASS )
				current = 0;
			else if( bookedClass == FlightClass.BUSINESSCLASS )
				current = 1;
			else if( bookedClass == FlightClass.ECONOMYCLASS )
				current = 2;
			int before = -1;
			if( pClass != null ) {
				if( pClass == FlightClass.FIRSTCLASS )
					before = 0;
				else if( pClass == FlightClass.BUSINESSCLASS )
					before = 1;
				else if( pClass == FlightClass.ECONOMYCLASS )
					before = 2;
				session.setAttribute( "money", prices[current]-prices[before] );
			} else {
				session.setAttribute( "money", prices[current] );
			}
			request.getRequestDispatcher("/re_payment.jsp").forward(request, response);
		} catch (InputException | DatabaseException | FileException e) {
			response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
		}

	}
}
