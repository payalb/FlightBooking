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
import com.util.EnumUtil;
import com.util.FormatUtil;

@WebServlet("/update-profile")
public class ProfileUpdateCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PassengerDao passengerDao = new PassengerDaoImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String ssn = request.getParameter("ssn");
		Integer age = FormatUtil.strToInteger(request.getParameter("age"));
		String address = request.getParameter("address");
		Integer aptNumber = FormatUtil.strToInteger(request.getParameter("aptNumber"));
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		Integer zip = FormatUtil.strToInteger(request.getParameter("zip"));
		String telHome = request.getParameter("telHome");
		String telOffice = request.getParameter("telOffice");
		
		try {
			if (email == null || firstName == null || lastName == null || gender == null)
				throw new InputException("Invalid input during profile updating");
				
			Passenger passenger = new Passenger(firstName, lastName, email, EnumUtil.stringToGender(gender), ssn, age,
				address, aptNumber, city, state, zip, telHome, telOffice);
			
			int row = passengerDao.updatePassenger(passenger);
			if (row == 1) {
				response.sendRedirect(request.getContextPath()+"/");
			} else {
				response.sendRedirect(request.getContextPath()
						+"/error?exception=Update passenger information fails.");
			}
		} catch (FileException | DatabaseException | InputException e) {
			response.sendRedirect(request.getContextPath()+"/error?exception="+ e.getMessage());
		}
	}

}
