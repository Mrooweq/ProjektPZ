package com.malinki.pz.dao;

import org.json.JSONObject;

import com.malinki.pz.dal.domain.UserDTO;
import com.malinki.pz.dao.constants.ParamNamesEnum;

public class JSONDeserializer {
		
	public static UserDTO parseToUser(String requestBody) {
		JSONObject jsonObject = new JSONObject(requestBody);
		String login = jsonObject.getString(ParamNamesEnum.LOGIN.getName());		
		String password = jsonObject.getString(ParamNamesEnum.PASSWORD.getName());	
		
		return new UserDTO(login, password);
	}
}
