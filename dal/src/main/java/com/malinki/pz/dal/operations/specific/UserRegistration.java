package com.malinki.pz.dal.operations.specific;

import com.malinki.pz.dal.operations.DatabaseComplexResponseOperation;
import com.malinki.pz.dal.mappers.UserMapper;
import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import com.malinki.pz.lib.entity.UserDTO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public class UserRegistration extends DatabaseComplexResponseOperation {

	private UserDTO user;
	private final Logger logger = Logger.getLogger(UserRegistration.class);

	private boolean isUserPositivelyValidated;
	private boolean isLoginAlreadyUsed;
	private boolean isEmailAlreadyUsed;

	public UserRegistration(UserDTO user) {
		super(UserMapper.class);
		this.user = user;
	}

	@Override
	protected MalinkiComplexResponse mainAction() {
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
				((UserMapper)mapper).registerUser(user);
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

		return new MalinkiComplexResponse();
	}

	private void validateUser(UserDTO user) {
		isLoginAlreadyUsed = getBoolean(((UserMapper)mapper).isLoginAlreadyUsed(user.getUsername()));
		isEmailAlreadyUsed = getBoolean(((UserMapper)mapper).isEmailAlreadyUsed(user.getEmail()));

		if(!isEmailAlreadyUsed && !isLoginAlreadyUsed)
			isUserPositivelyValidated = true;
		else
			isUserPositivelyValidated = false;
	}
}
