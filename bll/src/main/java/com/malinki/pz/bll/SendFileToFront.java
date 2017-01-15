package com.malinki.pz.bll;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.source.ByteArrayOutputStream;

public class SendFileToFront extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ByteArrayOutputStream arrayOutputStream;

	public SendFileToFront(ByteArrayOutputStream arrayOutputStream) {
		this.arrayOutputStream = arrayOutputStream;
	}

	public void doPost(HttpServletResponse response) {
		doGet(response);
	}

	public void doGet(HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			pw = response.getWriter();

			ByteArrayOutputStream ba = arrayOutputStream;

			// Converting byte[] to base64 string
			// NOTE: Always remember to encode your base 64 string in utf8
			// format other wise you may always get problems on browser.

			String pdfBase64String = org.apache.commons.codec.binary.StringUtils
					.newStringUtf8(org.apache.commons.codec.binary.Base64.encodeBase64(ba.toByteArray()));

			// wrting json response to browser

			pw.println("{");
			pw.println("\"successful\": true,");
			pw.println("\"pdf\": \"" + pdfBase64String + "\"");
			pw.println("}");
			return;
		} catch (Exception ex) {
			pw.println("{");
			pw.println("\"successful\": false,");
			pw.println("\"message\": \"" + ex.getMessage() + "\",");
			pw.println("}");
			return;
		}
	}
}
