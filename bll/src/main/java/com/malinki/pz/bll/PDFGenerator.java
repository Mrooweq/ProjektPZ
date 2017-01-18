package com.malinki.pz.bll;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.malinki.pz.bll.services.EmailAndPdfService;
import com.malinki.pz.lib.entity.TicketResponseUVM;

public class PDFGenerator {

	public PDFResponse generatePDF(TicketResponseUVM ticketResponseUVM, boolean ifSend) throws Exception {
		String receiverEmail = ticketResponseUVM.getEmail();
		EmailAndPdfService emailAndPdfService = new EmailAndPdfService();

		ByteArrayOutputStream outputStream = emailAndPdfService.getOutputStream(ticketResponseUVM);

		if(ifSend){
			MimeBodyPart pdfBodyPart = emailAndPdfService.generatePdf(outputStream);
			MimeMessage message = emailAndPdfService.generateEmail(pdfBodyPart, receiverEmail);
			emailAndPdfService.sendEmail(message);
		}

		emailAndPdfService.createTempFile(outputStream);
		//		emailAndPdfService.deleteTempFile();
		outputStream.close();

		return emailAndPdfService.generateResponse(outputStream);
	}
}