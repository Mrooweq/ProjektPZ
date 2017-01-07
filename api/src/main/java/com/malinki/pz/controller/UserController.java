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

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public SessionStorage login(@RequestBody String requestBody, HttpServletResponse response) {
		return userService.login(requestBody, response);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(@RequestBody String requestBody, HttpServletResponse response) {
		userService.register(requestBody, response);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(@RequestBody String requestBody) {
		userService.logout(requestBody);
	}
}
