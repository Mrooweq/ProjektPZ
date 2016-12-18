package com.malinki.pz.dao.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.malinki.pz.bll.UserOperations;
import com.malinki.pz.bll.UserUVM;
import com.malinki.pz.dao.JSONDeserializer;

@RestController
@RequestMapping(value="/api")   
public class UserController {

	@Autowired
	private UserOperations userOperations;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@RequestBody String requestBody, HttpServletResponse response) {		
		UserUVM user = JSONDeserializer.parseToUserUVM(requestBody);
		userOperations.registerUser(response, user);
	}
}
