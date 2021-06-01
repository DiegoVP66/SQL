package dataB;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import dbExceptions.DataBException;

public class DataBConnection {
	
	
	// method to read properties
	private static Properties loadDBProperties() {
		try(FileInputStream fileInput =  new FileInputStream("db.properties")){
			
			Properties properties = new Properties();
			
			properties.load(fileInput);
			
			return properties;
		}
		catch(IOException e) {
			throw new DataBException(e.getMessage());
		}
	}

}
