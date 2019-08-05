package com.dto;

public class Passenger {

	private int passengerId;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Gender gender;
	private String ssn;
	private int age;
	private String street;
	private int apartmentNumber;
	private String city;
	private String state;
	private int zip;
	private String telHome;
	private String telOffice;

	public Passenger() {
		super();
	}

	public Passenger(int passengerId, String password) {
		super();
		this.passengerId = passengerId;
		this.password = password;
	}

	public Passenger(String password, String firstName, String lastName, String email, Gender gender) {
		super();
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}
	
	public Passenger(String firstName, String lastName, String email, Gender gender, String ssn, int age, String street,
			int apartmentNumber, String city, String state, int zip, String telHome, String telOffice) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.ssn = ssn;
		this.age = age;
		this.street = street;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.telHome = telHome;
		this.telOffice = telOffice;
	}

	public Passenger(int passengerId, String password, String firstName, String lastName, String email, Gender gender,
			String ssn, int age, String street, int apartmentNumber, String city, String state, int zip, String telHome,
			String telOffice) {
		super();
		this.passengerId = passengerId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.ssn = ssn;
		this.age = age;
		this.street = street;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.telHome = telHome;
		this.telOffice = telOffice;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getTelHome() {
		return telHome;
	}

	public void setTelHome(String telHome) {
		this.telHome = telHome;
	}

	public String getTelOffice() {
		return telOffice;
	}

	public void setTelOffice(String telOffice) {
		this.telOffice = telOffice;
	}
}