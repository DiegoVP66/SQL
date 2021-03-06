package model.dao;

import dataB.DataBConnection;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class GenerateDao {
	// returns  Interface SellerDao
	public static SellerDao generateSellerDao() {
		// protects the implementation without exposing it
		return new SellerDaoJDBC(DataBConnection.getConnection());
	}
	
	public static DepartmentDao generateDepartmentDao(){
		return new DepartmentDaoJDBC(DataBConnection.getConnection());
	}
}
