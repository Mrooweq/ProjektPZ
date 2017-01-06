package com.malinki.pz.dal.operations;

import com.malinki.pz.lib.UserDTO;
import com.malinki.pz.lib.UserResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.DatabaseUserOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public class UserRegistration extends DatabaseUserOperation {

	private UserDTO user;
	private Logger logger = Logger.getLogger(UserRegistration.class);

	private boolean isUserPositivelyValidated;
	private boolean isLoginAlreadyUsed;
	private boolean isEmailAlreadyUsed;

	public UserRegistration(UserDTO user) {
		this.user = user;
	}

	@Override
	protected UserResponse mainAction() {
		boolean hasErrorOccurred = false;

		try{
			validateUser(user);
		} catch (Exception e){
			logger.log(Level.ERROR, e.toString());
			hasErrorOccurred = true;
		}

		if(hasErrorOccurred)
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
		else if(isUserPositivelyValidated) {
			try{
				userMapper.registerUser(user);
				userMapper.commit();
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_REGISTERED_SUCCESSFULLY;
			} catch (Exception e){
				logger.log(Level.ERROR, e.toString());
				databaseOperationResultEnum = DatabaseOperationResultEnum.USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR;
			}
		}
		else if (isLoginAlreadyUsed)
			databaseOperationResultEnum = DatabaseOperationResultEnum.USERNAME_ALREADY_USED;
		else if (isEmailAlreadyUsed)
			databaseOperationResultEnum = DatabaseOperationResultEnum.EMAIL_ALREADY_USED;

		return new UserResponse();
	}

	private void validateUser(UserDTO user) {
		isLoginAlreadyUsed = getBoolean(userMapper.isLoginAlreadyUsed(user.getUsername()));
		isEmailAlreadyUsed = getBoolean(userMapper.isEmailAlreadyUsed(user.getEmail()));

		if(!isEmailAlreadyUsed && !isLoginAlreadyUsed)
			isUserPositivelyValidated = true;
		else
			isUserPositivelyValidated = false;
	}
}
