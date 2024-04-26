package controller;
import enums.EmployeePosition;
import model.Employee;
import model.Admin;
import repository.FileType;
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
			if( emp.getPosition() != employeePosition) {
				return false;
			}
		} else if(employeePosition == EmployeePosition.ADMIN) {
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
	
	public static boolean changePassword(Employee emp, String password, String confirmPassword) {
		if(password.equals(confirmPassword)) {
			emp.setPassword(confirmPassword);
        	Repository.persistData(FileType.BRANCH);
        	Repository.persistData(FileType.EMPLOYEE);
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean changePassword(Admin admin, String password, String confirmPassword) {
		if(password.equals(confirmPassword)) {
			admin.setPassword(confirmPassword);
        	Repository.persistData(FileType.ADMIN);
			return true;
		}
		else {
			return false;
		}
	}
}	
