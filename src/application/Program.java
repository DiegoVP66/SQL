package application;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Stream;

import dataB.DataBConnection;
import model.dao.GenerateDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {

		SellerDao sellerDao = GenerateDao.generateSellerDao();

		// testing findById method on main class
		System.out.println("findById");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller);
		
		System.out.println();
		
		// testing findByDepartment method on main class
		Department dep = new Department(1, null);
		List<Seller> list = sellerDao.findByDepertment(dep);
		System.out.println("findByDepartment");
		
		//Stream.of(list).forEach(System.out::println);
		
		for(Seller x : list) {
			System.out.println(x);
		}
		
		DataBConnection.closeConnection();

	}

}
