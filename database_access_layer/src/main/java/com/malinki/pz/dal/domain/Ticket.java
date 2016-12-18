package com.malinki.pz.dal.domain;

import java.sql.Date;

public class Ticket{
	private String name;
	private String surname;
	private String nrIDCard;
	private String email;
	private int classTravel;
	private double price;
	private String suroceAirport;
	private String destinyAirport;
	private String flyDate;
	
	public Ticket(String name, String surname, String nrIDCard, String email, int classTravel, double price,
			String suroceAirport, String destinyAirport, String flyDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.nrIDCard = nrIDCard;
		this.email = email;
		this.classTravel = classTravel;
		this.price = price;
		this.suroceAirport = suroceAirport;
		this.destinyAirport = destinyAirport;
		this.flyDate = flyDate;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
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
