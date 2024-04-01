package controller;

import model.Employee;
import repository.Repository;

public class UserManager {
	public static boolean authenticate(String username, String password) {
		Employee emp = Repository.Employee.get(username);
		
		if (emp == null) {
			return false;
		}
		
		if (emp.getPassword() != password) {
			return false;
		}
		
		return true;
	}
}
