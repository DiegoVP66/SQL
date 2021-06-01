package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dataB.DataBConnection;
import dbExceptions.DataBException;

public class Program {

	public static void main(String[] args) {
	
		
		Connection connect = null;
		Statement statement = null;
		
		
		
		try {
			connect = DataBConnection.getConnection();
			
			// protecting the transaction
			connect.setAutoCommit(false);
			
			statement = connect.createStatement();
				                              
			int lines = statement.executeUpdate("UPDATE seller SET BaseSalary = 4000 WHERE Id = 1");
			
			
			int lines1 = statement.executeUpdate("UPDATE seller SET BaseSalary = 6000 WHERE Id = 2");
			
			// showing how many lines were affected
			System.out.println("UPDATED DATA, Lines affected: " +lines);
			System.out.println("UPDATED DATA, Lines affected: " +lines1);
			
			// protecting the transaction
			connect.commit();
			
		}
		catch(SQLException e) {
			try {
				// if the transaction fails
				connect.rollback();
				throw new DataBException("Transaction failed! Error: "+e.getMessage());
			} catch (SQLException e1) {
				
				throw new DataBException("Error: "+e1.getMessage());
			}
		}
		
		finally {
			
			DataBConnection.closeStatement(statement);
			DataBConnection.closeConnection();
		}

	}

}
