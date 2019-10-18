package com.dao;

import java.time.LocalDate;
import java.util.List;

import com.dto.Flight;
import com.exception.DatabaseException;
import com.exception.FileException;

public interface FlightDao {
	public int addFlight(Flight flight) throws FileException, DatabaseException;
	
	public Flight getFlightById(int flightId) throws FileException, DatabaseException;
	
	public List<Flight> getFlightsByCityDate(String depCity, String arrCity, LocalDate depTime) throws FileException, DatabaseException;
	
	public int updateFlight(Flight flight) throws DatabaseException, FileException;
	
	public int deleteFlight(int flightId) throws FileException, DatabaseException;
}
