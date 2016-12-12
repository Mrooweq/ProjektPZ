package com.malinki.pz.dao;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.dal.Temp;


public class UserRepository implements IUserRepository {
		
	@Override
	public void registerUser(HttpServletResponse response, String login, String password) {
		Temp ur = new Temp();
		ur.registerUser(response, login, password);
	}
}
