package com.malinki.pz.dal.constants;

public enum DatabaseOperationResultEnum {
	USER_ADDED_PROPERLY("User registered properly"),
	USER_ADD_ATTEMPT_FAILED_DUE_TO_ERROR("Error occured during user registering"),
	USER_ALREADY_EXIST("User with such login already exists");
	
	private String name;

	DatabaseOperationResultEnum(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}



