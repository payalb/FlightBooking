package com.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RegisterCtrlIntegrationTest {
	RegisterCtrl ctrl= new RegisterCtrl();
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock HttpSession session;
	
	@Test
	public void test() throws ServletException, IOException {
		when(request.getParameter(anyString())).thenReturn("MALE");
		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("root");
		ctrl.doPost(request, response);
		Mockito.verify(response).sendRedirect("root/");
	}
}
