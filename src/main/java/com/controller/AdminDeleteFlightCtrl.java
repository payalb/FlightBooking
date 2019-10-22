package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FlightDao;
import com.dao.FlightDaoImpl;
import com.dao.SeatDao;
import com.dao.SeatDaoImpl;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.FormatUtil;

@WebServlet("/adminflightdelete")
public class AdminDeleteFlightCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FlightDao flightDao = new FlightDaoImpl();
	SeatDao seatDao = new SeatDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer flightId = FormatUtil.strToInteger(request.getParameter("flightId"));
		try {
			if (flightId == null) {
				throw new InputException("Invalid input in deleting the flight.");
			} else {
				int row = flightDao.deleteFlight(flightId);
				if (row == 0) {
					throw new DatabaseException("Cannot delete the flight.");
				}
				
				//change start
				int row2=seatDao.deleteFlightSeats(flightId);
				if (row2 <= 0) {
					throw new DatabaseException("Fails to delete the seat information.");
				}
				//
				
				response.sendRedirect(request.getContextPath() + "/admin_index");
			}
		} catch (InputException | FileException | DatabaseException e) {
			response.sendRedirect(request.getContextPath() + "/admin_error?exception=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
