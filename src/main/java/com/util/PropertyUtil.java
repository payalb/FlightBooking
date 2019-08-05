package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.exception.FileException;

public class PropertyUtil {

	private static Properties prop;

	public static Properties getPropValues() throws FileException {

		String dbProperties = "/database.properties";
		if (prop == null) {
			prop = new Properties();
			
			try (InputStream inputStream = Thread.currentThread()
				    .getContextClassLoader().getResourceAsStream(dbProperties);) {
				if (inputStream != null) {
					prop.load(inputStream);
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
