package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import com.dao.PaymentDao;
import com.dao.PaymentDaoImpl;
import com.dao.SeatDao;
import com.dao.SeatDaoImpl;
import com.dto.Booking;
import com.dto.BookingStatus;
import com.dto.FlightClass;
import com.dto.Payment;
import com.dto.Seat;
import com.dto.SeatStatus;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.FormatUtil;

@WebServlet("/bookingCancel")
public class BookingCancelCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BookingDao bookDao;
	FlightDao flightDao;
	SeatDao seatDao;
	PaymentDao pDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		bookDao = new BookingDaoImpl();
		flightDao = new FlightDaoImpl();
		seatDao = new SeatDaoImpl();
		pDao = new PaymentDaoImpl();
		
		Integer bookId = FormatUtil.strToInteger(request.getParameter("bookingId"));
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("passengerId") == null ) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		session.removeAttribute("flight");
		session.removeAttribute("book");
		try {
			if( bookId == null ) {
				throw new InputException("Invalid booking information.");
			}
			Booking book = bookDao.getBookingById(bookId);
			if( book == null ) {
				throw new DatabaseException("Cannot get booking information.");
			}
			BookingStatus bs = book.getStatus();
			if( bs.toString().equalsIgnoreCase("paid") ){
				float[] prices = flightDao.getPrice(book.getFlightId());
				int current = -1;
				FlightClass bookedClass = book.getFlightClass();
				if( bookedClass == FlightClass.FIRSTCLASS )
					current = 0;
				else if( bookedClass == FlightClass.BUSINESSCLASS )
					current = 1;
				else if( bookedClass == FlightClass.ECONOMYCLASS )
					current = 2;
				Payment p = new Payment();
				p.setBooking(book);
				p.setPaymentAmount(-prices[current]);
				p.setPaymentTime(LocalDateTime.now());	
				pDao.addPayment(p);
				request.setAttribute("message", "Cancel booking success! A refund of $" +flightDao.getPrice(book.getFlightId())[current]+ " will be returned to your account shortly.");
			}else {
				request.setAttribute("message", "Cancel booking success!");
			}
			if( book.getSeatNumber() != null && !book.getSeatNumber().equals("") && seatDao.getSeatById(book.getSeatNumber(), book.getFlightId()).getSeatStatus() == SeatStatus.UNAVAILABLE )
				seatDao.alterSeatStatus(new Seat(book.getSeatNumber(), book.getFlightId(), null, SeatStatus.UNAVAILABLE, null));
			bookDao.cancelBooking(book);
			request.getRequestDispatcher("/passenger-history").forward(request, response);
		}catch( InputException | DatabaseException | FileException e ) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
