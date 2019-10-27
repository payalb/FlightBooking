package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.dto.BookingStatus;
import com.dto.Flight;
import com.dto.Payment;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.DatabaseUtil;

public class PaymentDaoImpl implements PaymentDao {

	BookingDao bookingDao = new BookingDaoImpl();
	@Override
	public int addPayment(Payment p) throws DatabaseException, FileException, InputException {
		int paymentId = -1;
		ResultSet set = null;
		//BookingDao bookingDao = new BookingDaoImpl();
		String sql = "insert into payment(payment_id, passenger_id, booking_id, price, payment_time) "
				+ "values (nextval('payment_seq'), ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				PreparedStatement ps1 = conn.prepareStatement("select currval('payment_seq') as id ");) {
			ps.setInt(1, p.getBooking().getPassangerId());
			ps.setInt(2, p.getBooking().getBookingId());
			ps.setDouble(3, p.getPaymentAmount());
			ps.setTimestamp(4, Timestamp.valueOf(p.getPaymentTime()));
			//
//			set = ps1.executeQuery();
//			System.out.println(set.getInt("id"));
			int row = ps.executeUpdate();
			if (row == 0) {
				throw new DatabaseException("Unable to insert payment information.");
			} else {
				set = ps1.executeQuery();
				if (set.next()) {
					paymentId = set.getInt("id");
				}
				if (paymentId == 0) {
					throw new DatabaseException("Unable to insert payment information.");
				}
			}
			if (set != null)
				set.close();
			p.getBooking().setStatus(BookingStatus.PAID);
			bookingDao.updateBooking(p.getBooking());
			conn.commit();
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new DatabaseException("Unable to insert payment information: " + e.getMessage());
		}

		return paymentId;
	}

	@Override
	public Payment getPaymentById(int paymentId) throws  FileException, DatabaseException{
		ResultSet set = null;
		Payment payment = null;
		//BookingDao bookingDao = new BookingDaoImpl();
		String sql = "select * from payment where payment_id = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, paymentId);
			set = ps.executeQuery();
			while (set.next()) {
				payment = new Payment();
				payment.setPaymentId(paymentId);
				payment.setBooking(bookingDao.getBookingById(set.getInt("booking_id")));
				payment.setPaymentAmount(set.getDouble("price"));
				payment.setPaymentTime(set.getTimestamp("payment_time").toLocalDateTime());

			}
			if (set != null)
				set.close();
		} catch (SQLException e) {
			throw new DatabaseException("Unable to get flight information: " + e.getMessage());
		}
		return payment;
	}


	@Override
	public List<Payment> getPaymentsByPassengerId(int passengerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePayment(Payment p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePayment(int paymentId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
