package application;

import dataB.DataBConnection;
import model.dao.DepartmentDao;
import model.dao.GenerateDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {

		DepartmentDao depDao = GenerateDao.generateDepartmentDao();
		// findById
		Department dep = depDao.findById(1);
		System.out.println("Before Update");
		System.out.println(dep);
		
		System.out.println();
		
		// findAll
		//List<Department> list = depDao.findAll();
		
		/*for(Department x : list){
			System.out.println(x);
		}*/
		
		// insert
		//Department d = new Department(null, "Music");
		//depDao.insert(d);
		
		// Update
		dep.setDepName("Development");
		depDao.update(dep);
		System.out.println("After Update");
		System.out.println(dep);
		
		
		// delete
		depDao.deleteById(11);
		
		DataBConnection.closeConnection();
	}

}
