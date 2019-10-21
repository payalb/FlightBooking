package com.dao;

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
	
	public Seat getSeatById(String seatId,int flightId) throws DatabaseException, FileException;
	
	public int addSeat(int flightId, int firstCap, int businessCap,  int economyCap) throws DatabaseException, FileException;
	
	public int updateSeat(Seat seat) throws DatabaseException, FileException, InputException;
	
}
