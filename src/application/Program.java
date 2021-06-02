package application;

import java.text.ParseException;

import model.dao.GenerateDao;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {

		SellerDao sellerDao = GenerateDao.generateSellerDao();

		// testing findById method on main class

		Seller seller = sellerDao.findById(1);

		System.out.println(seller);

	}

}
