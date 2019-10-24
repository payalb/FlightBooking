package com.util;

import org.junit.Test;
import com.util.PropertyUtil;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.anyString;

import java.io.InputStream;
import java.util.Properties;

import com.dao.BookingDao;
import com.dao.BookingDaoImpl;
import com.exception.FileException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PropertyUtil.class, ClassLoader.class,BookingDaoImpl.class,BookingDao.class})
public class PropertyUtilTest {
	
	

	//@Mock InputStream stream;
	@Mock ClassLoader cl;
//	@InjectMocks
//	PropertyUtil propertyUtil = new PropertyUtil();
	
	
	
//	@Test(expected = FileException.class)
//	public void test1() throws FileException{
//		String dbProperties = "/database.properties";
//		
//		Mockito.when(thread.currentThread().getContextClassLoader().getResourceAsStream(anyString())).thenReturn(null);
//		propertyUtil.getPropValues();
//	}
	
	//the question is: how do i mock a static field?
	@Test(expected = FileException.class)
	public void test2() throws FileException{
		String dbProperties = "database.properties";
		String testProperties = "db_test.properties";
		
		//PowerMockito.mockStatic(PropertyUtil.class);
		//PowerMockito.when(PropertyUtil.class.getClassLoader()).thenReturn(cl);
	
//		Mockito.when(Thread.currentThread().getContextClassLoader()).thenReturn(classLoader);
//		Mockito.when(currentThread.getContextClassLoader()).thenReturn(classLoader);
		
		PowerMockito.when(cl.getResourceAsStream(dbProperties)).thenReturn(null);
		
		PropertyUtil.setCl(cl);
		
		
		//Mockito.when(cl.getResourceAsStream(dbProperties)).thenReturn(null);
		PropertyUtil.getPropValues();
		//assertSame(PropertyUtil.getPropValues(), new Properties());
		
		
		
	}
	
	@Test
	public void test3() throws FileException{
		String dbProperties = "database.properties";
		String testProperties = "db_test1.properties";
//		
//		ClassLoader myLoader = PropertyUtil.class.getClassLoader();
//		InputStream myStream = myLoader.getResourceAsStream(testProperties);
////		Mockito.when(Thread.currentThread().getContextClassLoader()).thenReturn(classLoader);
////		Mockito.when(currentThread.getContextClassLoader()).thenReturn(classLoader);
//		PowerMockito.when(cl.getResourceAsStream(dbProperties)).thenReturn(myStream);
//		Mockito.when(cl.getResourceAsStream(dbProperties)).thenReturn(myStream);
//		//propertyUtil.getPropValues();
//		assertSame(PropertyUtil.getPropValues(), new Properties());
//		
	}
}
