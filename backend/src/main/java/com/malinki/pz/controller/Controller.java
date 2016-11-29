package com.malinki.pz.controller;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malinki.pz.ApplicationContextProvider;
import com.malinki.pz.BeanNamesEnum;
import com.malinki.pz.UserRepository;


@RestController
@RequestMapping(value="/api")            
public class Controller {
		
	public UserRepository repository;
	
	@RequestMapping(value = "/login")
	public void login(@RequestBody String requestBody, HttpServletResponse response) {		
		JSONObject jsonObject = new JSONObject(requestBody);
		String login = jsonObject.getString("login");		
		String password = jsonObject.getString("password");	

		repository = ApplicationContextProvider.getApplicationContext().getBean(BeanNamesEnum.USER_REPOSITORY.getName(), UserRepository.class);
		repository.registerUser(response, login, password);
	}
}
