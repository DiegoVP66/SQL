package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dataB.DataBConnection;
import dbExceptions.DataBIntegrityException;

public class Program {

	public static void main(String[] args) {
	
		
		Connection connect = null;
		PreparedStatement statement = null;
		
		
		// deleting data
		try {
			connect = DataBConnection.getConnection();
			statement = connect.prepareStatement(
					"DELETE FROM seller "
					+"WHERE "
					+"Id = ?");
			
			statement.setInt(1, 10);	
                                          
			int lines = statement.executeUpdate();
			
			// showing how many lines were affected
			System.out.println("UPDATED DATA, Lines affected: " +lines);
			
			
		}
		catch(SQLException e) {
			throw new DataBIntegrityException(e.getMessage());
		}
		
		finally {
			
			DataBConnection.closeStatement(statement);
			DataBConnection.closeConnection();
		}

	}

}
