package com.malinki.pz.dal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.malinki.pz.dal.domain.UserDTO;
import com.malinki.pz.dal.operations.UserRegistration;

public class UserRepository {

	public void registerUser(HttpServletResponse response, UserDTO user) {
		UserRegistration ur = new UserRegistration(user);
		ur.performAction(response);
	}
}
