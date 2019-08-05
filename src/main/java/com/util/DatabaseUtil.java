package com.util;

import java.sql.Connection;

import java.sql.SQLException;

import com.exception.DatabaseException;
import com.exception.FileException;

public class DatabaseUtil {

	public static Connection getConnection() throws DatabaseException, FileException {
		
		Connection conn = null;
		try {
			conn = DataSource.getDataSource().getConnection();
		} catch (SQLException e) {
			throw new DatabaseException("Cannot connect database: " + e.getMessage());
		}
		return conn;
	}
}
