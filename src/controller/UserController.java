package controller;
import enums.EmployeePosition;
import model.Employee;
import model.Admin;
import repository.Repository;

public class UserController {
	public static boolean authenticate(String username, String password, EmployeePosition employeePosition) {
		if(employeePosition == EmployeePosition.STAFF || employeePosition == EmployeePosition.MANAGER) {
			Employee emp = Repository.EMPLOYEE.get(username);
			if (emp == null) {
				return false;
			}
			if (!emp.getPassword().equals(password)) {
				return false;
			}
		}else if(employeePosition == EmployeePosition.ADMIN) {
			Admin admin = Repository.ADMIN.get(username);
			if(admin == null) {
				return false;
			}
			if(!admin.getPassword().equals(password)) {
				return false;
			}
		}
		return true;
	}
	
}	
