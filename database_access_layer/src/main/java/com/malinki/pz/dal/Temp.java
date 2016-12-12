package com.malinki.pz.dal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class Temp{

	public void registerUser(HttpServletResponse response, String login, String password) {
		new AbstractUserRepository() {
			@Override
			public void mainAction(Mapper mapper) {			
				mapper.addUser(login, password);
				mapper.commit();
			}
		}.performAction(response);
	}
}
