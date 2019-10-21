package com.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dto.Gender;
import com.dto.Passenger;
import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.DataSource;
import com.util.DatabaseUtil;
import com.util.PropertyUtil;


@RunWith(PowerMockRunner.class)
@PrepareForTest({PropertyUtil.class, DataSource.class})
@PowerMockIgnore("javax.management.*")
public class PassengerDapImplTest {

	@InjectMocks PassengerDaoImpl pdl;
	
	@BeforeClass
	public static void init() throws IOException, FileException, DatabaseException, SQLException {
		PowerMockito.mockStatic(PropertyUtil.class);
		Properties p = new Properties();
		p.load(PassengerDapImplTest.class.getResourceAsStream("/db_test.properties"));
		String CreateQuery="create table passenger(\n" + 
				"    passenger_id integer primary key not null," + 
				"    email varchar(45) not null," + 
				"    password varchar(50) not null," + 
				"    firstname varchar(45) not null," + 
				"    lastname varchar(45) not null," + 
				"    gender varchar(6) not null," + 
				"    ssn varchar(9)," + 
				"    age integer," + 
				"    street varchar(45)," + 
				"    apartment_number integer," + 
				"    city varchar(45)," + 
				"    state varchar(45)," + 
				"    zip integer," + 
				"    tel_home varchar(45)," + 
				"    tel_office varchar(45)," + 
				"    CONSTRAINT email_unique UNIQUE (email))";
		PowerMockito.when(PropertyUtil.getPropValues()).thenReturn(p);
		Connection conn = DatabaseUtil.getConnection();
		Statement st = conn.createStatement();
		st.execute(CreateQuery);
		st.execute("create sequence passenger_seq MINVALUE 1 start with 1 increment by 1 cache 20");
		st.executeUpdate("insert into passenger values(5, 'abc@abc.com', 'password', 'abc', 'abc', 'MALE', '0000', 10, 'Illinois Ave', 30, 'St Charles', 'IL', 60174, '2132434344', '887388733')");
		conn.commit();
	}
	
	@Test
	public void pLoginTest1() throws FileException, DatabaseException {
		assertNotNull(pdl.passengerLogin("abc@abc.com", "password"));
	}

	@Test
	public void pLoginTest2() throws FileException, DatabaseException {
		assertNull(pdl.passengerLogin("abc@abc.c", "password"));
	}
	
	@Test
	public void gpByEmailTest1() throws FileException, DatabaseException {
		assertNotNull(pdl.getPassengerByEmail("abc@abc.com"));
	}
	
	@Test
	public void gpByEmailTest2() throws FileException, DatabaseException {
		assertNull(pdl.getPassengerByEmail("abc@abc.c"));
	}
	
	@Test
	public void pRegTest1() throws DatabaseException, FileException {
		Passenger p =new Passenger("aabbcc", "apolis", "rjt", "bcd@bcd.com", Gender.FEMALE);
		assertEquals(p, pdl.passengerRegister(p));
	}
	
	@Test(expected=DatabaseException.class)
	public void pRegTest2() throws DatabaseException, FileException {
		Passenger p =new Passenger("aabbcc", "apolis", "rjt", "bcd@bcd.com", Gender.FEMALE);
		pdl.passengerRegister(p);
	}
	
	@Test
	public void uPassenTest1() throws FileException, DatabaseException {
		Passenger p =new Passenger("abc", "apols", "rt", "bcd@bcd.com", Gender.MALE);
		assertEquals(1, pdl.updatePassenger(p));
	}
	
	@Test(expected=DatabaseException.class)
	public void uPassenTest2() throws FileException, DatabaseException {
		Passenger p =new Passenger("abc", "apols", "rt", "bcd@bcd.co", Gender.MALE);
		assertEquals(0, pdl.updatePassenger(p));
	}
	

}
