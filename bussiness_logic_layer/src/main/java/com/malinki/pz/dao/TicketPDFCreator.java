package com.malinki.pz.dao;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.barcodes.*;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.malinki.pz.dal.domain.Ticket;

public class TicketPDFCreator {
	Color grayColor;
	Ticket ticket;
	
	public TicketPDFCreator(Ticket ticket) {
		this.grayColor = new DeviceCmyk(0.f, 0.f, 0.f, 0.875f);
		this.ticket = ticket;
	}
	
	@SuppressWarnings("resource")
	public void generatePDF(HttpServletResponse response) throws IOException {
		ServletOutputStream out = response.getOutputStream();
		PdfWriter writer = new PdfWriter(out);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

		Image logo = new Image(ImageDataFactory.create("./graphics/logo.png"));
		
		//TODO get code of airline and ticket from database
		String code = "5912345678900";
		
		document.add(new Paragraph().setFixedPosition(40, 718, 1000).add(createBarcode(code, pdf).setWidth(200)));

		document.add(new Paragraph().setFixedPosition(355, 602, 200).add(logo.setWidth(200)));
		
		
		document = writeAirlineName(document, font);
		document = writeCustomerName(document, font);
		document = writeCustomerSurname(document, font);
		document = writeDocumentID(document, font);
		document = writeFlyDate(document, font);
		document = writeSourceAirport(document, font);
		document = writeDestinyAirport(document, font);
		document = writePriceAndClassTravel(document, font);

		document.close();
	}

	private Image  createBarcode(String code, PdfDocument pdf) {
		BarcodeEAN barcodeEAN = new BarcodeEAN(pdf);
		barcodeEAN.setCodeType(BarcodeEAN.EAN13);
		barcodeEAN.setCode(code);
		Image barcode = new Image(barcodeEAN.createFormXObject(null, null, pdf));
		return barcode;
	}

	private Document writeAirlineName(Document document, PdfFont font) {
		document.add(new Paragraph(ticket.getAirlineName()).setFont(font)
				.setFontSize(40)
				.setFixedPosition(40, 592, 220)
				.setItalic());
		
		return document;
	}

	private Document writeCustomerName(Document document, PdfFont font) {
		document.add(new Paragraph("Name:").setFont(font).setFontSize(15).setFixedPosition(40, 553.5f, 80)
				.setFontColor(grayColor));

		document.add(new Paragraph(ticket.getName()).setFont(font).setFontSize(20).setFixedPosition(120, 552, 170));

		return document;
	}

	private Document writeCustomerSurname(Document document, PdfFont font) {
		document.add(new Paragraph("Last name:").setFont(font).setFontSize(15).setFixedPosition(40, 533.5f, 80)
				.setFontColor(grayColor));

		document.add(new Paragraph(ticket.getLastName()).setFont(font).setFontSize(20).setFixedPosition(120, 532, 170));

		return document;
	}

	private Document writeDocumentID(Document document, PdfFont font) {
		document.add(new Paragraph("Document ID:").setFont(font).setFontSize(15).setFixedPosition(260, 553.5f, 100)
				.setFontColor(grayColor));

		document.add(new Paragraph(ticket.getNrIDCard()).setFont(font).setFontSize(20).setFixedPosition(360, 552, 340));

		return document;
	}

	private Document writeFlyDate(Document document, PdfFont font) {
		document.add(new Paragraph("Fly date:").setFont(font).setFontSize(15).setFixedPosition(260, 533.5f, 100)
				.setFontColor(grayColor));

		document.add(new Paragraph(ticket.getFlyDate()).setFont(font).setFontSize(20).setFixedPosition(360, 532, 340)
				.setBold());

		return document;
	}

	private Document writeSourceAirport(Document document, PdfFont font) {
		document.add(new Paragraph("From:").setFont(font).setFontSize(15).setFixedPosition(40, 482, 170)
				.setFontColor(grayColor));

		document.add(
				new Paragraph(ticket.getSuroceAirport()).setFont(font).setFontSize(20).setFixedPosition(60, 462, 495));

		return document;
	}

	private Document writeDestinyAirport(Document document, PdfFont font) {
		document.add(new Paragraph("To:").setFont(font).setFontSize(15).setFixedPosition(40, 442, 170)
				.setFontColor(grayColor));

		document.add(
				new Paragraph(ticket.getDestinyAirport()).setFont(font).setFontSize(20).setFixedPosition(60, 422, 495));

		return document;
	}

	private Document writePriceAndClassTravel(Document document, PdfFont font) {
		document.add(new Paragraph("Class:").setFont(font).setFontSize(15).setFixedPosition(40, 383.5f, 40)
				.setFontColor(grayColor));

		document.add(new Paragraph(String.valueOf(ticket.getClassTravel())).setFont(font).setFontSize(20)
				.setFixedPosition(80, 382, 40));

		document.add(new Paragraph("Price:").setFont(font).setFontSize(15).setFixedPosition(400, 383.5f, 40)
				.setFontColor(grayColor));

		document.add(new Paragraph(String.valueOf(ticket.getPrice()) + " PLN").setFont(font).setFontSize(20)
				.setFixedPosition(440, 382, 100));

		return document;
	}
	
}
