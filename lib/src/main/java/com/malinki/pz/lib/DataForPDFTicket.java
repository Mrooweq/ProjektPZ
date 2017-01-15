package com.malinki.pz.lib;

import com.itextpdf.layout.element.Image;

public class DataForPDFTicket {

	private Image airlineLogo;
	private String ticketID;

	private String sourceAirport;
	private String destinyAirport;
	private String flightDate;
	private String airlineName;
	private String firstname;
	private String lastName;
	private String nrIDCard;
	private String email;
	private String flight;
	private String price;
	private String flightClass;

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getTicketID() {
		return ticketID;
	}

	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}
	
	public Image getAirlineLogo() {
		return airlineLogo;
	}

	public void setAirlineLogo(Image airlineLogo) {
		this.airlineLogo = airlineLogo;
	}

	public String getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(String sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public String getDestinyAirport() {
		return destinyAirport;
	}

	public void setDestinyAirport(String destinyAirport) {
		this.destinyAirport = destinyAirport;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}	

}
