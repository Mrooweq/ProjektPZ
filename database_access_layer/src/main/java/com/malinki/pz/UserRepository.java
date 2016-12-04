package com.malinki.pz;

import javax.servlet.http.HttpServletResponse;

public class UserRepository {

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
