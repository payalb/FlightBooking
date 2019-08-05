package com.dao;

import java.util.List;

import com.dto.Booking;
import com.exception.DatabaseException;
import com.exception.FileException;

public interface BookingDao {

	public List<Booking> BookingHistoryByPassengerId(int passengerId) throws FileException, DatabaseException;
	
	public List<Booking> BookingHistory() throws FileException, DatabaseException;
	
	public int BookingFlight(Booking booking) throws DatabaseException, FileException;
}
