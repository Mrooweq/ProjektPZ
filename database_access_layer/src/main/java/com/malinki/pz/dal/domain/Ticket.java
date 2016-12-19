package com.malinki.pz.dal.domain;

public class Ticket {
	private String name;
	private String lastName;
	private String nrIDCard;
	private String email;
	private int classTravel;
	private double price;
	private String suroceAirport;
	private String destinyAirport;
	private String flyDate;
	private String airlineName;

	public Ticket(String name, String lastName, String nrIDCard, String email, int classTravel, double price,
			String suroceAirport, String destinyAirport, String flyDate, String airportName) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.nrIDCard = nrIDCard;
		this.email = email;
		this.classTravel = classTravel;
		this.price = price;
		this.suroceAirport = suroceAirport;
		this.destinyAirport = destinyAirport;
		this.flyDate = flyDate;
		this.airlineName = airportName;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getSuroceAirport() {
		return suroceAirport;
	}

	public void setSuroceAirport(String suroceAirport) {
		this.suroceAirport = suroceAirport;
	}

	public String getDestinyAirport() {
		return destinyAirport;
	}

	public void setDestinyAirport(String destinyAirport) {
		this.destinyAirport = destinyAirport;
	}

	public String getFlyDate() {
		return flyDate;
	}

	public void setFlyDate(String flyDate) {
		this.flyDate = flyDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNrIDCard() {
		return nrIDCard;
	}

	public void setNrIDCard(String nrIDCard) {
		this.nrIDCard = nrIDCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getClassTravel() {
		return classTravel;
	}

	public void setClassTravel(int classTravel) {
		this.classTravel = classTravel;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
