package com.dto;

/**
 * @author    Chenghao Cai
 * @fileName  Seat.java
 * @date      Oct 20, 2019
 */

public class Seat {
	private String seatId; //11A
	private int flightId;
	private FlightClass flightClass;
	private SeatStatus seatStatus;
	private SeatLocation seatLocation;
	
	
	
	
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public FlightClass getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}
	public SeatStatus getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}
		
	public SeatLocation getSeatLocation() {
		return seatLocation;
	}
	public void setSeatLocation(SeatLocation seatLocation) {
		this.seatLocation = seatLocation;
	}
	public Seat(String seatId, int flightId, FlightClass flightClass, SeatStatus seatStatus,
			SeatLocation seatLocation) {
		super();
		this.seatId = seatId;
		this.flightId = flightId;
		this.flightClass = flightClass;
		this.seatStatus = seatStatus;
		this.seatLocation = seatLocation;
	}
	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", flightId=" + flightId + ", flightClass=" + flightClass + ", seatStatus="
				+ seatStatus + ", seatLocation=" + seatLocation + "]";
	}
	
}


