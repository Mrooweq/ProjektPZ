package com.malinki.pz.bll;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.malinki.pz.lib.TicketResponseUVM;
import org.springframework.beans.factory.annotation.Autowired;

public class PDFGenerator {

	public PDFResponse generatePDF(TicketResponseUVM ticketResponseUVM, boolean ifSend) throws Exception {
		String receiverEmail = ticketResponseUVM.getEmail();
		EmailAndPdfService emailAndPdfService = new EmailAndPdfService();

		ByteArrayOutputStream outputStream = emailAndPdfService.getOutputStream(ticketResponseUVM);
		emailAndPdfService.createTempFile(outputStream);

		if(ifSend){
			MimeBodyPart pdfBodyPart = emailAndPdfService.generatePdf(outputStream);
			MimeMessage message = emailAndPdfService.generateEmail(pdfBodyPart, receiverEmail);
			emailAndPdfService.sendEmail(message);
		}

		outputStream.close();
//		emailAndPdfService.deleteTempFile();

		return emailAndPdfService.generateResponse(outputStream);
	}
}