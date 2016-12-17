package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseOperation;
import com.malinki.pz.dal.Mapper;
import com.malinki.pz.dal.domain.UserDTO;

public class UserRegistration extends DatabaseOperation{

	private UserDTO user;
	
	public UserRegistration(UserDTO user) {
		this.user = user;
	}
	
	@Override
	protected void mainAction(Mapper mapper) {
		mapper.addUser(user.getLogin(), user.getPassword());
		mapper.commit();
	}
}
