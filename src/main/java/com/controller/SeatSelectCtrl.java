package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPath;

import org.postgresql.shaded.com.ongres.scram.common.stringprep.StringPreparation;

import com.dao.BookingDao;
import com.dao.BookingDaoImpl;
import com.dao.FlightSeatDao;
import com.dao.FlightSeatDaoImpl;
import com.dao.SeatDao;
import com.dao.SeatDaoImpl;
import com.dto.Booking;
import com.dto.FlightClass;
import com.dto.FlightSeat;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.FormatUtil;
@WebServlet("/seating")
public class SeatSelectCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SeatDao seatDao=new SeatDaoImpl();
	BookingDao bookingDao=new BookingDaoImpl();
	FlightSeatDao flightSeatDao = new FlightSeatDaoImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("passengerId") == null) {
			response.sendRedirect(request.getContextPath() + "/");
		}

		Integer passengerId = FormatUtil.objToInteger(session.getAttribute("passengerId"));
		if (passengerId == null) {
			response.sendRedirect(request.getContextPath() + "/");
		}
		int[] tickets=(int[]) session.getAttribute("tickets");
		int[] ticketsLeftVersion= (int[]) session.getAttribute("ticketsLeftVersion");
		List<Booking> bookings = new ArrayList<Booking>();
		bookings=(List<Booking>) session.getAttribute("bookingList");
		String seatStr=request.getParameter("seats");
		System.out.println(seatStr);
		for(Booking b: bookings) {
			System.out.println(b.toString());
		}
		String[] seatArr=seatStr.split("_");
		ArrayList<String> seatIds=new ArrayList<>();
		int[] classes= {0,0,0};
		String[] seatClass=new String[seatArr.length];
		if(seatArr.length<=0 || bookings.size()!=seatArr.length ) {
			response.sendRedirect(request.getContextPath() + "/error?exception=select wrong flight class seat" );
		}else {
			for(int i=0;i<seatArr.length;i++) {
				seatIds.add(seatArr[i].split("-")[2]);
				if(seatArr[i].charAt(0)=='F') {
					classes[0]++;
					seatClass[i]="F";
				}else if(seatArr[i].charAt(0)=='B') {
					classes[1]++;
					seatClass[i]="B";
				}else if(seatArr[i].charAt(0)=='E') {
					classes[2]++;
					seatClass[i]="E";
				}
			}
			for(Booking b: bookings) {
				if(b.getFlightClass()==FlightClass.FIRSTCLASS) {
					classes[0]--;
				}else if(b.getFlightClass()==FlightClass.BUSINESSCLASS) {
					classes[1]--;
				}else if(b.getFlightClass()==FlightClass.ECONOMYCLASS) {
					classes[2]--;
				}
			}
			if(classes[0]!=0 ||classes[1]!=0 || classes[2]!=0) {
				response.sendRedirect(request.getContextPath() + "/error?exception=select wrong flight class seat" );
			}else {
				for(int i=0;i<bookings.size();i++) {
					if(bookings.get(i).getFlightClass()==FlightClass.FIRSTCLASS) {
						for(int j=0;j<seatClass.length;j++) {
							if(seatClass[j].equals("F")) {
								bookings.get(i).setSeatNumber(seatIds.get(j));
							}
						}
					}else if(bookings.get(i).getFlightClass()==FlightClass.BUSINESSCLASS) {
						for(int j=0;j<seatClass.length;j++) {
							if(seatClass[j].equals("B")) {
								bookings.get(i).setSeatNumber(seatIds.get(j));
							}
						}
					}else if(bookings.get(i).getFlightClass()==FlightClass.ECONOMYCLASS) {
						for(int j=0;j<seatClass.length;j++) {
							if(seatClass[j].equals("E")) {
								bookings.get(i).setSeatNumber(seatIds.get(j));
							}
						}
					}
				}
				try {
					for(int i=0;i<bookings.size();i++) {
					
						int row1=bookingDao.updateBooking(bookings.get(i));
						if (row1 <= 0) {
							throw new DatabaseException("Cannot update booking information.");
						}
						for(String s:seatIds) {
							int row2 = seatDao.alterSeatStatus(seatDao.getSeatById(s, bookings.get(0).getFlightId()));
							if (row2 <= 0) {
								throw new DatabaseException("Cannot update seat information.");
							}
						}
						
						//FlightSeat seat = new FlightSeat(flightId, businessLeft, firstLeft, economyLeft, oldVersion++);
						System.out.println(ticketsLeftVersion.toString());
						FlightSeat seat = new FlightSeat(ticketsLeftVersion[0], --ticketsLeftVersion[1],
								--ticketsLeftVersion[2],--ticketsLeftVersion[3], ticketsLeftVersion[4]++);
						int row = flightSeatDao.updateFlightSeat(seat);
						if (row <= 0) {
							//throw new DatabaseException("Cannot update flight seat information.");
						}
					
					}
					session.setAttribute("bookingList", bookings);
					request.getRequestDispatcher("/payment.jsp").forward(request, response);
				} catch (InputException | DatabaseException | FileException e) {	
					e.printStackTrace();

					response.sendRedirect(request.getContextPath() + "/error?exception=" + e.getMessage());
				}
								
			}
			
		}
		
		
	}

}
