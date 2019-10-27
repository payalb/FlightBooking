package com.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

@RunWith(PowerMockRunner.class)
@PrepareForTest({PropertyUtil.class, DataSource.class, DatabaseUtil.class})
@PowerMockIgnore("javax.management.*")
public class BookingDaoImplTest {
	
	@InjectMocks BookingDaoImpl bdi;
	
	@BeforeClass
	public static void init() throws IOException, FileException, DatabaseException, SQLException {
		PowerMockito.mockStatic(PropertyUtil.class);
		Properties p = new Properties();
		p.load(BookingDaoImplTest.class.getResourceAsStream("/db_test.properties"));
		String query = "create table booking(" + 
				"    booking_id integer primary key not null," + 
				"    passenger_id integer not null," + 
				"    flight_id integer not null," + 
				"    seat_number varchar(10) not null," + 
				"    baggage integer not null," + 
				"    class varchar(15) not null," + 
				"    status varchar(20) not null," + 
				");";
		
		PowerMockito.when(PropertyUtil.getPropValues()).thenReturn(p);
		Connection conn = DatabaseUtil.getConnection();
		Statement st = conn.createStatement();
		st.execute("create sequence booking_seq start with 1 increment by 1 cache 20");
		st.execute(query);
		st.execute("insert into booking values(5,5,5,'5A',5,'FIRSTCLASS','PAID')");
		conn.commit();
	}
	
//	@Test
//	public void BookByIdtest1() throws FileException, DatabaseException {
//		assertNotNull(bdi.BookingHistoryByPassengerId(5));
//	}
	
	@Test
	public void BookByIdtest2() throws FileException, DatabaseException {
		assertNotNull(bdi.BookingHistoryByPassengerId(0));
	}
	
	@Test
	public void BookHistest1() throws FileException, DatabaseException {
		assertNotNull(bdi.BookingHistory());
	}
	
	@Test
	public void BookFlitest1() throws FileException, DatabaseException {
		Booking b = new Booking( 1, 1, "", 1, FlightClass.FIRSTCLASS, BookingStatus.PAID);
		assertNotNull(bdi.BookingFlight(b));
	}	
	
	@Test//(expected=DatabaseException.class)
	public void getBookByIdtest1() throws FileException, DatabaseException, SQLException {
		assertNotNull(bdi.getBookingById(5));
	}	
	
	@Test
	public void getBookByIdtest2() throws FileException, DatabaseException, SQLException {
		assertNull(bdi.getBookingById(2));
	}	
	
	@Test
	public void updateBooktest1() throws FileException, DatabaseException, SQLException, InputException {
		Booking b = new Booking( 5, 1, 5, "1A", 1, FlightClass.FIRSTCLASS, BookingStatus.PAID);
		assertNotNull(bdi.updateBooking(b));
	}	
	
	@Test(expected=InputException.class)
	public void updateBooktest2() throws FileException, DatabaseException, SQLException, InputException {
		Booking b = new Booking( 1, 1, "1A", 1, FlightClass.FIRSTCLASS, BookingStatus.PAID);
		assertNotNull(bdi.updateBooking(b));
	}	
	
	@Test(expected=DatabaseException.class)
	public void updateBooktest3() throws FileException, DatabaseException, SQLException, InputException {
		Booking b = new Booking( 4, 4, 1, "1A", 1, FlightClass.FIRSTCLASS, BookingStatus.CANCELLED);
		assertNotNull(bdi.updateBooking(b));
	}	
	
}
