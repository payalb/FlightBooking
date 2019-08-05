package com.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class FormatUtil {

	public static Integer strToInteger(String str) {
		if (str == null)
			return null;
		try {
			return Integer.parseInt(str.trim());
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public static Integer strToInteger(String str, int def) {
		if (str == null)
			return def;
		try {
			return Integer.parseInt(str.trim());
		} catch (NumberFormatException e) {
			return def;
		}
	}
	
	public static LocalDate strToLocalDate(String str) {
		if (str == null)
			return null;
		try {
			return LocalDate.parse(str.trim());
		} catch (DateTimeParseException e) {
			return null;
		}
	}
	
	public static LocalTime strToLocalTime(String str) {
		if (str == null)
			return null;
		try {
			return LocalTime.parse(str.trim());
		} catch (DateTimeParseException e) {
			return null;
		}
	}
	
	public static Integer objToInteger(Object obj) {
		if (obj == null)
			return null;
		try {
			return (Integer) obj;
		} catch (ClassCastException e) {
			return null;
		}
		
	}
}
