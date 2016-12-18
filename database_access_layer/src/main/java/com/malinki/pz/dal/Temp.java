package com.malinki.pz.dal;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.dal.domain.Ticket;
import com.malinki.pz.dal.domain.User;

public class Temp {

	public void registerUser(HttpServletResponse response, User user) {
		AbstractUserRepository abstractUserRepository = new AbstractUserRepository() {
			@Override
			public void mainAction(Mapper mapper) {			
				mapper.addUser(user.getLogin(), user.getPassword());
				mapper.commit();
			}};
		abstractUserRepository.performAction(response);
	}

	public void saveBoughtTicket(HttpServletResponse response, Ticket ticket) {
		// TODO Auto-generated method stub
		
	}
}
