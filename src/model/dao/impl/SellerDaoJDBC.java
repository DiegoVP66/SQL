package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dataB.DataBConnection;
import dbExceptions.DataBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private  Connection connect;
	
	// creating dependency between connection and class
	public  SellerDaoJDBC(Connection connect) {
		this.connect = connect;
	}

	@Override
	public void insert(Seller obj) {
		
		
	}

	@Override
	public void update(Seller obj) {
		
		
	}

	@Override
	public void deleteById(Integer id) {
		
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			connect = DataBConnection.getConnection();
			st = connect.prepareStatement(
					 "SELECT seller.*,department.Name as DepName "
					 + "FROM seller INNER JOIN department "
					 + "ON seller.DepartmentId = department.Id "
					 + "WHERE seller.id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			if(rs.next()) {
				// binding the obj department to the database department
				Department dep = new Department();
				dep.setDepId(rs.getInt("DepartmentId"));
				dep.setDepName(rs.getString("DepName"));
				
				// binding the obj seller to the database seller
				Seller seller = new Seller();
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				// binding the obj seller to the obj department 
				seller.setDepartmentId(dep);
				return seller;
			}
		}
		catch(SQLException e) {
			throw new DataBException(e.getMessage());
		}
		finally {
			DataBConnection.closeResultSet(rs);
			DataBConnection.closeStatement(st);
		}
		
		
		return null;
	}

	@Override
	public List<Seller> findAll() {
		
		return null;
	}

}
