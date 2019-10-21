package com.controller;

import static org.mockito.Matchers.anyString;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dao.PassengerDao;
import com.dao.PassengerDaoImpl;
import com.dto.Gender;
import com.dto.Passenger;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.PropertyUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PropertyUtil.class})
@PowerMockIgnore("javax.management.*")
public class PassengerLoginCtrlTest {
	
	@InjectMocks PassengerLoginCtrl ctrl;
	
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock HttpSession ses;
	
	@Mock PassengerDao pd;

	
	@BeforeClass
	public static void init() throws ClassNotFoundException, SQLException, IOException, FileException, DatabaseException {
		PowerMockito.mockStatic(PropertyUtil.class);
		Properties p = new Properties();
		p.load(PassengerLoginCtrlTest.class.getResourceAsStream("/db_test.properties"));
		PowerMockito.when(PropertyUtil.getPropValues()).thenReturn(p);
	}
	
	@Test
	public void test1() throws FileException, DatabaseException, IOException, ServletException {
		Mockito.when(request.getContextPath()).thenReturn("/FlightBooking");
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(request.getContextPath()+"/error?exception=Invalid passenger information.");
	}
	
	@Test
	public void test2() throws FileException, DatabaseException, IOException, ServletException {
		Mockito.when(request.getParameter(anyString())).thenReturn("abc");
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(request.getContextPath()+"/login?errorMsg=Invalid username or password");
	}

	@Test
	public void test3() throws FileException, DatabaseException, IOException, ServletException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Mockito.when(request.getParameter(anyString())).thenReturn("abc");
		Passenger p = new Passenger(1, "abc");
		Mockito.when(pd.passengerLogin(anyString(), anyString())).thenReturn(p);
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(request.getContextPath()+"/error?exception=Invalid passenger information.");
	}
	
	@Test
	public void test4() throws FileException, DatabaseException, IOException, ServletException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Mockito.when(request.getParameter(anyString())).thenReturn("abc");
		Passenger p = new Passenger(1, "abc");
		p.setEmail("abc@apolis.com");
		Mockito.when(pd.passengerLogin(anyString(), anyString())).thenReturn(p);
		Mockito.when(request.getSession(true)).thenReturn(ses);
		Mockito.when(request.getContextPath()).thenReturn("/FlightBooking");
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(request.getContextPath()+"/");
	}
}
