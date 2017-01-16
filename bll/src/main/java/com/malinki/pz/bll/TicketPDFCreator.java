package com.malinki.pz.bll;

import java.io.IOException;
import java.io.InputStream;

import com.itextpdf.barcodes.*;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.malinki.pz.lib.TicketResponseUVM;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.io.Resources;

public class TicketPDFCreator {
	private Color grayColor;
	private TicketResponseUVM ticketResponseUVM;

	public TicketPDFCreator(TicketResponseUVM ticketResponseUVM) {
		this.grayColor = new DeviceCmyk(0.f, 0.f, 0.f, 0.875f);
		this.ticketResponseUVM = ticketResponseUVM;
	}

	@SuppressWarnings("resource")
	public void generatePDF(ByteArrayOutputStream outputStream) throws IOException {
		ByteArrayOutputStream out = outputStream;
		PdfWriter writer = new PdfWriter(out);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

		InputStream is = Resources.getResourceAsStream("logo.png");
		byte[] bytes = IOUtils.toByteArray(is);
		Image logo = new Image(ImageDataFactory.create(bytes));

		document.add(new Paragraph().setFixedPosition(40, 718, 1000).add(createBarcode(pdf).setWidth(200)));
		document.add(new Paragraph().setFixedPosition(355, 602, 200).add(logo.setWidth(200)));

		document = writeAirlineName(document, font);
		document = writeCustomerName(document, font);
		document = writeCustomerSurname(document, font);
		document = writeDocumentID(document, font);
		document = writeDepartureDate(document, font);
		document = writeArrivalDate(document, font);
		document = writeSourceAirport(document, font);
		document = writeDestinyAirport(document, font);
		document = writePriceAndClassTravel(document, font);
		document = writeNumberOfPlaces(document, font);

		document.close();
	}

	private Image createBarcode(PdfDocument pdf) {
		String airlineCountryCode = "59";
		String ticketCode = String.valueOf(ticketResponseUVM.getFlightNumber());
		while(ticketCode.length()<5) {
			ticketCode += "0";
		}
		if(ticketCode.length()>5){
			ticketCode = ticketCode.substring(0, 5);
		}
		String airlineCode = String.valueOf(ticketResponseUVM.getFlightNumber());
		while(airlineCode.length()<5) {
			airlineCode += "0";
		}
		if(airlineCode.length()>5){
			airlineCode = airlineCode.substring(0, 5);
		}
		String codeWithoutChecksum = airlineCountryCode + airlineCode + ticketCode;
		String controlNumber = calculateControlNumberForBarcode(codeWithoutChecksum);
		String fullCode = codeWithoutChecksum + controlNumber;

		BarcodeEAN barcodeEAN = new BarcodeEAN(pdf);
		barcodeEAN.setCodeType(BarcodeEAN.EAN13);
		barcodeEAN.setCode(fullCode);
		Image barcode = new Image(barcodeEAN.createFormXObject(null, null, pdf));
		return barcode;
	}

	private String calculateControlNumberForBarcode(String codeWithoutChecksum) {
		int sum = 0;
		for (int i = 0; i < codeWithoutChecksum.length(); i++) {
			if (i % 2 == 0) {
				sum += codeWithoutChecksum.charAt(i);
			} else {
				sum += 3 * codeWithoutChecksum.charAt(i);
			}
		}
		sum%=10;
		return String.valueOf(sum);
	}

	private Document writeAirlineName(Document document, PdfFont font) {
		document.add(new Paragraph(ticketResponseUVM.getAirline()).setFont(font).setFontSize(40).setFixedPosition(40, 592, 220)
				.setItalic());

		return document;
	}

	private Document writeCustomerName(Document document, PdfFont font) {
		document.add(new Paragraph("Name:").setFont(font).setFontSize(15).setFixedPosition(40, 553.5f, 80)
				.setFontColor(grayColor));

		document.add(new Paragraph(ticketResponseUVM.getFirstname()).setFont(font).setFontSize(20).setFixedPosition(120, 552, 170));

		return document;
	}

	private Document writeCustomerSurname(Document document, PdfFont font) {
		document.add(new Paragraph("Last name:").setFont(font).setFontSize(15).setFixedPosition(40, 533.5f, 80)
				.setFontColor(grayColor));

		document.add(new Paragraph(ticketResponseUVM.getLastname()).setFont(font).setFontSize(20).setFixedPosition(120, 532, 170));

		return document;
	}

	private Document writeDocumentID(Document document, PdfFont font) {
		document.add(new Paragraph("Document ID:").setFont(font).setFontSize(15).setFixedPosition(260, 553.5f, 100)
				.setFontColor(grayColor));

		document.add(new Paragraph(String.valueOf(ticketResponseUVM.getId())).setFont(font).setFontSize(20).setFixedPosition(360, 552, 340));

		return document;
	}

	private Document writeDepartureDate(Document document, PdfFont font) {
		document.add(new Paragraph("Departure date:").setFont(font).setFontSize(15).setFixedPosition(260, 482, 100)
				.setFontColor(grayColor));

		document.add(new Paragraph(ticketResponseUVM.getDepartureDate()).setFont(font).setFontSize(20).setFixedPosition(360, 462, 340)
				.setBold());

		return document;
	}
	
	private Document writeArrivalDate(Document document, PdfFont font) {
		document.add(new Paragraph("Arrival date:").setFont(font).setFontSize(15).setFixedPosition(260, 442, 100)
				.setFontColor(grayColor));

		document.add(new Paragraph(ticketResponseUVM.getArrivalDate()).setFont(font).setFontSize(20).setFixedPosition(360, 422, 340)
				.setBold());

		return document;
	}

	private Document writeSourceAirport(Document document, PdfFont font) {
		document.add(new Paragraph("From:").setFont(font).setFontSize(15).setFixedPosition(40, 482, 170)
				.setFontColor(grayColor));

		document.add(
				new Paragraph(ticketResponseUVM.getFrom()).setFont(font).setFontSize(20).setFixedPosition(60, 462, 495));

		return document;
	}

	private Document writeDestinyAirport(Document document, PdfFont font) {
		document.add(new Paragraph("To:").setFont(font).setFontSize(15).setFixedPosition(40, 442, 170)
				.setFontColor(grayColor));

		document.add(
				new Paragraph(ticketResponseUVM.getTo()).setFont(font).setFontSize(20).setFixedPosition(60, 422, 495));

		return document;
	}

	private Document writePriceAndClassTravel(Document document, PdfFont font) {
		document.add(new Paragraph("Class:").setFont(font).setFontSize(15).setFixedPosition(40, 383.5f, 120)
				.setFontColor(grayColor));

		document.add(new Paragraph(ticketResponseUVM.getFlightClass()).setFont(font).setFontSize(20)
				.setFixedPosition(80, 382, 120));

		document.add(new Paragraph("Price:").setFont(font).setFontSize(15).setFixedPosition(400, 383.5f, 40)
				.setFontColor(grayColor));

		document.add(new Paragraph(String.valueOf(ticketResponseUVM.getPrice()) + ".00 PLN").setFont(font).setFontSize(20)
				.setFixedPosition(440, 382, 100));

		return document;
	}

	private Document writeNumberOfPlaces(Document document, PdfFont font) {
		document.add(new Paragraph("Number of Places:").setFont(font).setFontSize(15).setFixedPosition(240, 383.5f, 120)
				.setFontColor(grayColor));

		document.add(new Paragraph(String.valueOf(ticketResponseUVM.getNumberOfPlaces())).setFont(font).setFontSize(20)
				.setFixedPosition(360, 382, 40));
		
		return document;
	}
}
