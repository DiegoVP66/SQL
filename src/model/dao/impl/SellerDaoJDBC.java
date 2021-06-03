package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement st = null;
		try {
			connect = DataBConnection.getConnection();
			st = connect.prepareStatement(
					"INSERT INTO seller "
					+"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+"VALUES "
					+"(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartmentId().getDepId());
			
			int rows = st.executeUpdate();
			
			if(rows > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
					System.out.println("Rows affected: "+rows);
				}
				else {
					throw new DataBException("Error! No rows affected!");
				}
				DataBConnection.closeResultSet(rs);
			}
		}
		catch(SQLException e) {
			throw new DataBException(e.getMessage());
		}
		finally {
			DataBConnection.closeStatement(st);
		}
		
	}

	@Override
	public void update(Seller obj) {
		
		
	}

	@Override
	public void deleteById(Integer id) {
		
		
	}

	@Override
	// implementing the sql code in the findById method
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
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);
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
	
	// method for reuse
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartmentId(dep);
		return seller;
	}
	// method for reuse
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setDepId(rs.getInt("DepartmentId"));
		dep.setDepName(rs.getString("DepName"));
		
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			connect = DataBConnection.getConnection();
			st = connect.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
							+"FROM seller INNER JOIN department "
							+"ON seller.DepartmentId = department.Id "
							+"ORDER BY Name");
			
			
			rs = st.executeQuery();
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department>map = new HashMap<>();
			while(rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
				
			}
			return list;
		}
		catch(SQLException e) {
			throw new DataBException(e.getMessage());
		}
		finally {
			DataBConnection.closeResultSet(rs);
			DataBConnection.closeStatement(st);
		}
		
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			connect = DataBConnection.getConnection();
			st = connect.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE "
					+"DepartmentId = ? "
					+"ORDER BY Name");
			
			st.setInt(1, department.getDepId());
			
			rs = st.executeQuery();
			
			List<Seller> addDep = new ArrayList<Seller>();
			Map<Integer,Department> map = new HashMap<>();
			while(rs.next()) {
				
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				
				addDep.add(seller);
				
			}
			return addDep;
		}
		catch(SQLException e) {
			throw new DataBException(e.getMessage());
		}
		finally {
			DataBConnection.closeResultSet(rs);
			DataBConnection.closeStatement(st);
		}
	}

}
