package com.util;

import com.dto.BookingStatus;
import com.dto.FlightClass;
import com.dto.Gender;

public class EnumUtil {

	public static Gender stringToGender(String str) {
		str = str.trim();
		if (str == null || "".equals(str)) {
			return null;
		}
		Gender gender = null;
		switch (str.toUpperCase()) {
			case "MALE":
				gender = Gender.MALE;
				break;

			case "FEMALE":
				gender = Gender.FEMALE;
				break;
		}
		return gender;
	}
	
	public static BookingStatus stringToBookingStatus(String str) {
		str = str.trim();
		if (str == null || "".equals(str)) {
			return null;
		}
		BookingStatus status = null;
		switch (str.toUpperCase()) {
			case "PAID":
				status = BookingStatus.PAID;
				break;

			case "RESERVED":
				status = BookingStatus.RESERVED;
				break;
				
			case "CANCELLED":
				status = BookingStatus.CANCELLED;
				break;
		}
		return status;
	}
	
	public static FlightClass stringToFlightClass(String str) {
		str = str.trim();
		if (str == null || "".equals(str)) {
			return null;
		}
		FlightClass flightClass = null;
		switch (str.toUpperCase()) {
			case "ECONOMYCLASS":
				flightClass = FlightClass.ECONOMYCLASS;
				break;

			case "FIRSTCLASS":
				flightClass = FlightClass.FIRSTCLASS;
				break;
				
			case "BUSINESSCLASS":
				flightClass = FlightClass.BUSINESSCLASS;
				break;
		}
		return flightClass;
	}
}
