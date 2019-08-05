package com.controller;

import static org.junit.Assert.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dao.PassengerDao;
import com.dto.Passenger;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.PropertyUtil;

import static org.mockito.Matchers.anyString;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PropertyUtil.class})
@PowerMockIgnore("javax.management.*")
public class PassengerLoginCtrlTest {

	PassengerLoginCtrl ctrl = new PassengerLoginCtrl();
	
	@BeforeClass
	public static void init() throws ClassNotFoundException, SQLException, IOException, FileException, DatabaseException {
		PowerMockito.mockStatic(PropertyUtil.class);
		Properties p = new Properties();
		p.load(PassengerLoginCtrlTest.class.getResourceAsStream("/db_test.properties"));
		PowerMockito.when(PropertyUtil.getPropValues()).thenReturn(p);
	}
	
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock PassengerDao impl;
	@Test
	public void test1() throws FileException, DatabaseException, IOException, ServletException {
		Mockito.when(impl.passengerLogin(anyString(), anyString())).thenReturn(null);
		Mockito.when(request.getContextPath()).thenReturn("/FlightBooking");
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(request.getContextPath()
				+"/login?errorMsg=Invalid username or password");
	}
	
	@Test(expected=DatabaseException.class)
	public void test2() throws FileException, DatabaseException, IOException, ServletException {
		Mockito.when(impl.passengerLogin(anyString(), anyString())).thenReturn(new Passenger());
		ctrl.doPost(request, response);
	}

	@Test
	public void test3() throws FileException, DatabaseException, IOException, ServletException {
		Mockito.when(impl.passengerLogin(anyString(), anyString())).thenReturn(new Passenger(1, "lyf@hotmail.com"));
		Mockito.when(request.getContextPath()).thenReturn("/FlightBooking");
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(request.getContextPath()+"/");
	}
}
