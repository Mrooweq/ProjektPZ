package com.malinki.pz.dal;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.dal.domain.UserDTO;
import com.malinki.pz.dal.operations.UserLogin;
import com.malinki.pz.dal.operations.UserRegistration;

public class UserRepository {

	public boolean registerUser(HttpServletResponse response, UserDTO user) {
		UserRegistration userRegistration = new UserRegistration(response, user);
		return userRegistration.performAction();
	}
	
	public boolean loginUser(HttpServletResponse response, UserDTO user) {
		UserLogin userLogin = new UserLogin(response, user);
		return userLogin.performAction();
	}
}
