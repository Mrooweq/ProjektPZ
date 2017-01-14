package com.malinki.pz.dal.operations;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.malinki.pz.dal.DatabaseTicketOperation;
import com.malinki.pz.dal.constants.Strings;
import com.malinki.pz.lib.DataForPDFTicket;
import com.malinki.pz.lib.TicketUVM;

public class DataForTicketGetter {
	private Logger logger = Logger.getLogger(DatabaseTicketOperation.class);
	
	static String url = "jdbc:oracle:thin:testuser/testpass@localhost";
	static String username = "testuser";
	static String password = "testpass";
	private TicketUVM ticket;
	DataForPDFTicket dataForPDFTicket;
	
	
	public DataForTicketGetter(TicketUVM ticket) {
		this.ticket = ticket;
		this.dataForPDFTicket = new DataForPDFTicket();
	}

	public DataForPDFTicket getAllDataForPdfTicket() throws Exception {
		InputStream is = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\do_wywalenia\\logo.png");
		byte[] bytes = IOUtils.toByteArray(is);
		Image image = new Image(ImageDataFactory.create(bytes));

		DataForPDFTicket dataForPDFTicket = new DataForPDFTicket();
		dataForPDFTicket.setFirstname("Jan");
		dataForPDFTicket.setAirlineID("1");
		dataForPDFTicket.setAirlineName("LOT");
		dataForPDFTicket.setDestinyAirport("Geneva");

		dataForPDFTicket.setEmail("adamalfons95@gmail.com");

		dataForPDFTicket.setFlight("1000");
		dataForPDFTicket.setFlightDate("Data");
		dataForPDFTicket.setLastName("Kowalski");
		dataForPDFTicket.setNrIDCard("100");
		dataForPDFTicket.setSourceAirport("Warsaw");
		dataForPDFTicket.setPrice(199);
		dataForPDFTicket.setFlightClass("VIP");
		dataForPDFTicket.setAirlineLogo(image);

		return dataForPDFTicket;
	}
}




















