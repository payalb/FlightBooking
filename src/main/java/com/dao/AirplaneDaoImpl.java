package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.Airplane;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.DatabaseUtil;

public class AirplaneDaoImpl implements AirplaneDao{

	@Override
	public Airplane getAirplaneById(int airplaneId) throws FileException, DatabaseException {
		Airplane airplane = null;
		ResultSet set = null;
		String sql = "select airplane_id as id, producer, firstclass_capacity as firstcap, economyclass_capacity as economycap,"
				+" businessclass_capacity as businessCap from airplane where airplane_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, airplaneId);
			set = ps.executeQuery();
			if (set.next()) {
				airplane = new Airplane(set.getInt("id"), set.getInt("firstcap"), 
						set.getInt("economycap"), set.getString("producer"), set.getInt("businessCap"));
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to query admin information: " + e.getMessage());
		}
		return airplane;
	}

	@Override
	public List<Airplane> getAirplaneList() throws FileException, DatabaseException {
		List<Airplane> airplaneList = new ArrayList<>();
		ResultSet set = null;
		String sql = "select AIRPLANE_ID as id, PRODUCER, FIRSTCLASS_CAPACITY as first, "
				+ "ECONOMYCLASS_CAPACITY as economy, BUSINESSCLASS_CAPACITY as business FROM AIRPLANE";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			set = ps.executeQuery();
			while (set.next()) {
				Airplane airplane = new Airplane(set.getInt("id"), set.getInt("first"), set.getInt("economy"), 
						set.getString("PRODUCER"), set.getInt("business"));
				airplaneList.add(airplane);
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get airplane information: " + e.getMessage());
		}
		return airplaneList;
	}

}
