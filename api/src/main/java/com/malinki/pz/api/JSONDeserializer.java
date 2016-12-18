package com.malinki.pz.api;

import org.json.JSONObject;

import com.malinki.pz.api.constants.ParamNamesEnum;
import com.malinki.pz.bll.UserUVM;

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
