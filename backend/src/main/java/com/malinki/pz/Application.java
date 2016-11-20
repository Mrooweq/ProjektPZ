package com.malinki.pz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final String CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String CONNECTION_ARGUMENT = "jdbc:oracle:thin:testuser/testpass@localhost";
	private static final String ADD_RECORD = "INSERT INTO person VALUES (4, '%s')";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Gosh, it's working!");

		try {
			Class.forName(CLASS_NAME);
		}
		catch (ClassNotFoundException e) {}
		
		addArgument("Cebula :p");
	}



	private static void addArgument(String arg)
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(CONNECTION_ARGUMENT);
			stmt = con.createStatement();
			rs = stmt.executeQuery(String.format(ADD_RECORD, arg));
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
}
