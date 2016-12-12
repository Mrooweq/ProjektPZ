package com.malinki.pz.dao;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malinki.pz.dal.Temp;
import com.malinki.pz.dal.domain.User;

@Service
public class UserRepository implements IUserRepository {
		
	@Autowired
	public Temp temp;
		
	@Override
	public void registerUser(HttpServletResponse response, User user) {
		temp.registerUser(response, user);
	}
}
