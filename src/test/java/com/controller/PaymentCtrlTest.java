package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dao.PaymentDao;
import com.dto.Booking;
import com.exception.InputException;

@RunWith(PowerMockRunner.class)

public class PaymentCtrlTest {
	
	PaymentCtrl ctrl = new PaymentCtrl(); 
	
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock PaymentDao impl;
	@Mock HttpSession session;
	//@Mock Booking booking;
	
	List<Booking> bookingList = new ArrayList<Booking>();

	
	@Test(expected = IOException.class)
	public void test1() throws ServletException, IOException
	{
		Booking booking = null;
		bookingList.add(booking);
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(session.getAttribute("bookingList")).thenReturn(bookingList);
		Mockito.when(session.getAttribute("paymentAmount")).thenReturn(0l);
		ctrl.doPost(request, response);
		
	}
	
}
