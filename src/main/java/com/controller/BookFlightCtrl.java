package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AirplaneDao;
import com.dao.AirplaneDaoImpl;
import com.dao.BookingDao;
import com.dao.BookingDaoImpl;
import com.dao.FlightDao;
import com.dao.FlightDaoImpl;
import com.dao.FlightSeatDao;
import com.dao.FlightSeatDaoImpl;
import com.dao.SeatDao;
import com.dao.SeatDaoImpl;
import com.dto.Airplane;
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
	List<Booking> bookings = new ArrayList<Booking>();
	///n
	FlightDao flightDao=new FlightDaoImpl();
	AirplaneDao airplaneDao=new AirplaneDaoImpl();
	SeatDao seatDao = new SeatDaoImpl();///e
	
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
		///n
		int[] tickets=new int[3];
		
		
		///e  first business economy
		try {///n
			Airplane plane= airplaneDao.getAirplaneById(flightDao.getFlightById(flightId).getAirplaneId());
			ArrayList<ArrayList<String>> layout=seatDao.getSeatLayout(flightId, plane.getFirstClassCap(), 
					plane.getBusinessClassCap(), plane.getEconomyClassCap());
			
			///e
			if (flightId == null || busiBaggage == null || businessLeft == null 
					|| firstLeft == null || economyLeft == null || oldVersion == null) {
				throw new InputException("Invalid input information during booking.");
			} else {
				if (business == 0 && firstClass == 0 && economy == 0) {
					throw new InputException("Invalid input, please input number of ticket to book.");
				}
			}

			for (int i = 0; i < business; i++) {
				///m     
				Booking booking = new Booking(passengerId, flightId,"",
						busiBaggage, FlightClass.BUSINESSCLASS, BookingStatus.RESERVED);
				businessLeft--;
				tickets[1]++;       ///e
				int bookingId = bookingDao.BookingFlight(booking);
				if (bookingId <= 0) {
					throw new DatabaseException("Cannot insert booking information.");
				}
				booking.setBookingId(bookingId);
				bookings.add(booking);				
				FlightSeat seat = new FlightSeat(flightId, businessLeft, firstLeft, economyLeft, oldVersion++);
				int row = flightSeatDao.updateFlightSeat(seat);
				if (row <= 0) {
					throw new DatabaseException("Cannot update flight seat information.");
				}
			}

			for (int i = 0; i < firstClass; i++) {
				///m
				Booking booking = new Booking(passengerId, flightId,"",
						firstBaggage, FlightClass.FIRSTCLASS, BookingStatus.RESERVED);
				firstLeft--;   
				tickets[0]++; ///e
				int bookingId = bookingDao.BookingFlight(booking);
				if (bookingId <= 0) {
					throw new DatabaseException("Cannot insert booking information.");
				}
				booking.setBookingId(bookingId);
				bookings.add(booking);	
				FlightSeat seat = new FlightSeat(flightId, businessLeft, firstLeft, economyLeft, oldVersion++);
				int row = flightSeatDao.updateFlightSeat(seat);
				if (row <= 0) {
					throw new DatabaseException("Cannot update flight seat information.");
				}
			}

			for (int i = 0; i < economy; i++) {
				///m
				Booking booking = new Booking(passengerId, flightId, "",
						econoBaggage, FlightClass.ECONOMYCLASS, BookingStatus.RESERVED);
				economyLeft--;
				tickets[2]++;
				 ///e
				int bookingId = bookingDao.BookingFlight(booking);
				if (bookingId <= 0) {
					throw new DatabaseException("Cannot insert booking information.");
				}
				booking.setBookingId(bookingId);
				bookings.add(booking);	
				FlightSeat seat = new FlightSeat(flightId, businessLeft, firstLeft, economyLeft, oldVersion++);
				int row = flightSeatDao.updateFlightSeat(seat);
				if (row <= 0) {
					throw new DatabaseException("Cannot update flight seat information.");
				}
			}
			session.setAttribute("bookingList", bookings);
			
			System.out.println( bookings.size());
			///m  /payment.jsp
			session.setAttribute("plane", plane);
			session.setAttribute("layout", layout);
			session.setAttribute("tickets", tickets);
			HashSet<String> availableSeat=seatDao.getAvailableSeats(flightId);
			System.out.println(availableSeat.size());
			session.setAttribute("availableSeat", availableSeat);
			request.getRequestDispatcher("/select_seat.jsp").forward(request, response);
			//e
			//response.sendRedirect(request.getContextPath() + "/passenger-history");
		} catch (InputException | DatabaseException | FileException e) {
			response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
		}
	}

}
