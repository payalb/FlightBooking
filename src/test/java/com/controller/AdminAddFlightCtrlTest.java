package com.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.matchers.Any;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dao.AdminDao;
import com.dao.AdminDaoImpl;
import com.dao.FlightDao;
import com.dao.FlightSeatDao;
import com.dao.PassengerDao;
import com.dto.Flight;
import com.dto.FlightSeat;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.FormatUtil;
import com.util.PropertyUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static  org.mockito.Mockito.times;


/**
 * @author    Chenghao Cai
 * @fileName  AdminAddFlightCtrlTest.java
 * @date      Oct 19, 2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FormatUtil.class,FlightDao.class,FlightSeatDao.class,Flight.class})
public class AdminAddFlightCtrlTest {
	@InjectMocks 
	AdminAddFlightCtrl ctrl;

	
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock HttpSession session;
	
	@Mock FlightSeatDao impl2;

	@Mock FlightDao impl1;
	@Test
	public void test1() throws ServletException, IOException, FileException, DatabaseException {
		
		PowerMockito.mockStatic(FormatUtil.class);
		Mockito.when(request.getParameter(anyString())).thenReturn("1");
		Mockito.when(FormatUtil.strToInteger(anyString())).thenReturn(1);
		Mockito.when(FormatUtil.strToLocalDate(anyString())).thenReturn(LocalDate.now());
		Mockito.when(FormatUtil.strToLocalTime(anyString())).
			thenReturn(LocalTime.now().minus(1,ChronoUnit.HOURS)).thenReturn(LocalTime.now());		
		PowerMockito.when(impl1.addFlight(Mockito.any(Flight.class))).thenReturn(1);
		PowerMockito.when(impl2.addFlightSeat(Mockito.any(FlightSeat.class))).thenReturn(1);
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(anyString());
	}
	@Test
	public void test2() throws ServletException, IOException, FileException, DatabaseException {
		
		PowerMockito.mockStatic(FormatUtil.class);
		Mockito.when(request.getParameter(anyString())).thenReturn("1");
		Mockito.when(FormatUtil.strToInteger(anyString())).thenReturn(1);
		Mockito.when(FormatUtil.strToLocalDate(anyString())).thenReturn(LocalDate.now());
		Mockito.when(FormatUtil.strToLocalTime(anyString())).
			thenReturn(LocalTime.now().minus(1,ChronoUnit.HOURS)).thenReturn(LocalTime.now());		
		PowerMockito.when(impl1.addFlight(Mockito.any(Flight.class))).thenReturn(1);
		PowerMockito.when(impl2.addFlightSeat(Mockito.any(FlightSeat.class))).thenReturn(0);
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(anyString());
	}@Test
	public void test3() throws ServletException, IOException, FileException, DatabaseException {
		
		PowerMockito.mockStatic(FormatUtil.class);
		Mockito.when(request.getParameter(anyString())).thenReturn("1");
		Mockito.when(FormatUtil.strToInteger(anyString())).thenReturn(1);
		Mockito.when(FormatUtil.strToLocalDate(anyString())).thenReturn(LocalDate.now());
		Mockito.when(FormatUtil.strToLocalTime(anyString())).
			thenReturn(LocalTime.now().minus(1,ChronoUnit.HOURS)).thenReturn(LocalTime.now());		
		PowerMockito.when(impl1.addFlight(Mockito.any(Flight.class))).thenReturn(0);
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(anyString());
	}
	@Test
	public void test4() throws ServletException, IOException, FileException, DatabaseException {
		
		PowerMockito.mockStatic(FormatUtil.class);
		Mockito.when(request.getParameter(anyString())).thenReturn("1");
		Mockito.when(FormatUtil.strToInteger(anyString())).thenReturn(1);
		Mockito.when(FormatUtil.strToLocalDate(anyString())).thenReturn(LocalDate.now());
		Mockito.when(FormatUtil.strToLocalTime(anyString())).
			thenReturn(LocalTime.now()).thenReturn(LocalTime.now());		
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(anyString());
	}
	@Test
	public void test5() throws ServletException, IOException, FileException, DatabaseException {
		
		PowerMockito.mockStatic(FormatUtil.class);
		Mockito.when(request.getParameter(anyString())).thenReturn("1");
		Mockito.when(FormatUtil.strToInteger(anyString())).thenReturn(null);
		Mockito.when(FormatUtil.strToLocalDate(anyString())).thenReturn(LocalDate.now());
		Mockito.when(FormatUtil.strToLocalTime(anyString())).
			thenReturn(LocalTime.now()).thenReturn(LocalTime.now());		
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(anyString());
	}
}
