package controller;

import model.Employee;
import repository.Repository;

public class UserController {
	public static boolean authenticate(String username, String password) {
		Employee emp = Repository.EMPLOYEE.get(username);
		
		if (emp == null) {
			return false;
		}
		
		if (!emp.getPassword().equals(password)) {
			return false;
		}
		
		return true;
	}
}
