package com.util;

import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.exception.FileException;

public class DataSource {
	
	private static BasicDataSource ds;
	private static Properties properties;
	
	public static BasicDataSource getDataSource() throws FileException {
		if (properties == null) {
			properties = PropertyUtil.getPropValues();
		}
		if (ds == null) {
			synchronized (DatabaseUtil.class) {
				if(ds == null) {
					ds = new BasicDataSource();
					ds.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
					ds.setUrl(properties.getProperty("jdbc.url"));
					ds.setUsername(properties.getProperty("jdbc.username"));
					ds.setPassword(properties.getProperty("jdbc.password"));
					ds.setMaxIdle(20);
				//	ds.setMaxConnLifetimeMillis(3000);
					ds.setMaxTotal(100);
				//	ds.setMaxWaitMillis(3000);
					ds.setDefaultAutoCommit(false);
					//commit
				}
			}
		}
		return ds;
	}
}
