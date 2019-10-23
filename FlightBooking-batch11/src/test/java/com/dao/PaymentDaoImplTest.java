package com.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dto.Booking;
import com.dto.BookingStatus;
import com.dto.FlightClass;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.exception.InputException;
import com.util.DataSource;
import com.util.DatabaseUtil;
import com.util.PropertyUtil;
import com.dto.Payment;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
@RunWith(PowerMockRunner.class)
@PrepareForTest({PropertyUtil.class, DataSource.class})
@PowerMockIgnore("javax.management.*")
public class PaymentDaoImplTest {
	
	//BookingDao bdi = PowerMockito.mock(BookingDaoImpl.class);
	@Mock BookingDao bd;// =new BookingDaoImpl();
	@InjectMocks PaymentDaoImpl paymentDao;
	
	@BeforeClass
	public static void init() throws ClassNotFoundException, SQLException, IOException, DatabaseException, FileException  {
		
		PowerMockito.mockStatic(PropertyUtil.class);
		Properties p = new Properties();
		p.load(AdminDaoTest.class.getResourceAsStream("/db_test.properties"));
		//System.out.println(p);
		String createSequence = "CREATE SEQUENCE PAYMENT_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 20";
		String createPayment="CREATE TABLE IF NOT EXISTS Payment(\n" + 
				"payment_id INT NOT NULL,\n" +
				"passenger_id INT NOT NULL, \n" +
				"booking_id INT NOT NULL, \n" +
				//float?
				"price NUMBER NOT NULL, \n" +
				//datetime?
				"payment_time TIMESTAMP, \n" + 
				"PRIMARY KEY (payment_id)\n" + 				
				");";
		String createBooking="CREATE TABLE IF NOT EXISTS booking(\n" + 
				"flight_id INT NOT NULL,\n" +
				"seat_number INT NOT NULL, \n" +
				"baggage INT NOT NULL, \n" +
				"class VARCHAR(20) NOT NULL, \n" +
				"status VARCHAR(20) NOT NULL, \n" +
				"booking_id INT NOT NULL, \n" + 
				"PRIMARY KEY (booking_id)\n" + 				
				");";

		String insertPayment="INSERT INTO payment VALUES(nextval('payment_seq'),1,1,25.5,'2016-11-09 10:30:00');";
		
		PowerMockito.when(PropertyUtil.getPropValues()).thenReturn(p);
		System.out.println(p);
		Connection conn = DatabaseUtil.getConnection();
		PreparedStatement createPreparedStatement = conn.prepareStatement(createSequence);
		createPreparedStatement.execute();
		createPreparedStatement = conn.prepareStatement(createPayment);
		createPreparedStatement.executeUpdate();
		createPreparedStatement = conn.prepareStatement(insertPayment);
		createPreparedStatement.executeUpdate();
		createPreparedStatement = conn.prepareStatement(createBooking);
		createPreparedStatement.executeUpdate();
		
		PreparedStatement pStatement = conn.prepareStatement("select currval('payment_seq') as id ");
		ResultSet seqVal = pStatement.executeQuery();
		if(seqVal.next()) {
			System.out.println(seqVal.getInt("id"));
		}

		
		conn.commit();
		//createPreparedStatement.close();
		//conn.close();
	}
	
	
	
	
	@Test
	public void addtest1() throws SQLException, DatabaseException, FileException, InputException {
		//PaymentDao paymentDao = new PaymentDaoImpl();
		LocalDateTime time = LocalDateTime.parse("2016-11-09T10:30");
		//LocalDateTime time = LocalDateTime.parse("2016-11-09 10:30");
		//PowerMockito.when(bd.updateBooking(any(Booking.class))).thenReturn(10);
		PowerMockito.when(bd.updateBooking(any())).thenReturn(10);
		Booking booking = new Booking(1, 1, 1, 1, 1, FlightClass.BUSINESSCLASS, BookingStatus.RESERVED);
		Payment payment = new Payment();
		payment.setBooking(booking);
		payment.setPaymentAmount(25.5);
		payment.setPaymentTime(time);
		payment.setPaymentId(4);
		//paymentDao.addPayment(payment);
		assertEquals(2, paymentDao.addPayment(payment));
	}
	
	
	@Test
	public void getTest1() throws FileException, DatabaseException, SQLException, InputException {
		System.out.println(LocalDateTime.now());
		//PaymentDao paymentDao = new PaymentDaoImpl();
		Booking booking = new Booking(1, 1, 1, 1, 1, FlightClass.BUSINESSCLASS, BookingStatus.RESERVED);
		PowerMockito.when(bd.getBookingById(anyInt())).thenReturn(booking);
		LocalDateTime time = LocalDateTime.parse("2016-11-09T10:30");
		Payment payment = new Payment();
		payment.setBooking(booking);
		payment.setPaymentAmount(25.5);
		payment.setPaymentTime(time);
		payment.setPaymentId(1);
		assertSame(1, paymentDao.getPaymentById(1).getPaymentId());
	}
	
	
//	@Test(expected = DatabaseException.class)
//	public void addtest2() throws SQLException, DatabaseException, FileException, InputException {
//		//PaymentDao paymentDao = new PaymentDaoImpl();
//		LocalDateTime time = LocalDateTime.parse("2016-11-09T10:30");
//		//LocalDateTime time = LocalDateTime.parse("2016-11-09 10:30");
//		Booking booking = new Booking(1, 1, 1, 1, 1, FlightClass.BUSINESSCLASS, BookingStatus.RESERVED);
//		Payment payment = new Payment();
//		payment.setBooking(booking);
//		payment.setPaymentAmount(25.5);
//		payment.setPaymentTime(time);
//		payment.setPaymentId(1);
//		paymentDao.addPayment(payment);
//		//assertEquals(1, paymentDao.addPayment(payment));
//	}
	
//	
//	@Test
//	public void getTest2() throws FileException, DatabaseException {
//		PaymentDao paymentDao = new PaymentDaoImpl();
//		LocalDateTime time = LocalDateTime.now();
//		Booking booking = new Booking();
//		Payment payment = new Payment();
//		payment.setBooking(booking);
//		payment.setPaymentAmount(25.5);
//		payment.setPaymentTime(time);
//		payment.setPaymentId(3);
//		assertEquals(3, paymentDao.addPayment(payment));
//	}
}
