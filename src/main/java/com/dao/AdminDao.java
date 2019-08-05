package com.dao;

import com.exception.DatabaseException;
import com.exception.FileException;

public interface AdminDao {

	public String adminLogin(String username, String password) throws FileException, DatabaseException;
}
