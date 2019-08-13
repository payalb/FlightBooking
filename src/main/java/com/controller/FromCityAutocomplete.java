package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FlightDaoImpl;
import com.dao.From_cityDao;

import com.google.gson.Gson;

@WebServlet("/fcauto")
public class FromCityAutocomplete extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String find = request.getParameter("departure_city");
		
		System.out.println("Data from ajax call");
		ArrayList<String> list = new From_cityDao().getCityNames(find);
		list.add("Delhi");
		list.add("Mumbai");
		System.out.println(list);
		String Json = new Gson().toJson(list);
		response.getWriter().write(Json);
	}


}
