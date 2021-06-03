package application;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import dataB.DataBConnection;
import model.dao.GenerateDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc =  new Scanner(System.in);
		SellerDao sellerDao = GenerateDao.generateSellerDao();

		// testing findById method on main class
		System.out.println("findById");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller);
		
		System.out.println();
		
		// testing findByDepartment method on main class
		Department dep = new Department(1, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		System.out.println("findByDepartment");
		
		//Stream.of(list).forEach(System.out::println);
		
		for(Seller x : list) {
			System.out.println(x);
		}
		
		// testing findAll
		list = sellerDao.findAll();
		System.out.println("FindAll");
		for(Seller y : list) {
			System.out.println(y);
		}
		
		System.out.println();
		
		// testing insert
		/*System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Birth date: ");
		Date date = sdf.parse(sc.next());
		System.out.print("Base salary: ");
		Double salary = sc.nextDouble();
		
		seller = new Seller(null,name, email, date, salary, dep);
		sellerDao.insert(seller);
		
		System.out.println("id: "+seller.getId());*/
		
		// testing update
		//seller =  sellerDao.findById(16);
		//seller.setBaseSalary(4000.00);
		//sellerDao.update(seller);
		sellerDao.deleteById(17);
		
		
		DataBConnection.closeConnection();
		sc.close();

	}

}
