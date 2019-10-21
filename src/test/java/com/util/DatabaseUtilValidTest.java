package com.util;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import com.exception.DatabaseException;
import com.exception.FileException;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class DatabaseUtilValidTest {
	
	@Test
	public void validTest() throws SQLException, FileException, DatabaseException {
		assertNotNull(DatabaseUtil.getConnection());
	}

}
