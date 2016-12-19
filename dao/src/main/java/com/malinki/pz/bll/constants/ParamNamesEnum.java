package com.malinki.pz.bll.constants;

public enum ParamNamesEnum {
	LOGIN("login"),
	PASSWORD("password"),
	NAME("name"),
	SURNAME("surname"),
	NRIDCARD("nrIDCard"),
	EMAIL("email"),
	CLASSTRAVEL("classTravel"),
	PRICE("price"),
	SOURCEAIRPORT("sourceAirport"),
	DESTINYAIRPORT("destinyAirport"),
	FLYDATE("flyDate"),
	AIRLINENAME("airlineName");
	

	private String name;

	ParamNamesEnum(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}


