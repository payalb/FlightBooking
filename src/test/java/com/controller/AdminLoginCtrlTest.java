//package com.controller;
//
//import static org.junit.Assert.*;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import static org.mockito.ArgumentMatchers.*;
//
//@RunWith(PowerMockRunner.class)
//public class AdminLoginCtrlTest {
//
//	AdminLoginCtrl ctrl = new AdminLoginCtrl();
//	
//	@Mock HttpServletRequest request;
//	@Mock HttpServletResponse response;
//	@Test
//	public void test1() throws ServletException, IOException {
//		Mockito.when(request.getParameter(anyString())).thenReturn("43");
//		PowerMockito.mockStatic(SeatDao.class);
//		PowerMockito.when(SeatDao.getSeats(anyInt())).thenReturn(new Seat());
//		StringWriter obj = new StringWriter();
//		Mockito.when(response.getWriter()).thenReturn(new PrintWriter(obj));
//		ctrl.doPost(request, response);
//		assertEquals(new Gson().toJson(new Seat(1,1,1,1)), obj.toString());
//	}
//
//}
