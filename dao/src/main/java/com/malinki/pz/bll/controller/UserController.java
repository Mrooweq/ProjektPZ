package com.malinki.pz.bll.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.malinki.pz.bll.domain.JSONTranslator;
import com.malinki.pz.dal.domain.Ticket;
import com.malinki.pz.dal.domain.User;
import com.malinki.pz.dao.UserRepository;
import com.malinki.pz.validators.DTOForm;

@RestController
@RequestMapping(value="/api")   
public class UserController {

	JSONTranslator translator = new JSONTranslator();
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@RequestBody String requestBody, HttpServletResponse response) {		
		User user = translator.parseToUser(requestBody);
		userRepository.registerUser(response, user);
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public void ticket(@RequestBody String requestBody, HttpServletResponse response, @Valid DTOForm form, BindingResult result) {		
		Ticket ticket = translator.parseToTicket(requestBody);
		userRepository.saveBoughtTicket(response, ticket);
	}
}
