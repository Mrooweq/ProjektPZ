package com.malinki.pz.dal;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.dal.domain.UserDTO;
import com.malinki.pz.dal.operations.UserRegistration;

public class UserRepository {

	public void registerUser(HttpServletResponse response, UserDTO user) {
		UserRegistration ur = new UserRegistration(response, user);
		ur.performAction();
	}
	
	public void loginUser(HttpServletResponse response, UserDTO user) {

	}
}
