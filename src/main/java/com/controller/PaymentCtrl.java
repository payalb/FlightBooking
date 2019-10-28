package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.FlightDao;
import com.dao.FlightDaoImpl;
import com.dao.PaymentDao;
import com.dao.PaymentDaoImpl;
import com.dto.Booking;
import com.dto.BookingStatus;
import com.dto.FlightClass;
import com.dto.Payment;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;

/**
 * Servlet implementation class PaymentCtrl
 */
@WebServlet("/payment")
public class PaymentCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PaymentDao paymentDao = new PaymentDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		HttpSession session = request.getSession(false);
		//System.out.println(request.getSession(true)==null);
		//session.setAttribute("a", "somevalue");
		if (session == null || session.getAttribute("bookingList") == null) {
			//System.out.println("Null Booking List");
			response.sendRedirect("/FlightBooking");
			return;
		}

		List<Booking> bookingList = (List<Booking>)session.getAttribute("bookingList");
//		if(bookingList==null)
//		{
//			//System.out.println("Null Booking List");
//			response.sendRedirect("/FlightBooking");
//		}
		//System.out.println(bookingList.size());
		FlightDao flightDao = new FlightDaoImpl();
		for(Booking booking : bookingList) {
			Payment payment = new Payment();
			payment.setBooking(booking);
			//to set up the correct amount
			int flightId = booking.getFlightId();
			FlightClass flightClass = booking.getFlightClass();
			float[] prices = new float[3];
			float amount = 0f;
			try {
				prices = flightDao.getPrice(flightId);
			} catch (FileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DatabaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(flightClass==FlightClass.FIRSTCLASS) {
				amount = prices[0];
			}
			if(flightClass==FlightClass.BUSINESSCLASS) {
				amount = prices[1];
			}
			if(flightClass==FlightClass.ECONOMYCLASS) {
				amount=prices[2];
			}
			payment.setPaymentAmount((double)amount);
			//finished setting up the correct amount
			//payment.setPaymentAmount(Double.parseDouble((String)session.getAttribute("totalpayment")));
			payment.setPaymentTime(LocalDateTime.now());
			try {
				if (booking == null || payment.getPaymentAmount() <=0) {
					//System.out.println("payment = "+ payment.getPaymentAmount());
					throw new InputException("Invalid input information during making payment.");
				}else {
					//System.out.println("checkpoint 1");
					//why is thhis part not reached?
					int paymentId = paymentDao.addPayment(payment);
					//System.out.println("checkpoint 2");
					payment.setPaymentId(paymentId);
				}
			}catch(InputException | DatabaseException | FileException e) {
				//throw new InputException("Invalid input information during making payment.");
				if(session.getAttribute("bookingList") != null) {
					session.removeAttribute("bookingList");
					session.invalidate();
				}
				response.sendRedirect(request.getContextPath() + "/error?exception=" );//+ e.getMessage());
			}
		}
		request.setAttribute("message", "Submit payment success!");
		request.getRequestDispatcher("/passenger-history").forward(request, response);
	}

}
