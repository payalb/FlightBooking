package com.controller;

import java.util.Arrays;
import java.util.List;

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

@RunWith(PowerMockRunner.class)

public class PaymentCtrlTest {
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock PaymentDao impl;
	@Mock HttpSession session;
	@Mock Booking booking;
	
	List<Booking> bookingList = Arrays.asList(booking);
	
	@Test
	public void test1()
	{
		Mockito.when(request.getSession(false)).thenReturn(session);
		Mockito.when(session.getAttribute("bookingList")).thenReturn(bookingList);
		Mockito.when(bookingList.size()).thenReturn(1);
		
	}
	
}
