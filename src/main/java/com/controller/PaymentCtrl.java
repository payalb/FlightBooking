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

import com.dao.PaymentDao;
import com.dao.PaymentDaoImpl;
import com.dto.Booking;
import com.dto.BookingStatus;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaymentDao paymentDao = new PaymentDaoImpl();
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("bookingList") == null) {
			System.out.println("Null Booking List");
			response.sendRedirect("/FlightBooking");
		}
		
		List<Booking> bookingList = (List<Booking>)session.getAttribute("bookingList");	
		if(bookingList==null)
		{
			System.out.println("Null Booking List");
			response.sendRedirect("/FlightBooking");
		}
		System.out.println(bookingList.size());
		for(Booking booking : bookingList) {
			Payment payment = new Payment();
			payment.setBooking(booking);
			payment.setPaymentAmount((Long)session.getAttribute("paymentAmount"));
			payment.setPaymentTime(LocalDateTime.now());			
			try {
				if (booking == null || payment.getPaymentAmount() <=0) {
					throw new InputException("Invalid input information during making payment.");
				}
				int paymentId = paymentDao.addPayment(payment);
				payment.setPaymentId(paymentId);
				
				
				
			}
				catch(InputException | DatabaseException | FileException | SQLException e) {
					//throw new InputException("Invalid input information during making payment.");
					response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
				
			}
		}
		
		response.sendRedirect("/FlightBooking" + "/passenger-history");
		
	}

}
