package com.controller;

import java.io.IOException;
import java.time.LocalDateTime;

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
import com.dao.PaymentDao;
import com.dao.PaymentDaoImpl;
import com.dao.SeatDao;
import com.dao.SeatDaoImpl;
import com.dto.Booking;
import com.dto.BookingStatus;
import com.dto.Flight;
import com.dto.FlightClass;
import com.dto.Payment;
import com.dto.Seat;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.FormatUtil;

@WebServlet("/paymentComplete")
public class PaymentCompCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	BookingDao bookDao = new BookingDaoImpl();
	FlightDao flightDao = new FlightDaoImpl();
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("passengerId") == null )
			response.sendRedirect(request.getContextPath() + "/");
		else {
			Integer bookId = FormatUtil.strToInteger(request.getParameter("bookingId"));
			try {
				Booking book = bookDao.getBookingById(bookId);
				Flight flight = flightDao.getFlightById(book.getFlightId());
				float[] prices = flightDao.getPrice(flight.getFlightId());
				
				int current = -1;
				FlightClass bookedClass = book.getFlightClass();
				if( bookedClass == FlightClass.FIRSTCLASS )
					current = 0;
				else if( bookedClass == FlightClass.BUSINESSCLASS )
					current = 1;
				else if( bookedClass == FlightClass.ECONOMYCLASS )
					current = 2;
				session.setAttribute("book", book);
				session.setAttribute("flight", flight);
				session.setAttribute("class",  bookedClass);
				session.setAttribute("money", prices[current]);
				request.getRequestDispatcher("/re_payment.jsp").forward(request, response);
			}catch( DatabaseException | FileException e) {
				response.sendRedirect(request.getContextPath()+"error?exception="+e.getMessage());
			}
		}
	}
	
}
