	package com.dto;

public class FlightSeat {
	private int flightId;
	private int businessLeft;
	private int firstLeft;
	private int economyLeft;
	private int version;
	
	public FlightSeat() {super();}
	
	public FlightSeat(int flightId, int businessLeft, int firstLeft, int economyLeft, int version) {
		super();
		this.flightId = flightId;
		this.businessLeft = businessLeft;
		this.firstLeft = firstLeft;
		this.economyLeft = economyLeft;
		this.version = version;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getBusinessLeft() {
		return businessLeft;
	}
	public void setBusinessLeft(int businessLeft) {
		this.businessLeft = businessLeft;
	}
	public int getFirstLeft() {
		return firstLeft;
	}
	public void setFirstLeft(int firstLeft) {
		this.firstLeft = firstLeft;
	}
	public int getEconomyLeft() {
		return economyLeft;
	}
	public void setEconomyLeft(int economyLeft) {
		this.economyLeft = economyLeft;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
