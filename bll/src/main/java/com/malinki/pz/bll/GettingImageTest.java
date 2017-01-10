package com.malinki.pz.bll;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.malinki.pz.dal.operations.DataForTicketGetter;

public class GettingImageTest {
	/*ImageGetter getter = new ImageGetter();
	File image = new File("./java.png");
    byte[] buffer = new byte[1];
    
	public void getImage() throws Exception{
		System.out.println(image.getPath());
		FileOutputStream fos = new FileOutputStream(image);
		InputStream is = getter.getImage(1).getPhoto().getBinaryStream();
		while (is.read(buffer) > 0) {
			fos.write(buffer);
		}
		fos.close();
	}*/
	
	static String url = "jdbc:oracle:thin:testuser/testpass@localhost";
	  static String username = "testuser";
	  static String password = "testpass";
	  public static void getImage() throws Exception {
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection conn = DriverManager.getConnection(url, username, password);

	    String sql = "SELECT Name, Logo FROM Airline ";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    ResultSet resultSet = stmt.executeQuery();
	    while (resultSet.next()) {
	      String name = resultSet.getString(1);
	      File image = new File("C:\\java.png");
	      FileOutputStream fos = new FileOutputStream(image);

	      byte[] buffer = new byte[1];
	      InputStream is = resultSet.getBinaryStream(2);
	      while (is.read(buffer) > 0) {
	        fos.write(buffer);
	      }
	      fos.close();
	    }
	    conn.close();
	  }
}
