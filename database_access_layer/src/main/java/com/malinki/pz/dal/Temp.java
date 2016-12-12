package com.malinki.pz.dal;

import javax.servlet.http.HttpServletResponse;

public class Temp {

	public void registerUser(HttpServletResponse response, String login, String password) {
		AbstractUserRepository abstractUserRepository = new AbstractUserRepository() {
			@Override
			public void mainAction(Mapper mapper) {			
				mapper.addUser(login, password);
				mapper.commit();
			}};
		abstractUserRepository.performAction(response);
	}
}
