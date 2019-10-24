package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.juli.OneLineFormatter;

import com.exception.FileException;

public class PropertyUtil {

	private static Properties prop;
	static ClassLoader cl = PropertyUtil.class.getClassLoader();
	


	public static void setCl(ClassLoader cl) {
		PropertyUtil.cl = cl;
	}



	public static Properties getPropValues() throws FileException {

		String dbProperties = "database.properties";
		if (prop == null) {
			prop = new Properties();
			
			//Thread currenThread = Thread.currentThread();
			
			//ClassLoader cl = currenThread.getContextClassLoader();
			
			// 1.the property file does not exist
			// 2.the fields inside the file is empty
			
			InputStream ip = cl.getResourceAsStream(dbProperties);
			System.out.println(ip==null);
			
			try (InputStream inputStream = cl.getResourceAsStream(dbProperties);) {
				System.out.println(cl.getResourceAsStream(dbProperties)==null);
				System.out.println(inputStream==null);
				if (inputStream != null) {
					prop.load(inputStream);
					String url = prop.getProperty("jdbc.url");
					System.out.println(url);
					String username = prop.getProperty("jdbc.username");
					String password = prop.getProperty("jdbc.password");
					String driver = prop.getProperty("jdbc.driverClassName");
					if(url!=null & username!=null && password!=null && driver!=null) {
						
					}else {
						throw new FileException("One or more field is blank, plz check property file");
					}
				} else {
					throw new FileException(
							"Database property file '" + dbProperties + "' not found in the classpath.");
				}
			} catch (IOException e) {
				throw new FileException("Loading database property file error: " + e.getMessage());
			}
		}
		return prop;
	}
}
