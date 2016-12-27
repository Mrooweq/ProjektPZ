package com.malinki.pz.dal.constants;

public enum DatabaseOperationResultEnum {
	USER_REGISTERED_SUCCESSFULLY("User registered properly"),
	USER_ALREADY_EXIST("User with such login already exists"),
	USER_REGISTER_ATTEMPT_FAILED_DUE_TO_ERROR("Error occured during user registering"),
	
	USER_LOGGED_IN_SUCCESSFULLY("User logged in successfully"),
	USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_WRONG_USERNAME_OR_PASSWORD("User log in attempt failed due to wrong username or password"),
	USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR("User log in attempt failed due to error"),

	TICKET_BOUGHT_SUCCESSFULLY("Ticket bought successfully"),
	TICKET_NOT_BOUGHT_SUCCESSFULLY_DUE_TO_ERROR("Ticket not bought successfully due to error");

	private String name;

	DatabaseOperationResultEnum(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}



