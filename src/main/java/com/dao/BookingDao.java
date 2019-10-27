package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.Booking;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;

public interface BookingDao {

	public List<Booking> BookingHistoryByPassengerId(int passengerId) throws FileException, DatabaseException;

	public List<Booking> FutureBookingByPassengerId(int passengerId) throws FileException, DatabaseException;

	public List<Booking> BookingHistory() throws FileException, DatabaseException;

	public Booking getBookingById(int bookingId) throws DatabaseException, FileException;

	public int BookingFlight(Booking booking) throws DatabaseException, FileException;

	public int cancelBooking(Booking booking) throws DatabaseException, FileException, InputException;

	public int updateBooking(Booking booking) throws DatabaseException, FileException, InputException;

	int deleteBooking(Booking booking) throws DatabaseException, FileException, InputException;
}
