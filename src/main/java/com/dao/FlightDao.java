package com.dao;

import java.time.LocalDate;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.dto.Flight;
import com.dto.Price;
import com.exception.DatabaseException;
import com.exception.FileException;

public interface FlightDao {
	public float[] getPrice(int flightId) throws FileException, DatabaseException;
	
	public int addFlight(Flight flight) throws FileException, DatabaseException;
	
	public void setPrice(Price price) throws FileException, DatabaseException;
	
	public Flight getFlightById(int flightId) throws FileException, DatabaseException;
	
	public List<Flight> getFlightsByCityDate(String depCity, String arrCity, LocalDate depTime) throws FileException, DatabaseException;
	
	public int updateFlight(Flight flight) throws DatabaseException, FileException;
	
	public int deleteFlight(int flightId) throws FileException, DatabaseException;
}
