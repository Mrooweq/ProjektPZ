package com.malinki.pz.bll;

import com.malinki.pz.lib.MalinkiComplexResponse;
import com.malinki.pz.lib.UserDTO;
import com.malinki.pz.lib.UserUVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malinki.pz.dal.UserRepository;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserOperations implements IUserOperations {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionTable sessionTable;

	@Override
	public int registerUser(UserUVM user) {
		UserValidation validation = new UserValidation(user);
		MalinkiComplexResponse userResponse = new MalinkiComplexResponse();
		if(validation.checkUser()) {
			userResponse = userRepository.registerUser(UserConverter.fromUserUVMToUserDTO(user));
		}
		return userResponse.getResult();
	}

	@Override
	public MalinkiComplexResponse loginUser(UserUVM userForLoginValidation) {
		MalinkiComplexResponse userResponse = userRepository.loginUser(UserConverter.fromUserUVMToUserDTO(userForLoginValidation));

		UserUVM loggedUserUVM = UserConverter.fromUserDTOToUserUVM((UserDTO) userResponse.getDtoResult());
		userResponse.setUvmResult(loggedUserUVM);

		if(userResponse.getResult() == HttpServletResponse.SC_OK)
			sessionTable.addUser(loggedUserUVM);

		return userResponse;
	}

	@Override
	public void logoutUser(UserUVM user) {
		sessionTable.deleteUserSession(user);
	}

	@Override
	public boolean validateUserByToken(String username, String token) {
		return sessionTable.validateUserByToken(username, token);
	}
}
