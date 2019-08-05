package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.Booking;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.DatabaseUtil;
import com.util.EnumUtil;

public class BookingDaoImpl implements BookingDao{

	@Override
	public List<Booking> BookingHistoryByPassengerId(int passengerId) throws FileException, DatabaseException {
		List<Booking> bookingList = new ArrayList<>();
		ResultSet set = null;
		String sql = "select booking_id, flight_id, seat_number, baggage, class, status from booking where passenger_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, passengerId);
			set = ps.executeQuery();
			while (set.next()) {
				Booking booking = new Booking(set.getInt("booking_id"), passengerId, 
						set.getInt("flight_id"), set.getInt("seat_number"), set.getInt("baggage"), 
						EnumUtil.stringToFlightClass(set.getString("class")), 
						EnumUtil.stringToBookingStatus(set.getString("status")));
				bookingList.add(booking);
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get booking information: " + e.getMessage());
		}
		return bookingList;
	}

	@Override
	public List<Booking> BookingHistory() throws FileException, DatabaseException {
		List<Booking> bookingList = new ArrayList<>();
		ResultSet set = null;
		String sql = "select booking_id, passenger_id, flight_id, seat_number, baggage, class, status from booking";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			set = ps.executeQuery();
			while (set.next()) {
				Booking booking = new Booking(set.getInt("booking_id"), set.getInt("passenger_id"), 
						set.getInt("flight_id"), set.getInt("seat_number"), set.getInt("baggage"), 
						EnumUtil.stringToFlightClass(set.getString("class")), 
						EnumUtil.stringToBookingStatus(set.getString("status")));
				bookingList.add(booking);
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get booking information: " + e.getMessage());
		}
		return bookingList;
	}

	@Override
	public int BookingFlight(Booking booking) throws DatabaseException, FileException {
		int booking_id = 0;
		String insertSql = "insert into booking(booking_id, passenger_id, flight_id, seat_number, baggage, class, status) "
				+ "values (booking_seq.nextval, ?, ?, ?, ?, ?, ?)";
		String querySql = "select booking_seq.currval as id from dual";
		ResultSet set = null;
		try (Connection conn = DatabaseUtil.getConnection(); 
				PreparedStatement insertPS = conn.prepareStatement(insertSql);
				PreparedStatement queryPS = conn.prepareStatement(querySql);) {
			insertPS.setInt(1, booking.getPassangerId());
			insertPS.setInt(2, booking.getFlightId());
			insertPS.setInt(3, booking.getSeatNumber());
			insertPS.setInt(4, booking.getBaggage());
			insertPS.setString(5, booking.getFlightClass().toString());
			insertPS.setString(6, booking.getStatus().toString());
			int row = insertPS.executeUpdate();
			if (row == 0) {
				throw new DatabaseException("Unable to book ticket.");
			}
			set = queryPS.executeQuery();
			if (set.next()) {
				booking_id = set.getInt("id");
			}
			if (booking_id == 0) {
				throw new DatabaseException("Unable to book ticket.");
			}
			if (set != null) {
				set.close();
			}
			conn.commit();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to book ticket: " + e.getMessage());
		}
		return booking_id;
	}

}
