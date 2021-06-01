package dataB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import dbExceptions.DataBException;

public class DataBConnection {

	private static Connection connect = null;

	// method to connect to database
	public static Connection getConnection() {
		if (connect == null) {
			try {
				Properties properties = loadDBProperties();

				String url = properties.getProperty("dburl");

				connect = DriverManager.getConnection(url, properties);
				
				System.out.println("connected");
				
			} catch (SQLException e) {
				throw new DataBException(e.getMessage());
			}
		}
		return connect;
	}
	
	//method to disconnect from the database
	public static void closeConnection() {
		if(connect != null) {
			try {
				System.out.println("disconnected");
				connect.close();
			}
			catch(SQLException e) {
				throw new DataBException(e.getMessage());
			}
		}
	}

	// method to read properties
	private static Properties loadDBProperties() {
		
		try (FileInputStream fileInput = new FileInputStream("db.properties")) {

			Properties properties = new Properties();

			properties.load(fileInput);

			return properties;
			
		} catch (IOException e) {
			throw new DataBException(e.getMessage());
		}
	}

}
