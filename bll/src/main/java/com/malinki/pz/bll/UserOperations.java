package com.malinki.pz.bll;

import com.malinki.pz.lib.UserResponse;
import com.malinki.pz.lib.UserDTO;
import com.malinki.pz.lib.UserUVM;
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
		UserResponse userResponse = userRepository.registerUser(UserConverter.fromUserUVMToUserDTO(user));
		return userResponse.getResult();
	}

	@Override
	public int loginUser(UserUVM user) {
		UserResponse userResponse = userRepository.loginUser(UserConverter.fromUserUVMToUserDTO(user));

		UserDTO loggedUser = userResponse.getUser();
		userContext.setCurrentUser(UserConverter.fromUserDTOToUserUVM(loggedUser));

		return userResponse.getResult();
	}
}
