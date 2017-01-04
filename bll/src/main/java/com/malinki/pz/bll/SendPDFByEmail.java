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

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.malinki.pz.lib.TicketUVM;



public class SendPDFByEmail {
	
	final String senderEmailID = "malinkibooking";
	final String senderPassword = "znaczek6598";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	String senderEmail = "malinkibooking@gmail.com";
	String receiverEmailID = "sajgon338@gmail.com";
	String emailSubject = "Test Mail";
	String emailBody = ":)";
	TicketUVM ticket;

	public SendPDFByEmail(TicketUVM ticketUVM) {
		this.ticket = ticketUVM;
	}

	public void email() throws Exception {
		
		Properties props = setConnectionSettings();

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmailID, senderPassword);
			}
		});

		ByteArrayOutputStream outputStream = null;

		try {
			//construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(emailBody);
             
            //write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            TicketPDFCreator pdfCreator = new TicketPDFCreator(ticket);
            pdfCreator.generatePDF(outputStream);
            byte[] bytes = outputStream.toByteArray();
             
            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            //TODO get ticket number
            pdfBodyPart.setFileName("ticketNumber.pdf");
            FileOutputStream fos = new FileOutputStream (new File("ticketNumber.pdf")); 
            try {
                outputStream.writeTo(fos);
            } catch(IOException ioe) {
                // Handle exception here
                ioe.printStackTrace();
            } finally {
                fos.close();
            }
                         
            //construct the mime multi part of messege
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);
            
            //create message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmailID));
			message.setSubject(emailSubject);
			message.setContent(mimeMultipart);

			System.out.println("Sending");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
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