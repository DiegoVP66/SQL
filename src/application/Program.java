package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		// testing the Seller class and the Department class in the main program
		Seller seller = new Seller(1, "Diego Vicente Pereira", "vp.diego28@gmail.com", sdf.parse("16/08/1988"), 2000.00,
				new Department(5, "Tec"));

		System.out.println(seller);
	}

}
