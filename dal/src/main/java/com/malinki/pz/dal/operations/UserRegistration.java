package com.malinki.pz.dal.operations;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.dal.DatabaseOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.dal.domain.UserDTO;

public class UserRegistration extends DatabaseOperation{

	private UserDTO user;
	
	public UserRegistration(HttpServletResponse response, UserDTO user) {
		super(response);
		this.user = user;
	}
	
	@Override
	protected void mainAction() {
		if(isUserPositivelyValidated(user)) {
			mapper.addUser(user.getLogin(), user.getPassword());
			mapper.commit();	
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_ADDED_PROPERLY;
		}
		else
			databaseOperationResultEnum = DatabaseOperationResultEnum.USER_ALREADY_EXIST;
	}
		
	private boolean isUserPositivelyValidated(UserDTO user) {
		boolean isLoginAlreadyUsed = parseDualToBoolean(mapper.isLoginAlreadyUsed(user.getLogin()));
		return !isLoginAlreadyUsed;
	}
	
	private boolean parseDualToBoolean(int dual){
		if(dual == 1)
			return true;
		else
			return false;
	}
}
