package com.malinki.pz.bll;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malinki.pz.dal.UserRepository;

@Service
public class UserOperations implements IUserRepository {
	
	@Autowired
	public UserRepository userRepository;
			
	@Override
	public void registerUser(HttpServletResponse response, UserUVM user) {	
		userRepository.registerUser(response, UserConverter.fromUserUVMToUserDTO(user));
	}

	@Override
	public void loginUser(HttpServletResponse response, UserUVM user) {
		userRepository.loginUser(response, UserConverter.fromUserUVMToUserDTO(user));
	}
}
