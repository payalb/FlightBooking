package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.ResolutionSyntax;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.flywaydb.core.internal.database.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.Returns;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dao.PaymentDao;
import com.dao.PaymentDaoImpl;
import com.dto.Booking;
import com.dto.Payment;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
public class PaymentCtrlTest {
	
	//PaymentCtrl ctrl = new PaymentCtrl(); 
	
//	@Mock Payment payment1;
	@Mock PaymentDao paymentDao;
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock HttpSession session;
	@Mock Payment payment;
	
	@InjectMocks
	PaymentCtrl ctrl;
	
	

	
	//@Mock Booking booking;
	
	List<Booking> bookingList = new ArrayList<Booking>();
	
	
	//test if a session is null
	@Test
	public void test1() throws ServletException, IOException{
		Mockito.when(request.getSession(false)).thenReturn(null);
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect("/FlightBooking");
	}
	
	//test if a booking list is null
	@Test
	public void test2() throws ServletException, IOException{
		//HttpSession sessionobj = Mockito.mock(HttpSession.class);	
		
		Mockito.when(session.getAttribute("bookingList")).thenReturn(null);
		Mockito.when(request.getSession(false)).thenReturn(session);
		
		ctrl.doGet(request, response);
		Mockito.verify(response).sendRedirect("/FlightBooking");
	}
	
	
	//test if everything goes well
	@Test
	public void test3() throws ServletException, IOException, SQLException, DatabaseException, FileException, InputException{
		Booking booking = Mockito.mock(Booking.class);
		bookingList.add(booking);
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(session.getAttribute("bookingList")).thenReturn(bookingList);
		Mockito.when(session.getAttribute("paymentAmount")).thenReturn(100l);
		Mockito.when(paymentDao.addPayment(Mockito.any(Payment.class))).thenReturn(1);
		ctrl.doPost(request, response);
		//Mockito.verify(response).sendRedirect(request.getContextPath() + "/error?exception=");// + anyString());
		Mockito.verify(response).sendRedirect("/FlightBooking" + "/passenger-history");
	}
	
	//test if the input amount is negative
	@Test
	public void test4() throws ServletException, IOException
	{
		Booking booking = null;
		bookingList.add(booking);
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(session.getAttribute("bookingList")).thenReturn(bookingList);
		Mockito.when(session.getAttribute("paymentAmount")).thenReturn(0l);
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(request.getContextPath() + "/error?exception=");
		
	}
	
}
