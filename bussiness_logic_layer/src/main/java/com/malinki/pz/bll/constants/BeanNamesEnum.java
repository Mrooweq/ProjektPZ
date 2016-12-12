package com.malinki.pz.bll.constants;

public enum BeanNamesEnum {
	
	APPLICATION_CONTEXT_PROVIDER("applicationContextProvder"),
	TEMP("temp"),
	USER_REPOSITORY("userrepository");
	
	private String name;
	
	BeanNamesEnum(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
