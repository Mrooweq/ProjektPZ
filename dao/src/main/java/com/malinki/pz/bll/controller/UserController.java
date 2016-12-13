package com.malinki.pz.bll.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.malinki.pz.bll.domain.JSONTranslator;
import com.malinki.pz.dal.domain.User;
import com.malinki.pz.dao.UserRepository;

@RestController
@RequestMapping(value="/api")   
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@RequestBody String requestBody, HttpServletResponse response) {		
		User user = JSONTranslator.parseToUser(requestBody);
		userRepository.registerUser(response, user);
	}
}
