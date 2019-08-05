package com.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FlightDao;
import com.dao.FlightDaoImpl;
import com.dto.Flight;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.FormatUtil;

@WebServlet("/adminflightquery")
public class AdminFlightQueryCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FlightDao flightDao = new FlightDaoImpl();
       
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
				throw new DatabaseException("Cannot get information from database.");
			} else {
				request.setAttribute("flightList", flights);
				request.getRequestDispatcher("/admin_index.jsp").forward(request, response);
			}	
		} catch (FileException | DatabaseException | InputException e) {
			response.sendRedirect(request.getContextPath() + "/admin_error?exception=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
