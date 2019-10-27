package com.util;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.exception.DatabaseException;
import com.exception.FileException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PropertyUtil.class})
@PowerMockIgnore("javax.management.*")
public class DatabaseUtilvalidTest2 {
	
	@Test
	public void noDriverNameTest() throws SQLException, FileException, DatabaseException, IOException {	
		Properties p = new Properties();
		p.setProperty("jdbc.url", "jdbc:h2:mem:test");
		p.setProperty("jdbc.username","sa");
		p.setProperty("jdbc.driverClassName","");
		p.setProperty("jdbc.password", "sa");
		PowerMockito.mockStatic(PropertyUtil.class);
		PowerMockito.when(PropertyUtil.getPropValues()).thenReturn(p);
		assertNotNull(DatabaseUtil.getConnection());
	}
}