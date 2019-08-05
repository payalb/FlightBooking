package com.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.flywaydb.core.Flyway;

import com.exception.FileException;

public class FlywayListerner implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	Flyway flyway= new Flyway();
		flyway.setBaselineOnMigrate(true);
		flyway.setLocations("classpath:/migration");
		try {
			flyway.setDataSource(DataSource.getDataSource());
		} catch (FileException e) {
			sce.getServletContext().setAttribute("connMsg", e.getMessage());
		}
		flyway.migrate();
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    
    }
}
