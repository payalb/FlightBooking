package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.DatabaseUtil;

public class AdminDaoImpl implements AdminDao {

	@Override
	public String adminLogin(String username, String password) throws FileException, DatabaseException {
		String adminName = null;
		String sql = "select admin_name as name from admin where admin_name = ? and password = ?";
		ResultSet set = null;
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, username);
			ps.setString(2, password);
			set = ps.executeQuery();
			while (set.next()) {
				adminName = set.getString("name");
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to query admin information: " + e.getMessage());
		}
		return adminName;
	}

}
