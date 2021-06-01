package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dataB.DataBConnection;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection connect = null;
		PreparedStatement statement = null;
		
		
		// inserting data into the database
		try {
			connect = DataBConnection.getConnection();
			statement = connect.prepareStatement(
					"INSERT INTO seller "
					+"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+"VALUES "
					+"(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, "Diego Vicente Pereira");
			statement.setString(2, "vp.diego28@gmail.com");
			statement.setDate(3, new java.sql.Date(sdf.parse("17/06/1988").getTime()));
			statement.setDouble(4, 5000.00);
			statement.setInt(5, 2);
			
			int lines = statement.executeUpdate();
			
			// showing the created key
			if(lines > 0) {
				ResultSet resultSet = statement.getGeneratedKeys();
				while(resultSet.next()) {
					int id = resultSet.getInt(1);
					System.out.println("Id created: "+id);
				}
			}
			else {
				System.out.println("Nothing changed!");
			}
			
                                          
			
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			
			DataBConnection.closeStatement(statement);
			DataBConnection.closeConnection();
		}

	}

}
