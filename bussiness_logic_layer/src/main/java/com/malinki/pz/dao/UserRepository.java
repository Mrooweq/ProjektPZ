package com.malinki.pz.dao;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malinki.pz.dal.Temp;
import com.malinki.pz.dal.domain.Ticket;
import com.malinki.pz.dal.domain.User;

@Service
public class UserRepository implements IUserRepository {
		
	@Autowired
	public Temp temp;
		
	@Override
	public void registerUser(HttpServletResponse response, User user) {
		temp.registerUser(response, user);
	}
	
	@Override
	public void saveBoughtTicket(HttpServletResponse response, Ticket ticket) {
		TicketPDFCreator creator = new TicketPDFCreator();
		try {
			creator.generatePDF(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp.saveBoughtTicket(response, ticket);
	}
}
