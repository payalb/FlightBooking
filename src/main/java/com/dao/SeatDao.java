package com.dao;

import java.util.ArrayList;

import javax.swing.JList.DropLocation;

import com.dto.FlightSeat;
import com.dto.Seat;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;

/**
 * @author    Chenghao Cai
 * @fileName  SeatDao.java
 * @date      Oct 20, 2019
 */

public interface SeatDao {
	// get seat object by seatId, and flightId
	public Seat getSeatById(String seatId,int flightId) throws DatabaseException, FileException;
	// add all the seats for a flight
	public int addSeats(int flightId, int firstCap, int businessCap,  int economyCap) throws DatabaseException, FileException;
	// toggle seat status (unavailable, available)
	public int alterSeatStatus(Seat seat) throws DatabaseException, FileException, InputException;
	//drop all the seats for a flight
	public int deleteFlightSeats(int flightId)throws DatabaseException, FileException, InputException;

	public ArrayList<Seat> getAvailableSeats(int flightId)throws DatabaseException, FileException, InputException;
	public ArrayList<ArrayList<String>> getSeatLayout(int flightId, int firstCap, int businessCap,  int economyCap);
	

}
