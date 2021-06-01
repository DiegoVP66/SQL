package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dataB.DataBConnection;

public class Program {

	public static void main(String[] args) {
		
		
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		// Retrieving data from the database
		try {
			connect = DataBConnection.getConnection();
			
			statement = connect.createStatement();
                                             
			resultSet = statement.executeQuery("select * from seller");
			
			while(resultSet.next()) {
				
				System.out.println(resultSet.getInt("Id")+ " - "
						+ resultSet.getString("Name")
						+", "
						+resultSet.getString("Email"));
			}
			
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DataBConnection.closeConnection();
		}

	}

}
