package com.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import com.dao.SeatDao;
import com.dao.SeatDaoImpl;
import com.dto.Booking;
import com.dto.Flight;
import com.dto.Seat;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.FormatUtil;

@WebServlet("/bookingEdit")
public class BookingEditCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BookingDao bookDao;
	FlightDao flightDao;
	SeatDao seatDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		bookDao = new BookingDaoImpl();
		flightDao = new FlightDaoImpl();
		seatDao = new SeatDaoImpl();
		
		HttpSession session = request.getSession();
		Integer bookId = FormatUtil.strToInteger(request.getParameter("bookingId"));
		try {
			Booking book = bookDao.getBookingById(bookId);
			Flight flight = flightDao.getFlightById(book.getFlightId());
			session.setAttribute("book", book);
			session.setAttribute("flight", flight);
			request.getRequestDispatcher("./edit_booking.jsp").forward(request, response);;
		}catch( DatabaseException | FileException e) {
			response.sendRedirect(request.getContextPath()+"error?exception="+e.getMessage());
		}
	}
}
