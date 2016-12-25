package com.malinki.pz.bll;

import javax.servlet.http.HttpServletResponse;

public interface IUserRepository {
	public int registerUser(UserUVM user);
	public int loginUser(UserUVM user);
}
