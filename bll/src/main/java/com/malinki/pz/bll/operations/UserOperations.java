package com.malinki.pz.bll.operations;

import com.malinki.pz.bll.operations.interfaces.IUserOperations;
import com.malinki.pz.bll.validation.SessionTable;
import com.malinki.pz.bll.validation.UserValidation;
import com.malinki.pz.bll.converters.UserConverter;
import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.UserDTO;
import com.malinki.pz.lib.entity.UserUVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malinki.pz.dal.repositories.UserRepository;

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

		boolean isUserPositivelyValidated = validation.validateUser();

		if(isUserPositivelyValidated)
			userResponse = userRepository.registerUser(UserConverter.fromUserUVMToUserDTO(user));

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
