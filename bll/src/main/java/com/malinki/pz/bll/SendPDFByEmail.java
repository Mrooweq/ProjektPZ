package com.malinki.pz.bll;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.malinki.pz.lib.TicketResponseUVM;
import org.springframework.beans.factory.annotation.Autowired;

public class SendPDFByEmail {

	@Autowired
	private EmailAndPdfService emailAndPdfService;

	public PDFResponse generareAndSendEmail(TicketResponseUVM ticketResponseUVM) throws Exception {
		ByteArrayOutputStream outputStream = emailAndPdfService.getOutputStream(ticketResponseUVM);
		MimeBodyPart pdfBodyPart = emailAndPdfService.generatePdf(ticketResponseUVM, outputStream);
		MimeMessage message = emailAndPdfService.generateEmail(pdfBodyPart);
		emailAndPdfService.sendEmail(message);

		return emailAndPdfService.generateResponse(outputStream);
	}
}