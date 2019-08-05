package com.dao;

import com.dto.Passenger;
import com.exception.DatabaseException;
import com.exception.FileException;

public interface PassengerDao {
	
	public Passenger passengerLogin(String email, String password) throws FileException, DatabaseException;

	public Passenger passengerRegister(Passenger passenger) throws DatabaseException, FileException;
	
	public Passenger getPassengerByEmail(String email) throws FileException, DatabaseException;
	
	public int updatePassenger(Passenger passenger) throws FileException, DatabaseException;
}
