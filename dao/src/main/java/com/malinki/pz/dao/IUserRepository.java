package com.malinki.pz.dao;

import javax.servlet.http.HttpServletResponse;

public interface IUserRepository {
	public void registerUser(HttpServletResponse response, String login, String password);
}
