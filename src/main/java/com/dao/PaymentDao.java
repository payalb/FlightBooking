package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.Payment;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;

public interface PaymentDao {

	public int addPayment(Payment p) throws SQLException, DatabaseException, FileException, InputException;
	
	public Payment getPaymentById(int paymentId) throws DatabaseException, FileException;
	
	public List<Payment> getPaymentsByPassengerId(int passengerId);
	
	public int updatePayment(Payment p);
	
	public int deletePayment(int paymentId);
}
