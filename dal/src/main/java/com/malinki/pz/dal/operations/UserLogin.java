package com.malinki.pz.dal.operations;

import com.malinki.pz.lib.UserDTO;
import com.malinki.pz.lib.UserResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.DatabaseUserOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public class UserLogin extends DatabaseUserOperation {

	private UserDTO userForLoginValidation;
	private Logger logger = Logger.getLogger(UserLogin.class);

	public UserLogin(UserDTO user) {
		this.userForLoginValidation = user;
	}

	@Override
	protected UserResponse mainAction() {
		boolean isUsernameAndPasswordCorrect = false;
		boolean hasErrorOccurred = false;

		try{
			isUsernameAndPasswordCorrect = getBoolean(userMapper.isUsernameAndPasswordCorrect(userForLoginValidation));
		} catch (Exception e){
			logger.log(Level.ERROR, e.toString());
			hasErrorOccurred = true;
		}

		UserDTO user = null;

		if(hasErrorOccurred)
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
		else if(isUsernameAndPasswordCorrect){
			try{
				user = userMapper.getUserByUsername(userForLoginValidation.getUsername());
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOGGED_IN_SUCCESSFULLY;
			} catch (Exception e){
				logger.log(Level.ERROR, e.toString());
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
			}
		}
		else
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_WRONG_USERNAME_OR_PASSWORD;

		UserResponse userResponse = new UserResponse();
		userResponse.setUserDTO(user);

		return userResponse;
	}
}
