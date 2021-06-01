package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class GenerateDao {
	// returns  Interface SellerDao
	public static SellerDao generateSellerDao() {
		// protects the implementation without exposing it
		return new SellerDaoJDBC();
	}
}
