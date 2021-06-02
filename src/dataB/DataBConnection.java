package dataB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	// method to close the statement
	public static void closeStatement(Statement statement) {
		if(statement != null) {
			try {
				
				statement.close();
			}
			catch(SQLException e) {
				throw new DataBException(e.getMessage());
			}
		}
	}
	
	// method to close the resultSet
	public static void closeResultSet(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				
				resultSet.close();
			}
			catch(SQLException e) {
				throw new DataBException(e.getMessage());
			}
		}
	}

}
