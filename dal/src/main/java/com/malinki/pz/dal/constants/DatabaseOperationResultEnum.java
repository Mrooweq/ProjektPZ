package com.malinki.pz.dal.constants;

public enum DatabaseOperationResultEnum {
	USER_REGISTERED_SUCCESSFULLY("User registered successfully"),
	USER_ALREADY_EXIST("User with such login already exists"),
	USER_REGISTER_ATTEMPT_FAILED_DUE_TO_ERROR("User not registered successfully due to error"),
	
	USER_LOGGED_IN_SUCCESSFULLY("User logged in successfully"),
	USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_WRONG_USERNAME_OR_PASSWORD("User log in attempt failed due to wrong username or password"),
	USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR("User log in attempt failed due to error"),

	POSSIBLE_AIRPORTS_FETCHED_SUCCESSFULLY("Possible airports fetched successfully"),
	POSSIBLE_AIRPORTS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR("Possible airports not fetched successfully due to error"),

	FLIGTS_FETCHED_SUCCESSFULLY("Flights fetched successfully"),
	FLIGTS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR("Flights not fetched successfully due to error"),

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



