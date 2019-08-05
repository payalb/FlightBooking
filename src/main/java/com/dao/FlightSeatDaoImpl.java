package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dto.FlightSeat;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.DatabaseUtil;

public class FlightSeatDaoImpl implements FlightSeatDao{

	@Override
	public FlightSeat getFlightSeatById(int flightId) throws DatabaseException, FileException {
		ResultSet set = null;
		FlightSeat flightSeat = null;
		String sql = "select firstclass_left, economyclass_left, businessclass_left, version "
				+ "from flight_seat where flight_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, flightId);
			set = ps.executeQuery();
			if (set.next()) {
				flightSeat = new FlightSeat(flightId, set.getInt("businessclass_left"), 
						set.getInt("firstclass_left"), set.getInt("economyclass_left"), 
						set.getInt("version"));
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get flight seat information: " + e.getMessage());
		}
		return flightSeat;
	}

	@Override
	public int addFlightSeat(FlightSeat flightSeat) throws DatabaseException, FileException {
		int row = 0;
		String sql = "insert into flight_seat(flight_id, businessclass_left, firstclass_left, economyclass_left, version) "
				+ "values (?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, flightSeat.getFlightId());
			ps.setInt(2, flightSeat.getBusinessLeft());
			ps.setInt(3, flightSeat.getFirstLeft());
			ps.setInt(4, flightSeat.getEconomyLeft());
			ps.setInt(5, flightSeat.getVersion());
			row = ps.executeUpdate();
			System.out.println(row);
			if (row == 0) {
				throw new DatabaseException("Unable to insert flight seat information.");
			}
			conn.commit();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to insert flight seat information: " + e.getMessage());
		}
		return row;
	}

	@Override
	public int updateFlightSeat(FlightSeat flightSeat) throws DatabaseException, FileException, InputException {
		int row = 0;
		String sql = "update flight_seat set businessclass_left = ?, firstclass_left = ?, economyclass_left = ?"
				+ ", version = ? where flight_id = ? and version = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			
			if (flightSeat.getBusinessLeft() < 0 || flightSeat.getEconomyLeft() < 0 || flightSeat.getFirstLeft() < 0) {
				throw new InputException("Invalid booking operation.");
			}
			ps.setInt(1, flightSeat.getBusinessLeft());
			ps.setInt(2, flightSeat.getFirstLeft());
			ps.setInt(3, flightSeat.getEconomyLeft());
			ps.setInt(4, flightSeat.getVersion()+1);
			ps.setInt(5, flightSeat.getFlightId());
			ps.setInt(6, flightSeat.getVersion());
			row = ps.executeUpdate();
			if (row == 0) {
				throw new DatabaseException("Unable to update flight seat information.");
			}
			conn.commit();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to update flight seat information: " + e.getMessage());
		}
		return row;
	}

}
