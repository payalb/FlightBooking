package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dto.Flight;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.DatabaseUtil;

public class FlightDaoImpl implements FlightDao {

	@Override
	public int addFlight(Flight flight) throws FileException, DatabaseException {
		int flightId = -1;
		ResultSet set = null;
		String sql = "insert into flight(flight_id, departure_time, arrival_time, departure_city, arrival_city, "
				+ "airplane_id) values (nextval('flight_seq'), ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);
				PreparedStatement ps1 = conn.prepareStatement("select currval('flight_seq') as id ");
				) {
			ps.setTimestamp(1, Timestamp.valueOf(flight.getDepartureTime()));
			ps.setTimestamp(2, Timestamp.valueOf(flight.getArrivalTime()));
			ps.setString(3, flight.getDepartureCity());
			ps.setString(4, flight.getArrivalCity());
			ps.setInt(5, flight.getAirplaneId());
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DatabaseException("Unable to insert flight information.");
			} else {

				//ps -> ps1

				set = ps1.executeQuery();
				if (set.next()) {
					flightId = set.getInt("id");
				}
				if (flightId == 0) {
					throw new DatabaseException("Unable to insert flight information.");
				}
			}
			if (set != null)
				set.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Unable to insert flight information: " + e.getMessage());
		}
		return flightId;
	}

	@Override
	public Flight getFlightById(int flightId) throws FileException, DatabaseException {
		ResultSet set = null;
		Flight flight = null;
		String sql = "select flight_id, departure_time, arrival_time, departure_city, arrival_city, "
				+ "airplane_id from flight where flight_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, flightId);
			set = ps.executeQuery();
			while (set.next()) {
				flight = new Flight(set.getInt("flight_id"), set.getTimestamp("departure_time").toLocalDateTime(),
						set.getTimestamp("arrival_time").toLocalDateTime(), set.getString("departure_city"),
						set.getString("arrival_city"), set.getInt("airplane_id"));
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get flight information: " + e.getMessage());
		}
		return flight;
	}

	@Override
	public List<Flight> getFlightsByCityDate(String depCity, String arrCity, LocalDate depTime)
			throws FileException, DatabaseException {
		ResultSet set = null;
		List<Flight> flightList = new ArrayList<>();
		String sql = "select flight_id, departure_time, arrival_time, departure_city, arrival_city, "
				+ "airplane_id from flight where upper(departure_city) like upper(?) and "
				+ "upper(arrival_city) like upper(?) and date(departure_time) = date(?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, "%" + depCity + "%");
			ps.setString(2, "%" + arrCity + "%");
			ps.setString(3, depTime.toString());
			set = ps.executeQuery();
			while (set.next()) {
				Flight flight = new Flight(set.getInt("flight_id"),
						set.getTimestamp("departure_time").toLocalDateTime(),
						set.getTimestamp("arrival_time").toLocalDateTime(), set.getString("departure_city"),
						set.getString("arrival_city"), set.getInt("airplane_id"));
				flightList.add(flight);
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get flight information: " + e.getMessage());
		}
		return flightList;
	}

	@Override
	public int updateFlight(Flight flight) throws DatabaseException, FileException {
		int row = 0;
		String sql = "update flight set departure_time = ?, arrival_time = ?, departure_city = ?, arrival_city = ? "
				+ "where flight_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setTimestamp(1, Timestamp.valueOf(flight.getDepartureTime()));
			ps.setTimestamp(2, Timestamp.valueOf(flight.getArrivalTime()));
			ps.setString(3, flight.getDepartureCity());
			ps.setString(4, flight.getArrivalCity());
			ps.setInt(5, flight.getFlightId());
			row = ps.executeUpdate();
			if (row == 0) {
				throw new DatabaseException("Unable to update flight information.");
			}
			conn.commit();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to update flight information: " + e.getMessage());
		}
		return row;
	}

	@Override
	public int deleteFlight(int flightId) throws FileException, DatabaseException {
		int row = 0;
		String sql = "delete from flight where flight_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, flightId);
			row = ps.executeUpdate();
			if (row == 0) {
				throw new DatabaseException("Unable to delete flight information.");
			}
			conn.commit();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to delete flight information: " + e.getMessage());
		}
		return row;
	}

}
