package com.malinki.pz.dal;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.dal.domain.UserDTO;
import com.malinki.pz.dal.operations.UserLogin;
import com.malinki.pz.dal.operations.UserRegistration;

public class UserRepository {

	public int registerUser(UserDTO user) {
		UserRegistration userRegistration = new UserRegistration(user);
		return userRegistration.performAction();
	}
	
	public int loginUser(UserDTO user) {
		UserLogin userLogin = new UserLogin(user);
		return userLogin.performAction();
	}
}
