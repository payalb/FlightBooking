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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + apartmentNumber;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + passengerId;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((telHome == null) ? 0 : telHome.hashCode());
		result = prime * result + ((telOffice == null) ? 0 : telOffice.hashCode());
		result = prime * result + zip;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (age != other.age)
			return false;
		if (apartmentNumber != other.apartmentNumber)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (passengerId != other.passengerId)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (telHome == null) {
			if (other.telHome != null)
				return false;
		} else if (!telHome.equals(other.telHome))
			return false;
		if (telOffice == null) {
			if (other.telOffice != null)
				return false;
		} else if (!telOffice.equals(other.telOffice))
			return false;
		if (zip != other.zip)
			return false;
		return true;
	}
	
	
}