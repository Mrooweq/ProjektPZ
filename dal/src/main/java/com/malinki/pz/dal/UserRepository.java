package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.UserLogin;
import com.malinki.pz.dal.operations.UserRegistration;
import com.malinki.pz.lib.UserResponse;
import com.malinki.pz.lib.UserDTO;

public class UserRepository {
	public UserResponse registerUser(UserDTO user) {
		UserRegistration userRegistration = new UserRegistration(user);
		return userRegistration.performAction();
	}

	public UserResponse loginUser(UserDTO userForLoginValidation) {
		UserLogin userLogin = new UserLogin(userForLoginValidation);
		return userLogin.performAction();
	}
}
