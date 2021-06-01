package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dataB.DataBConnection;

public class Program {

	public static void main(String[] args) {
	
		
		Connection connect = null;
		PreparedStatement statement = null;
		
		
		// updating database data
		try {
			connect = DataBConnection.getConnection();
			statement = connect.prepareStatement(
					"UPDATE seller "
					+"SET Email = ? "
					+"WHERE "
					+"(Id = ?)");
			
			statement.setString(1, "lucas@gmail.com");
			statement.setInt(2, 2);	
                                          
			int lines = statement.executeUpdate();
			
			// showing how many lines were affected
			System.out.println("UPDATED DATA, Lines affected: " +lines);
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			
			DataBConnection.closeStatement(statement);
			DataBConnection.closeConnection();
		}

	}

}
