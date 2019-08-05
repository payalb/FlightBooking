package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dto.Passenger;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.DatabaseUtil;
import com.util.EnumUtil;

public class PassengerDaoImpl implements PassengerDao{

	@Override
	public Passenger passengerLogin(String email, String password) throws FileException, DatabaseException {
		Passenger passenger = null;
		ResultSet set = null;
		String sql = "select passenger_id, email from passenger where email = ? and password = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, email);
			ps.setString(2, password);
			set = ps.executeQuery();
			while (set.next()) {
				passenger = new Passenger();
				passenger.setPassengerId(set.getInt("passenger_id"));
				passenger.setEmail(set.getString("email"));
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to query passenger information: " + e.getMessage());
		}
		return passenger;
	}
	
	@Override
	public Passenger getPassengerByEmail(String email) throws FileException, DatabaseException {
		Passenger passenger = null;
		ResultSet set = null;
		String sql = "select passenger_id, email, password, firstname, lastname, gender, ssn, age, street, "
				+ "apartment_number, city, state, zip, tel_home, tel_office from passenger where email = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, email);
			set = ps.executeQuery();
			if (set.next()) {
				passenger = new Passenger(set.getInt("passenger_id"), set.getString("password"), 
						set.getString("firstname"), set.getString("lastname"), set.getString("email"), 
						EnumUtil.stringToGender(set.getString("gender")), set.getString("ssn"), 
						set.getInt("age"), set.getString("street"), set.getInt("apartment_number"), 
						set.getString("city"), set.getString("state"), set.getInt("zip"), 
						set.getString("tel_home"), set.getString("tel_office"));
			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get passenger information: " + e.getMessage());
		}
		return passenger;
	}

	@Override
	public Passenger passengerRegister(Passenger passenger) throws DatabaseException, FileException {
		ResultSet set = null;
		String updateSql = "insert into passenger(passenger_id, email, password, firstname, lastname, gender) values("
				+ "passenger_seq.nextval, ?, ?, ?, ?, ?)";
		String querySql = "select passenger_seq.currval as id from dual";
		try (Connection conn = DatabaseUtil.getConnection(); 
				PreparedStatement updatePs = conn.prepareStatement(updateSql);
				PreparedStatement queryPs = conn.prepareStatement(querySql);) {
			updatePs.setString(1, passenger.getEmail());
			updatePs.setString(2, passenger.getPassword());
			updatePs.setString(3, passenger.getFirstName());
			updatePs.setString(4, passenger.getLastName());
			updatePs.setString(5, passenger.getGender().toString());
			int row = updatePs.executeUpdate();
			if (row == 0)
				throw new DatabaseException("Unable to register passenger.");
			else {
				set = queryPs.executeQuery();
				if (set.next())
					passenger.setPassengerId(set.getInt("id"));
			}
			if (set != null)
				set.close();
			conn.commit();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to register passenger: " + e.getMessage());
		}
		return passenger;
	}

	@Override
	public int updatePassenger(Passenger passenger) throws FileException, DatabaseException {
		int row = 0;
		String sql = "update passenger set tel_office = ?, firstname = ?, lastname = ?, gender = ?, ssn = ?, "
				+ "age = ?, street = ?, apartment_number = ?, city = ?, state = ?, zip = ?, tel_home = ? "
				+ "where email = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, passenger.getTelOffice());
			ps.setString(2, passenger.getFirstName());
			ps.setString(3, passenger.getLastName());
			ps.setString(4, passenger.getGender().toString());
			ps.setString(5, passenger.getSsn());
			ps.setInt(6, passenger.getAge());
			ps.setString(7, passenger.getStreet());
			ps.setInt(8, passenger.getApartmentNumber());
			ps.setString(9, passenger.getCity());
			ps.setString(10, passenger.getState());
			ps.setInt(11, passenger.getZip());
			ps.setString(12, passenger.getTelHome());
			ps.setString(13, passenger.getEmail());
			row = ps.executeUpdate();
			if (row == 0)
				throw new DatabaseException("Unable to update passenger.");
			conn.commit();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to update passenger: " + e.getMessage());
		}
		return row;
	}

}
