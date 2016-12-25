package com.malinki.pz.bll;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malinki.pz.dal.UserRepository;

@Service
public class UserOperations implements IUserRepository {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserContext userContext;
			
	@Override
	public int registerUser(UserUVM user) {
		return userRepository.registerUser(UserConverter.fromUserUVMToUserDTO(user));
	}

	@Override
	public int loginUser(UserUVM user) {
		int result = userRepository.loginUser(UserConverter.fromUserUVMToUserDTO(user));
		
		if(result == HttpServletResponse.SC_OK)
			userContext.setCurrentUser(user);

		return result;
	}
}
