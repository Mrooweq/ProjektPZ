package com.malinki.pz.lib;

import java.awt.Image;

public class BlobDTO{

	private Image logo;
	private String airline;
	
	public BlobDTO(Image logo, String airline) {
		this.logo = logo;
		this.airline = airline;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
}
