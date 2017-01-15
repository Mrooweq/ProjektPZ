package com.malinki.pz.bll;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.dal.FlightRepository;
import com.malinki.pz.lib.TicketResponseUVM;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

public class SendPDFByEmail {

	@Autowired
	private EmailAndPdfService emailAndPdfService;

	private Logger logger = Logger.getLogger(SendPDFByEmail.class);

	public void sendEmail(TicketResponseUVM ticketResponseUVM, HttpServletResponse response) throws Exception {
		MimeBodyPart pdfBodyPart = emailAndPdfService.generatePdf(ticketResponseUVM, response);
		MimeMessage message = emailAndPdfService.generateEmail(pdfBodyPart);
		emailAndPdfService.sendEmail(message);
	}
}