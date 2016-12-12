package com.malinki.pz.dao;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.dal.UserRepository;



public class Temp {
	
	UserRepository ur;
	
	public void registerUser(HttpServletResponse response, String login, String password) {
		UserRepository ur = new UserRepository();
		ur.registerUser(response, login, password);
	}
}
