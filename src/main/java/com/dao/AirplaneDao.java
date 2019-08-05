package com.dao;

import java.util.List;

import com.dto.Airplane;
import com.exception.DatabaseException;
import com.exception.FileException;

public interface AirplaneDao {
	
	public Airplane getAirplaneById(int airplaneId) throws FileException, DatabaseException;
	
	public List<Airplane> getAirplaneList() throws FileException, DatabaseException;
}
