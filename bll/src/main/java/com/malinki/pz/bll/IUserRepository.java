package com.malinki.pz.bll;

import javax.servlet.http.HttpServletResponse;

public interface IUserRepository {
	public void registerUser(HttpServletResponse response, UserUVM user);
	public void loginUser(HttpServletResponse response, UserUVM user);
}
