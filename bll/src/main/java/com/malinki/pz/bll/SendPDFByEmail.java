package com.malinki.pz.bll;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.malinki.pz.lib.TicketResponseUVM;
import org.springframework.beans.factory.annotation.Autowired;

public class SendPDFByEmail {

	public PDFResponse generateAndSendEmail(TicketResponseUVM ticketResponseUVM) throws Exception {
		String receiverEmail = ticketResponseUVM.getEmail();
		EmailAndPdfService emailAndPdfService = new EmailAndPdfService();

		ByteArrayOutputStream outputStream = emailAndPdfService.getOutputStream(ticketResponseUVM);
		MimeBodyPart pdfBodyPart = emailAndPdfService.generatePdf(outputStream, receiverEmail);
		MimeMessage message = emailAndPdfService.generateEmail(pdfBodyPart, receiverEmail);
		emailAndPdfService.sendEmail(message);

		return emailAndPdfService.generateResponse(outputStream);
	}
}