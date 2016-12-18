package com.malinki.pz.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinki.pz.bll.UserOperations;
import com.malinki.pz.bll.UserUVM;

@RestController
@RequestMapping(value="/api")   
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserOperations userOperations;	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@RequestBody String requestBody, HttpServletResponse response) {		
		userOperations.registerUser(response, parseToUserUVM(requestBody));
	}
	
	public UserUVM parseToUserUVM(String requestBody) {			
		ObjectMapper mapper = new ObjectMapper();
		UserUVM user = null;
		
		try {
			user = mapper.readValue(requestBody, UserUVM.class);
		} catch (IOException e) {
			logger.log(Level.ERROR, e.toString());
			throw new RuntimeException();
		}
				
		return user;
	}
}
