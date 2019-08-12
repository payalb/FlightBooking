package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PaymentDao;
import com.dao.PaymentDaoImpl;
import com.dto.Booking;
import com.dto.Payment;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;

/**
 * Servlet implementation class PaymentCtrl
 */
@WebServlet("/PaymentCtrl")
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaymentDao paymentDao = new PaymentDaoImpl();
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("booking") == null) {
			response.sendRedirect(request.getContextPath() + "/");
		}
		Booking booking = (Booking)session.getAttribute("booking");
		Payment payment = new Payment();
		payment.setBooking(booking);
		payment.setPaymentAmount(Double.valueOf(request.getParameter("paymentAmount")));
		payment.setPaymentTime(LocalDateTime.now());
		
		try {
			if (booking == null || payment.getPaymentAmount() <=0) {
				throw new InputException("Invalid input information during making payment.");
			}
			int paymentId = paymentDao.addPayment(payment);
			payment.setPaymentId(paymentId);
			session.setAttribute("payment", payment);
			
		}
			catch(InputException | DatabaseException | FileException | SQLException e) {
				response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
			
		}
	}

}
