package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.exception.InputException;

public class DateUtil {
	
	public static Date strToDate(String str) throws InputException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			throw new InputException("Invalid date format: " + e.getMessage());
		}
		return date;
	}
}
