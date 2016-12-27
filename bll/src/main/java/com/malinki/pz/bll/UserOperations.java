package com.malinki.pz.bll;

import com.malinki.pz.lib.UserResponse;
import com.malinki.pz.lib.UserUVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malinki.pz.dal.UserRepository;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserOperations implements IUserRepository {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionTable sessionTable;

	@Override
	public int registerUser(UserUVM user) {
		UserValidation validation = new UserValidation(user);
		UserResponse userResponse = new UserResponse();
		if(validation.checkUser()) {
			userResponse = userRepository.registerUser(UserConverter.fromUserUVMToUserDTO(user));
		}
		return userResponse.getResult();
	}

	@Override
	public UserResponse loginUser(UserUVM userForLoginValidation) {
		UserResponse userResponse = userRepository.loginUser(UserConverter.fromUserUVMToUserDTO(userForLoginValidation));

		UserUVM loggedUserUVM = UserConverter.fromUserDTOToUserUVM(userResponse.getUserDTO());
		userResponse.setUserUVM(loggedUserUVM);

		if(userResponse.getResult() == HttpServletResponse.SC_OK)
			sessionTable.addUser(loggedUserUVM);

		return userResponse;
	}
}
