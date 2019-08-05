package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PassengerDao;
import com.dao.PassengerDaoImpl;
import com.dto.Passenger;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;

@WebServlet("/emailcheck")
public class EmailCheckCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PassengerDao passengerDao = new PassengerDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			if (email == null)
				throw new InputException("Invalid email information.");
			Passenger passenger = passengerDao.getPassengerByEmail(email);
			boolean used = false;
			if (passenger != null) {
				used = true;
			}
			response.getWriter().write(String.valueOf(used));
		} catch (FileException | DatabaseException | InputException e) {
			response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
