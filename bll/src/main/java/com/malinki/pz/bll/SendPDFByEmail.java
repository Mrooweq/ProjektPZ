package com.malinki.pz.bll;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.malinki.pz.lib.TicketResponseUVM;
import com.malinki.pz.lib.TicketUVM;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class SendPDFByEmail {
	private Logger logger = Logger.getLogger(SendPDFByEmail.class);

	private String senderEmailID = "malinkibooking";
	private String senderPassword = "znaczek6598";
	private String emailSMTPserver = "smtp.gmail.com";
	private String emailServerPort = "465";
	private String emailSubject = "Your Ticket";
	private String emailBody = "Hello! \n You just buy ticket from MalinkiBooking. The ticket is attached in this sendEmail. Have a nice day!";
	private String senderEmail = "malinkibooking@gmail.com";
	private String receiverEmail;

	private TicketResponseUVM ticketResponseUVM;
	private HttpServletResponse response;

	public SendPDFByEmail(TicketResponseUVM ticketResponseUVM, HttpServletResponse response) {
		this.ticketResponseUVM = ticketResponseUVM;
		this.response = response;
	}

	public void sendEmail() throws Exception {
		Properties props = setConnectionSettings();

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmailID, senderPassword);
			}
		});

		ByteArrayOutputStream outputStream = null;

		try {
			// construct the text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(emailBody);

			// write the PDF content to the output stream
			outputStream = new ByteArrayOutputStream();
			TicketPDFCreator pdfCreator = new TicketPDFCreator(ticketResponseUVM);
			pdfCreator.generatePDF(outputStream);
			receiverEmail = pdfCreator.getDataForPDFTicket().getEmail();
			byte[] bytes = outputStream.toByteArray();

			// construct the pdf body part
			DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			// TODO get ticket number
			pdfBodyPart.setFileName("ticket.pdf");
			SendFileToFront fileToFront = new SendFileToFront(outputStream);

			fileToFront.doGet(response);

			FileOutputStream fos = new FileOutputStream(new File("ticketNumber.pdf"));
			try {
				outputStream.writeTo(fos);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				fos.close();
			}

			// construct the mime multi part of messege
			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(textBodyPart);
			mimeMultipart.addBodyPart(pdfBodyPart);

			// create message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
			message.setSubject(emailSubject);
			message.setContent(mimeMultipart);

			System.out.println("Sending");

			Transport.send(message);

			System.out.println("Done");

//			File file = new File("ticketNumber.pdf");
//			if(file.exists())
//				file.delete();

		} catch (MessagingException e) {
			logger.log(Level.ERROR, e.toString());
		}

	}

	private Properties setConnectionSettings() {
		Properties props = new Properties();
		props.put("mail.smtp.user", senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.debug", "true");
		props.put("mail.smtp.localhost", senderEmailID);
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.imap.connectiontimeout", "18000");
		props.put("mail.imap.timeout", "18000");
		return props;
	}
}