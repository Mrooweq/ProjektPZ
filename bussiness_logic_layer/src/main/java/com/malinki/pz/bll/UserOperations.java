package com.malinki.pz.bll;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malinki.pz.dal.UserRepository;
import com.malinki.pz.dal.domain.UserDTO;

@Service
public class UserOperations implements IUserRepository {
		
	@Autowired
	public UserRepository userRepository;
		
	@Override
	public void registerUser(HttpServletResponse response, UserDTO user) {
		userRepository.registerUser(response, user);
	}
}
