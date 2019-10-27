package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

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
import com.dto.Flight;
import com.dto.FlightClass;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.EnumUtil;
import com.util.FormatUtil;

@WebServlet("/reselectSeating")
public class seatReselectCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BookingDao bookingDao = new BookingDaoImpl();
	FlightDao flightDao=new FlightDaoImpl();
	AirplaneDao airplaneDao=new AirplaneDaoImpl();
	SeatDao seatDao = new SeatDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("passengerId") == null || session.getAttribute("book") == null )
			response.sendRedirect(request.getContextPath() + "/");
		Booking book = (Booking) session.getAttribute("book");
		session.setAttribute("class", EnumUtil.stringToFlightClass(request.getParameter("class").toUpperCase()));
		if( book.getStatus() == BookingStatus.PAID ) {
			session.setAttribute("pClass", book.getFlightClass());
			session.setAttribute("pSeatId", book.getSeatNumber());
		}
	
		Integer flightId = book.getFlightId();
		Integer business = 0;
		Integer firstClass = 0;
		Integer economy = 0;
		
		String bookedClass = request.getParameter("class");
		if( "firstclass".equals(bookedClass) ) {
			firstClass++;
		}
		else if( "businessclass".equals(bookedClass) ) {
			business++;
		}
		else if( "economyclass".equals(bookedClass) ) {
			economy++;
		}
		
		ArrayList<ArrayList<String>> layout=new ArrayList<>();
		
		try {///n
			Airplane plane= airplaneDao.getAirplaneById(flightDao.getFlightById(flightId).getAirplaneId());
			layout=seatDao.getSeatLayout(flightId, plane.getFirstClassCap(), 
					plane.getBusinessClassCap(), plane.getEconomyClassCap());
			
			int[] rows=seatDao.getRowsForClasses(flightId, plane.getFirstClassCap(), 
					plane.getBusinessClassCap(), plane.getEconomyClassCap());
			request.setAttribute("rows", rows);
			
			if (business == 0 && firstClass == 0 && economy == 0) {
					throw new InputException("Invalid input, please input number of ticket to book.");
			}

			request.setAttribute("plane", plane);
			request.setAttribute("layout", layout);
			HashSet<String> availableSeat=seatDao.getAvailableSeats(flightId);
			request.setAttribute("availableSeat", availableSeat);
			request.getRequestDispatcher("/reselect_seat.jsp").forward(request, response);
		} catch (InputException | DatabaseException | FileException e) {
			response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
		}
	}
}
