package com.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dao.AdminDao;
import com.dao.AdminDaoImpl;
import com.dao.PassengerDao;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.PropertyUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static  org.mockito.Mockito.times;


/**
 * @author    Chenghao Cai
 * @fileName  AdminLoginCtrlTest.java
 * @date      Oct 18, 2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PropertyUtil.class,AdminDao.class})
public class AdminLoginCtrlTest {
	@InjectMocks 
	AdminLoginCtrl ctrl;

	
	@BeforeClass
	public static void init() throws ClassNotFoundException, SQLException, IOException, FileException, DatabaseException {
		PowerMockito.mockStatic(PropertyUtil.class);
		Properties p = new Properties();
		p.load(PassengerLoginCtrlTest.class.getResourceAsStream("/db_test.properties"));
		PowerMockito.when(PropertyUtil.getPropValues()).thenReturn(p);
	}
	
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock HttpSession session;
	@Mock AdminDao impl;
	@Test
	public void test1() throws ServletException, IOException, FileException, DatabaseException {
		Mockito.when(request.getParameter(anyString())).thenReturn("aa");
		
		PowerMockito.when(impl.adminLogin(anyString(),anyString())).thenReturn("aa");
		PowerMockito.when(request.getSession(anyBoolean())).thenReturn(session);
		Mockito.when(request.getContextPath()).thenReturn("/FlightBooking");
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(anyString());
	}
	@Test
	public void test2() throws ServletException, IOException, FileException, DatabaseException {
		Mockito.when(request.getParameter(anyString())).thenReturn(null);
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(anyString());
	}
	
	@Test
	public void test3() throws ServletException, IOException, FileException, DatabaseException {
		Mockito.when(request.getParameter(anyString())).thenReturn("aa");
		
		PowerMockito.when(impl.adminLogin(anyString(),anyString())).thenReturn("");
		PowerMockito.when(request.getSession(anyBoolean())).thenReturn(session);
		Mockito.when(request.getContextPath()).thenReturn("/FlightBooking");
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect(anyString());
	}
}
