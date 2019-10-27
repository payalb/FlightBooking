package com.dto;

public class Booking {
	private int bookingId;
	private int passangerId;
	private int flightId;
	private String seatNumber;
	private int baggage;
	private FlightClass flightClass;
	private BookingStatus status;
	private float price;

	
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Booking() {
		super();
	}
	
	public Booking(int passangerId, int flightId, String seatNumber, int baggage, FlightClass flightClass,
			BookingStatus status) {
		super();
		this.passangerId = passangerId;
		this.flightId = flightId;
		this.seatNumber = seatNumber;
		this.baggage = baggage;
		this.flightClass = flightClass;
		this.status = status;
	}

	public Booking(int bookingId, int passangerId, int flightId, String seatNumber, int baggage, FlightClass flightClass,
			BookingStatus status) {
		super();
		this.bookingId = bookingId;
		this.passangerId = passangerId;
		this.flightId = flightId;
		this.seatNumber = seatNumber;
		this.baggage = baggage;
		this.flightClass = flightClass;
		this.status = status;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getPassangerId() {
		return passangerId;
	}

	public void setPassangerId(int passangerId) {
		this.passangerId = passangerId;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getBaggage() {
		return baggage;
	}

	public void setBaggage(int baggage) {
		this.baggage = baggage;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}
}
