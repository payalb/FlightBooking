//package com.dao;
//
//import static org.junit.Assert.*;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PowerMockIgnore;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import com.util.DataSource;
//import com.util.DatabaseUtil;
//import com.util.PropertyUtil;
//
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({PropertyUtil.class, DataSource.class, })
//@PowerMockIgnore("javax.management.*")
//public class PassengerDapImplTest {
//
//	@BeforeClass
//	public static void init() throws ClassNotFoundException, SQLException, IOException {
//		PowerMockito.mockStatic(PropertyUtil.class);
//		Properties p = new Properties();
//		p.load(PassengerDapImplTest.class.getResourceAsStream("/db_test.properties"));
//		String CreateQuery="CREATE TABLE Passanger(\n" + 
//				"Passanger_id INT NOT NULL AUTO_INCREMENT,\n" + 
//				"username VARCHAR(20),\n" + 
//				"FirstName VARCHAR(45) NOT NULL,\n" + 
//				"LastName VARCHAR(45) ,\n" + 
//				"SSN VARCHAR(9),\n" + 
//				"Age INT,\n" + 
//				"Street VARCHAR(45) NOT NULL,\n" + 
//				"Apartment_number INT NOT NULL,\n" + 
//				"City VARCHAR(45) NOT NULL,\n" + 
//				"State VARCHAR(45) NOT NULL,\n" + 
//				"Zip INT NOT NULL,\n" + 
//				"Tel_home VARCHAR(45),\n" + 
//				"Tel_office VARCHAR(45),\n" + 
//				"Email VARCHAR(45),\n" + 
//				"PRIMARY KEY (Passanger_id)\n" + 
//				"\n" + 
//				");" ;
//		PowerMockito.when(PropertyUtil.getPropValues()).thenReturn(p);
//		Connection conn = DatabaseUtil.getConnection();
//		PreparedStatement createPreparedStatement = conn.prepareStatement(CreateQuery);
//		createPreparedStatement.executeUpdate();
//	}
//	
//	@Test
//	public void testInsert() throws IOException, SQLException {
//		Passanger p =new Passanger(null,"Sohan","P","3345",25,"St Charles",1000,"St Charles","IL",12345,"12345","456789","abc@abc.com");
//		
//		assertEquals(1,PassangerDao.insertPassanger(p));
//	}
//	
//	@Test
//	public void testGetId() throws IOException, SQLException {
//		Passanger p =new Passanger("abc","Sohan","P","3345",25,"St Charles",1000,"St Charles","IL",12345,"12345","456789","abc@abc.com");
//		PassangerDao.insertPassanger(p);
//		assertEquals(2,PassangerDao.getPassangerId("abc"));
//	}
//
//}
