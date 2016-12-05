package com.malinki.pz.bll.controller;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malinki.pz.bll.ApplicationContextProvider;
import com.malinki.pz.bll.constants.BeanNamesEnum;
import com.malinki.pz.bll.constants.ParamNamesEnum;
import com.malinki.pz.dal.UserRepository;



@RestController
@RequestMapping(value="/api")   
public class UserController {
		
	public UserRepository repository;
	
	@RequestMapping(value = "/login")
	public void login(@RequestBody String requestBody, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(requestBody);
		String login = jsonObject.getString(ParamNamesEnum.LOGIN.getName());		
		String password = jsonObject.getString(ParamNamesEnum.PASSWORD.getName());	

		repository = ApplicationContextProvider.getApplicationContext().getBean(BeanNamesEnum.USER_REPOSITORY.getName(), UserRepository.class);
		repository.registerUser(response, login, password);
	}
}
