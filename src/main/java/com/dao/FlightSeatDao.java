package com.dao;

import com.dto.FlightSeat;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;

public interface FlightSeatDao {
	
	public FlightSeat getFlightSeatById(int flightId) throws DatabaseException, FileException;
	
	public int addFlightSeat(FlightSeat flightSeat) throws DatabaseException, FileException;
	
	public int updateFlightSeat(FlightSeat flightSeat) throws DatabaseException, FileException, InputException;
	
}
