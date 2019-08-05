package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PassengerDao;
import com.dao.PassengerDaoImpl;
import com.dto.Passenger;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;

@WebServlet("/passenger-login")
public class PassengerLoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PassengerDao passengerDao = new PassengerDaoImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			if (email == null || password == null) {
				throw new InputException("Invalid passenger information.");
			}
			Passenger passenger = passengerDao.passengerLogin(email, password);
			if (passenger != null) {
				HttpSession session= request.getSession(true);
				if (passenger.getPassengerId() != 0 && passenger.getEmail() != null) {
					session.setAttribute("passengerId", passenger.getPassengerId());
					session.setAttribute("passengerEmail", passenger.getEmail());
					response.sendRedirect(request.getContextPath()+"/");
				} else {
					throw new DatabaseException("Invalid passenger information.");
				}
			} else {
				response.sendRedirect(request.getContextPath()+"/login?errorMsg=Invalid username or password");
			}
		} catch (FileException | DatabaseException | InputException e) {
			response.sendRedirect(request.getContextPath()+"/error?exception="+ e.getMessage());
		}
	}

}
