package com.malinki.pz.bll.domain;

import org.json.JSONObject;

import com.malinki.pz.bll.constants.ParamNamesEnum;
import com.malinki.pz.dal.domain.Ticket;
import com.malinki.pz.dal.domain.User;

public class JSONTranslator {

	public User parseToUser(String requestBody) {
		JSONObject jsonObject = new JSONObject(requestBody);
		String login = jsonObject.getString(ParamNamesEnum.LOGIN.getName());
		String password = jsonObject.getString(ParamNamesEnum.PASSWORD.getName());

		return new User(login, password);
	}

	public Ticket parseToTicket(String requestBody) {
		JSONObject jsonObject = new JSONObject(requestBody);
		String name = jsonObject.getString(ParamNamesEnum.NAME.getName());
		String surname = jsonObject.getString(ParamNamesEnum.SURNAME.getName());
		String email = jsonObject.getString(ParamNamesEnum.EMAIL.getName());
		String nrIDCard = jsonObject.getString(ParamNamesEnum.NRIDCARD.getName());
		int classTravel = jsonObject.getInt(ParamNamesEnum.CLASSTRAVEL.getName());
		double price = jsonObject.getDouble(ParamNamesEnum.PRICE.getName());
		String sourceAirport = jsonObject.getString(ParamNamesEnum.SOURCEAIRPORT.getName());
		String destinyAirport = jsonObject.getString(ParamNamesEnum.DESTINYAIRPORT.getName());
		String flyDate = jsonObject.getString(ParamNamesEnum.FLYDATE.getName());
		String airlineName = jsonObject.getString(ParamNamesEnum.AIRLINENAME.getName());
		return new Ticket(name, surname, nrIDCard, email, classTravel, price, sourceAirport, destinyAirport, flyDate,
				airlineName);
	}
}
