package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AirplaneDao;
import com.dao.AirplaneDaoImpl;
import com.dto.Airplane;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.google.gson.Gson;

@WebServlet("/airplaneinfo")
public class AirplaneInfoCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AirplaneDao airplaneDao = new AirplaneDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Airplane> list = airplaneDao.getAirplaneList();
			if (list == null || list.size() == 0) {
				throw new DatabaseException("Cannot get valid airplane.");
			} else {
				Gson gson = new Gson();
				response.getWriter().write(gson.toJson(list));
			}
		} catch (FileException | DatabaseException e) {
			response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
