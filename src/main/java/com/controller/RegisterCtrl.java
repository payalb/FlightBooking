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
import com.dto.Gender;
import com.dto.Passenger;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.EnumUtil;

@WebServlet("/passenger-register")
public class RegisterCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PassengerDao passengerDao = new PassengerDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("pass_confirmation");
		Gender gender = EnumUtil.stringToGender(request.getParameter("gender"));

		try {
			if (firstName == null || lastName == null || email == null || password == null || gender == null) {
				throw new InputException("Invalid input information during registration.");
			}
			Passenger passenger = new Passenger(password, firstName, lastName, email, gender);
			passenger = passengerDao.passengerRegister(passenger);
			HttpSession session = request.getSession(true);
			session.setAttribute("passengerId", passenger.getPassengerId());
			session.setAttribute("passengerEmail", passenger.getEmail());
			response.sendRedirect(request.getContextPath() + "/");

		} catch (FileException | DatabaseException |

				InputException e) {
			response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
		}
	}

}
