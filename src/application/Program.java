package application;

import java.sql.Connection;

import dataB.DataBConnection;

public class Program {

	public static void main(String[] args) {
		
		// testing database connection and disconnection
		Connection connect = DataBConnection.getConnection();
		DataBConnection.closeConnection();

	}

}
