package com.malinki.pz.dao;

import org.json.JSONObject;

import com.malinki.pz.bll.UserUVM;
import com.malinki.pz.dao.constants.ParamNamesEnum;

public class JSONDeserializer {
		
	public static UserUVM parseToUserUVM(String requestBody) {
		JSONObject jsonObject = new JSONObject(requestBody);
		String login = jsonObject.getString(ParamNamesEnum.LOGIN.getName());		
		String password = jsonObject.getString(ParamNamesEnum.PASSWORD.getName());	
				
		return new UserUVM.UserUVMBuilder()
                .login(login)
                .password(password)
                .build();
	}
}
