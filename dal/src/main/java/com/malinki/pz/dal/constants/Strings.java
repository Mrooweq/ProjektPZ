package com.malinki.pz.dal.constants;

import java.util.HashMap;

public class Strings {
	public static final HashMap<String, String> airlineLogoPathMap = new HashMap<>();

	static {
		airlineLogoPathMap.put("Enter Air", "enter-air.jpg");
		airlineLogoPathMap.put("LOT", "LOT_Polish_Airlines.png");
		airlineLogoPathMap.put("Skytaxi", "Skytaxi.png");
		airlineLogoPathMap.put("Small Planet Airlines", "small-planet-airlines.jpg");
		airlineLogoPathMap.put("SprintAir", "sprintair.jpg");
	}

	public static final String MYBATIS_CONFIG_FILE_NAME = "mybatis-config.xml";

	public static final String NUMBER_OF_PLACES = "Number of Places:";
	public static final String PRICE_END = ".00 €";
	public static final String PRICE = "Price:";
	public static final String CLASS = "Class:";
	public static final String FROM = "From:";
	public static final String TO = "To:";
	public static final String DEPARTURE_DATE = "Departure date:";
	public static final String ARRIVAL_DATE = "Arrival date:";
	public static final String DOCUMENT_ID = "Document ID:";
	public static final String FIRSTNAME = "Firstname";
	public static final String LASTNAME = "Lastname";

	public static final String EMAIL_MESSAGE = "Hello!\nYou just buy ticket from MalinkiBooking. The ticket is attached in this generareAndSendEmail. Have a nice day!";
	public static final String EMAIL_SUBJECT = "Your ticket";
	public static final String SENDER_EMAIL_ID = "malinkibooking";
	public static final String SENDER_PASSWORD = "znaczek6598";
	public static final String EMAIL_SMTP_SERVER = "smtp.gmail.com";
	public static final String EMAIL_SERVER_PORT = "465";
	public static final String SENDER_EMAIL = "malinkibooking@gmail.com";
	public static final String ATTACHEMENT_FILE_NAME = "ticket.pdf";
	public static final String TEMP_FILE_NAME = "ticketNumber.pdf";

	public static final String USER_NOT_AUTHORIZED = "User not authorized";
}
