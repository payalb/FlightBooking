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
import com.dto.Payment;
import com.dto.Seat;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;

@WebServlet("/re_payment")
public class RepaymentCtrl extends HttpServlet{
	private static final long serialVersionUID = 2L;
	
	SeatDao sd;
	BookingDao bd;
	FlightDao fd;
	PaymentDao pd;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sd = new SeatDaoImpl();
		bd = new BookingDaoImpl();
		fd = new FlightDaoImpl();
		pd = new PaymentDaoImpl();
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("passengerId") == null || session.getAttribute("book") == null ) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		Booking book = (Booking) session.getAttribute("book");
		book.setStatus(BookingStatus.PAID);
		session.removeAttribute("book");
		try {
			Seat s = sd.getSeatById(book.getSeatNumber(), book.getFlightId());
			sd.alterSeatStatus(s);
			if( session.getAttribute("pSeatId") != null ) {
				s = sd.getSeatById( (String)session.getAttribute("pSeatId"), book.getFlightId());
				sd.alterSeatStatus(s);
				session.removeAttribute("pSeatId");
				session.removeAttribute("pClass");
				Payment p = new Payment();
				p.setBooking(book);
				p.setPaymentAmount((Float) session.getAttribute("money"));
				p.setPaymentTime(LocalDateTime.now());	
				pd.addPayment(p);
				session.removeAttribute("money");
			}
			bd.updateBooking(book);
			request.setAttribute("message", "Submit payment success!");
			request.getRequestDispatcher("/passenger-history").forward(request, response);
		} catch (DatabaseException | FileException | InputException e) {
			response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
		}
	}
}
