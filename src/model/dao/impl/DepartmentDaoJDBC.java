package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataB.DataBConnection;
import dbExceptions.DataBException;
import dbExceptions.DataBIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private  Connection connect;
	
	public  DepartmentDaoJDBC(Connection connect) {
		this.connect = connect;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			connect = DataBConnection.getConnection();
			st = connect.prepareStatement(
					"INSERT INTO department (Name) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getDepName());
			int rows = st.executeUpdate();
			
			if(rows > 0) {
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setDepId(id);
				}
			}
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
	public void update(Department obj) {
		PreparedStatement st  = null;
		
		
		try {
			connect = DataBConnection.getConnection();
			st = connect.prepareStatement(
					"UPDATE department "
					+"SET Name = ?"
					+"WHERE Id = ?");
			
			st.setString(1, obj.getDepName());
			st.setInt(2, obj.getDepId());
			st.executeUpdate();
			
			
		}
		catch(SQLException e) {
			throw new DataBException(e.getMessage());
		}
		
		finally {
			DataBConnection.closeStatement(st);
		}
		
		
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = connect.prepareStatement(
				"DELETE FROM department WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DataBIntegrityException(e.getMessage());
		}
		finally {
			DataBConnection.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			connect = DataBConnection.getConnection();
			st = connect.prepareStatement("SELECT * FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = instatiateDepartment(rs);
				return dep;
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

	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setDepId(rs.getInt("Id"));
		dep.setDepName(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			connect = DataBConnection.getConnection();
			st = connect.prepareStatement(
					"SELECT * FROM department ORDER BY Name");
			
			rs =st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			
			while(rs.next()) {				
					Department dep = instatiateDepartment(rs);
					list.add(dep);
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

}
