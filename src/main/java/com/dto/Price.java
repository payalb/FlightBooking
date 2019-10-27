package com.dto;

public class Price {
	private int flight_id;
	private float firstPrice;
	private float businessPrice;
	private float economyPrice;
	
	
	
	public int getFlight_id() {
		return flight_id;
	}



	public float getFirstPrice() {
		return firstPrice;
	}



	public float getBusinessPrice() {
		return businessPrice;
	}



	public float getEconomyPrice() {
		return economyPrice;
	}



	public Price(int flight_id, float firstPrice, float businessPrice, float economyPrice) {
		super();
		this.flight_id = flight_id;
		this.firstPrice = firstPrice;
		this.businessPrice = businessPrice;
		this.economyPrice = economyPrice;
	}
	
	
}
