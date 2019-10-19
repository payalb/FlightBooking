package com.dao;


import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.exception.DatabaseException;
import com.exception.FileException;
import com.util.DataSource;
import com.util.DatabaseUtil;
import com.util.PropertyUtil;

/**
 * @author    Chenghao Cai
 * @fileName  AdminDaoTest.java
 * @date      Oct 18, 2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PropertyUtil.class, DataSource.class})
@PowerMockIgnore("javax.management.*")
public class AdminDaoTest {
	@BeforeClass
	public static void init() throws ClassNotFoundException, SQLException, IOException, DatabaseException, FileException  {
		PowerMockito.mockStatic(PropertyUtil.class);
		Properties p = new Properties();
		p.load(AdminDaoTest.class.getResourceAsStream("/db_test.properties"));
		//System.out.println(p);
		String createQuery="CREATE TABLE IF NOT EXISTS Admin(\n" + 
				"adminId INT NOT NULL,\n" + 
				"admin_name VARCHAR(45) NOT NULL,\n" + 
				"password VARCHAR(45) NOT NULL,\n" +  
				"PRIMARY KEY (adminId)\n" + 				
				");";
		String insertQuery="INSERT INTO Admin VALUES (1,'admin','admin');";
				
		PowerMockito.when(PropertyUtil.getPropValues()).thenReturn(p);System.out.println(p);
		Connection conn = DatabaseUtil.getConnection();
		
		PreparedStatement createPreparedStatement = conn.prepareStatement(createQuery);
		createPreparedStatement.executeUpdate();
		createPreparedStatement = conn.prepareStatement(insertQuery);
		createPreparedStatement.executeUpdate();
		conn.commit();
		//createPreparedStatement.close();
		//conn.close();
	
	@Test
	public void testLoginInvalid() throws SQLException, IOException, DatabaseException, FileException {
		AdminDao adminDao=new AdminDaoImpl();
		
		assertEquals(null,adminDao.adminLogin("1","1"));
	}
	@Test
	public void testLoginValid() throws SQLException, IOException, DatabaseException, FileException {
		
		AdminDao adminDao=new AdminDaoImpl();
		
		assertEquals("admin",adminDao.adminLogin("admin","admin"));
	}
	
	
	/*
	@Test
	public void  selectUserValid() throws  DatabaseException {		
		User u= new User("sys3","pwd","admin");
		UserDao.insertUser(u);
		assertEquals("sys3",UserDao.selectUser("sys3","pwd","admin"));
	}
	@Test(expected=DatabaseException.class)
	public void  selectUserInvalid() throws  DatabaseException {		
		User u= new User("sys4","pwd","adminadminadminadminadminadminvadminadminadminadminadminadminadmin");
		UserDao.insertUser(u);
		
	}	
		*/
}
