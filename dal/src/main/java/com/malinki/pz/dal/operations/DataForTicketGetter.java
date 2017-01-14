package com.malinki.pz.dal.operations;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.malinki.pz.dal.DatabaseTicketOperation;
import com.malinki.pz.lib.DataForPDFTicket;
import com.malinki.pz.lib.TicketUVM;

public class DataForTicketGetter {
	private Logger logger = Logger.getLogger(DatabaseTicketOperation.class);

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
		dataForPDFTicket.setFirstname("San");
		dataForPDFTicket.setLastName("Escobar");
		dataForPDFTicket.setTicketID("1");
		dataForPDFTicket.setAirlineName("British Airways");
		dataForPDFTicket.setSourceAirport("Warsaw");
		dataForPDFTicket.setDestinyAirport("Geneva");

		dataForPDFTicket.setEmail("adamalfons95@gmail.com");

		dataForPDFTicket.setFlight("1000");
		dataForPDFTicket.setFlightDate("1999-01-01");
		dataForPDFTicket.setNrIDCard("777");
		dataForPDFTicket.setPrice("199");
		dataForPDFTicket.setFlightClass("VIP");
		dataForPDFTicket.setAirlineLogo(image);

		return dataForPDFTicket;
	}
}




















