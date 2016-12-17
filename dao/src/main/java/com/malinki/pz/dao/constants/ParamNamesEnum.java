package com.malinki.pz.dao.constants;

public enum ParamNamesEnum {
	LOGIN("login"),
	PASSWORD("password");

	private String name;

	ParamNamesEnum(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}



