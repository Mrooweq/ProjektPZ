package com.malinki.pz.dal.repositories;

import com.malinki.pz.dal.operations.specific.UserLogin;
import com.malinki.pz.dal.operations.specific.UserRegistration;
import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.UserDTO;

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
