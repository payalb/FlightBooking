package com.dto;

import java.time.LocalDateTime;

public class Flight {
	private int flightId;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private String departureCity;
	private String arrivalCity;
	private int airplaneId;
	
	public Flight() {super();}
	
	public Flight(int flightId, LocalDateTime departureTime, LocalDateTime arrivalTime, String departureCity,
			String arrivalCity) {
		super();
		this.flightId = flightId;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
	}

	public Flight(LocalDateTime departureTime, LocalDateTime arrivalTime, String departureCity,
			String arrivalCity, int airplaneId) {
		super();
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.airplaneId = airplaneId;
	}
	
	public Flight(int flightId, LocalDateTime departureTime, LocalDateTime arrivalTime, String departureCity,
			String arrivalCity, int airplaneId) {
		super();
		this.flightId = flightId;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.airplaneId = airplaneId;
	}


	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public int getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(int airplaneId) {
		this.airplaneId = airplaneId;
	}

}
