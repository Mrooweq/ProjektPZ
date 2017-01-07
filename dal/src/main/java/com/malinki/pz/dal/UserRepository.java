package com.malinki.pz.dal;

import com.malinki.pz.dal.operations.UserLogin;
import com.malinki.pz.dal.operations.UserRegistration;
import com.malinki.pz.lib.MalinkiComplexResponse;
import com.malinki.pz.lib.UserDTO;

public class UserRepository {
	public MalinkiComplexResponse registerUser(UserDTO user) {
		UserRegistration userRegistration = new UserRegistration(user);
		return userRegistration.performAction();
	}

	public MalinkiComplexResponse loginUser(UserDTO userForLoginValidation) {
		UserLogin userLogin = new UserLogin(userForLoginValidation);
		return userLogin.performAction();
	}
}
