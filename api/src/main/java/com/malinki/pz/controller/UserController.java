package com.malinki.pz.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.malinki.pz.bll.*;
import com.malinki.pz.lib.UserResponse;
import com.malinki.pz.lib.UserUVM;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/api")
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserOperations userOperations;

	@Autowired
	private SessionTable sessionTable;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public SessionStorage login(@RequestBody String requestBody, HttpServletResponse response) {
		UserResponse userResponse = userOperations.loginUser(parseToUserUVM(requestBody));
		int result = userResponse.getResult();
		response.setStatus(result);

		if(result == HttpServletResponse.SC_OK)
			return sessionTable.getUserSession(userResponse.getUserUVM());
		else
			return null;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(@RequestBody String requestBody, HttpServletResponse response) {
		int result = userOperations.registerUser(parseToUserUVM(requestBody));
		response.setStatus(result);
	}

	public UserUVM parseToUserUVM(String requestBody) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		UserUVM user = null;

		try {
			user = mapper.readValue(requestBody, UserUVM.class);
		} catch (IOException e) {
			logger.log(Level.ERROR, e.toString());
		}

		return user;
	}
}
