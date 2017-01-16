package com.malinki.pz.bll;


import com.itextpdf.io.source.ByteArrayOutputStream;
import com.malinki.pz.dal.constants.Strings;
import com.malinki.pz.lib.TicketResponseUVM;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

public class EmailAndPdfService {
    private Logger logger = Logger.getLogger(EmailAndPdfService.class);

    private String senderEmailID = "malinkibooking";
    private String senderPassword = "znaczek6598";
    private String emailSMTPserver = "smtp.gmail.com";
    private String emailServerPort = "465";
    private String emailSubject = "Your Ticket";
    private String emailBody = "Hello! \n You just buy ticket from MalinkiBooking. The ticket is attached in this generareAndSendEmail. Have a nice day!";
    private String senderEmail = "malinkibooking@gmail.com";

    private String attachmentFileName = "ticket.pdf";
    private String tempFileName = "ticketNumber.pdf";

    private String receiverEmail;

    public MimeMessage generateEmail(MimeBodyPart pdfBodyPart){
        Properties props = setConnectionSettings();

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmailID, senderPassword);
            }
        });

        MimeBodyPart textBodyPart = new MimeBodyPart();
        try {
            textBodyPart.setText(emailBody);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(senderEmail));
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        try {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        try {
            message.setSubject(emailSubject);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        MimeMultipart mimeMultipart = new MimeMultipart();
        try {
            mimeMultipart.addBodyPart(textBodyPart);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        try {
            mimeMultipart.addBodyPart(pdfBodyPart);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        try {
            message.setContent(mimeMultipart);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        return message;
    }

    public ByteArrayOutputStream getOutputStream(TicketResponseUVM ticketResponseUVM){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TicketPDFCreator pdfCreator = new TicketPDFCreator(ticketResponseUVM);

        try {
            pdfCreator.generatePDF(outputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        return outputStream;
    }

    public PDFResponse generateResponse(ByteArrayOutputStream outputStream){
        String pdfBase64String = null;
        PDFResponse pdfResponse = new PDFResponse();

        try {
            pdfBase64String = StringUtils.newStringUtf8(Base64.encodeBase64(outputStream.toByteArray()));
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        pdfResponse.setPdf(pdfBase64String);

        return pdfResponse;
    }

    public MimeBodyPart generatePdf(TicketResponseUVM ticketResponseUVM, ByteArrayOutputStream outputStream){
        receiverEmail = ticketResponseUVM.getEmail();
        byte[] bytes = outputStream.toByteArray();

        DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
        MimeBodyPart pdfBodyPart = new MimeBodyPart();

        try {
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        try {
            pdfBodyPart.setFileName(attachmentFileName);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(new File(tempFileName));
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        try {
            outputStream.writeTo(fos);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }

        return pdfBodyPart;
    }

    public void sendEmail(MimeMessage message){
        logger.log(Level.INFO, "Sending");

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        finally {
//            deleteTempFile();
        }

        logger.log(Level.INFO, "Done");
    }

    private void deleteTempFile(){
        File file = new File(tempFileName);
        if(file.exists())
            file.delete();
    }

    public Properties setConnectionSettings() {
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
