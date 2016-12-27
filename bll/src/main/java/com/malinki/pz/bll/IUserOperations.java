package com.malinki.pz.bll;

import com.malinki.pz.lib.UserResponse;
import com.malinki.pz.lib.UserUVM;

public interface IUserOperations {
	public int registerUser(UserUVM user);
	public UserResponse loginUser(UserUVM user);
	public void logoutUser(UserUVM user);
}
