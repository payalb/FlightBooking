package com.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FlightDao;
import com.dao.FlightDaoImpl;
import com.dao.FlightSeatDao;
import com.dao.FlightSeatDaoImpl;
import com.dto.Flight;
import com.dto.FlightSeat;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.FormatUtil;

@WebServlet("/flightquery")
public class FlightQueryCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	FlightDao flightDao = new FlightDaoImpl();
	FlightSeatDao flightSeatDao = new FlightSeatDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String from = request.getParameter("from");
		String to = request.getParameter("to");
		LocalDate date = FormatUtil.strToLocalDate(request.getParameter("date"));
		try {
			if (from == null || to == null || date == null) {
				throw new InputException("Invalid input during flight querying.");
			}
			
			List<Flight> flights = flightDao.getFlightsByCityDate(from, to, date);
			if (flights == null) {
				throw new DatabaseException("Cannot get flight list.");
			} else {
				Map<Flight, Boolean> map = new HashMap<>();
				for (Flight flight : flights) {
					FlightSeat seat = flightSeatDao.getFlightSeatById(flight.getFlightId());
					if (seat == null) {
						throw new DatabaseException("Cannot get flight seat information.");
					}
					boolean left = false;
					if (seat.getBusinessLeft() > 0 || seat.getEconomyLeft() > 0 
							|| seat.getFirstLeft() > 0) {
						left = true;
					}
					map.put(flight, left);
				}
				request.setAttribute("flightList", map);
				request.getRequestDispatcher("/").forward(request, response);
			}	
		} catch (FileException | DatabaseException | InputException e) {
			response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
