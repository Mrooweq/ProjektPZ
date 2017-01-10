package com.malinki.pz.dal.operations;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.malinki.pz.dal.DatabaseTicketOperation;
import com.malinki.pz.dal.constants.Strings;
import com.malinki.pz.lib.DataForPDFTicket;
import com.malinki.pz.lib.TicketUVM;

public class DataForTicketGetter {
	private Logger logger = Logger.getLogger(DatabaseTicketOperation.class);
	
	static String url = "jdbc:oracle:thin:testuser/testpass@localhost";
	static String username = "testuser";
	static String password = "testpass";
	private TicketUVM ticket;
	DataForPDFTicket dataForPDFTicket;
	
	
	public DataForTicketGetter(TicketUVM ticket) {
		this.ticket = ticket;
		this.dataForPDFTicket = new DataForPDFTicket();
	}

	public DataForPDFTicket getAllDataForPdfTicket() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url, username, password);
		
		String airlineID = getAirlineID(conn);
		
		getAirlineData(conn, airlineID);
		
		getUserData(conn, ticket.getUser());
		
		String sourceAirport = getSourceAirport(conn);
		
		getNameOfSourceAirport(conn, sourceAirport);
		
		String destinyAirport = getDestinyAirport(conn);
		
		getNameOfDestinyAirport(conn, destinyAirport);
		
		conn.close();
		
		return dataForPDFTicket;
	}

	private String getAirlineID(Connection conn) throws SQLException {
		String sql = "SELECT ID_Airline FROM Flight WHERE ID_Flight = " + ticket.getFlight();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery();
		} catch (Exception e) {
			logger.log(Level.INFO, Strings.FLIGHT_DATA_NOT_FOUND);
		}
		String name = resultSet.getString(1);
		dataForPDFTicket.setAirlineID(name);
		
		return name;
	}

	private void getAirlineData(Connection conn, String airlineID) throws Exception {
		String sql = "SELECT Name, Logo FROM Airline WHERE ID_Airline = " + airlineID;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery();
		} catch (Exception e) {
			logger.log(Level.INFO, Strings.AIRLINE_DATA_NOT_FOUND);
		}
		
		dataForPDFTicket.setAirlineName(resultSet.getString(1));
		
		InputStream is = resultSet.getBinaryStream(2);
		byte[] bytes = IOUtils.toByteArray(is);
		Image image = new Image(ImageDataFactory.create(bytes));
		dataForPDFTicket.setAirlineLogo(image);
	}

	private void getUserData(Connection conn, String user) throws SQLException {
		String sql = "SELECT Firstname, Lastname, Email FROM User WHERE ID_User = " + user;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery();
		} catch (Exception e) {
			logger.log(Level.INFO, Strings.USER_DATA_NOT_FOUND);
		}
		
		dataForPDFTicket.setFirstname(resultSet.getString(1));
		dataForPDFTicket.setLastName(resultSet.getString(2));
		dataForPDFTicket.setEmail(resultSet.getString(3));
		
	}

	private String getSourceAirport(Connection conn) throws SQLException {
		String sql = "SELECT From FROM Flight WHERE ID_Flight = " + ticket.getFlight();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery();
		} catch (Exception e) {
			logger.log(Level.INFO, Strings.AIRPORT_DATA_NOT_FOUND);
		}
		String name = resultSet.getString(1);
		return name;
	}

	private void getNameOfSourceAirport(Connection conn, String sourceAirport) throws SQLException {
		String sql = "SELECT Name FROM Airport WHERE ID_Airport = " + sourceAirport;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery();
		} catch (Exception e) {
			logger.log(Level.INFO, Strings.AIRLINE_DATA_NOT_FOUND);
		}
		
		dataForPDFTicket.setSourceAirport(resultSet.getString(1));
	}
	private String getDestinyAirport(Connection conn) throws SQLException {
		String sql = "SELECT To FROM Flight WHERE ID_Flight = " + ticket.getFlight();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery();
		} catch (Exception e) {
			logger.log(Level.INFO, Strings.AIRPORT_DATA_NOT_FOUND);
		}
		String name = resultSet.getString(1);
		return name;
	}

	private void getNameOfDestinyAirport(Connection conn, String destinyAirport) throws SQLException {
		String sql = "SELECT Name FROM Airport WHERE ID_Airport = " + destinyAirport;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery();
		} catch (Exception e) {
			logger.log(Level.INFO, Strings.AIRLINE_DATA_NOT_FOUND);
		}
		
		dataForPDFTicket.setDestinyAirport(resultSet.getString(1));
	}

	
}




















