package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AdminDao;
import com.dao.AdminDaoImpl;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;

@WebServlet("/admin-login")
public class AdminLoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminDao adminDao = new AdminDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			if (username != null && password != null) {
				String adminName = adminDao.adminLogin(username, password);
				if (adminName != null && !"".equals(adminName)) {
					HttpSession session= request.getSession(true);
					session.setAttribute("adminName", adminName);
					response.sendRedirect(request.getContextPath() + "/admin_index");
				} else {
					response.sendRedirect(request.getContextPath()+"/admin?errorMsg=Invalid username or password");
				}
			} else {
				throw new InputException("Invalid input during admin login");
			}
		} catch (FileException | DatabaseException | InputException e) {
			response.sendRedirect(request.getContextPath()+"/admin_error?exception="+ e.getMessage());
		}

	}

}
