package com.malinki.pz.bll.domain;

import org.json.JSONObject;

import com.malinki.pz.bll.constants.ParamNamesEnum;
import com.malinki.pz.dal.domain.User;

public class JSONTranslator {
		
	public static User parseToUser(String requestBody) {
		JSONObject jsonObject = new JSONObject(requestBody);
		String login = jsonObject.getString(ParamNamesEnum.LOGIN.getName());		
		String password = jsonObject.getString(ParamNamesEnum.PASSWORD.getName());	
		
		return new User(login, password);
	}
}
