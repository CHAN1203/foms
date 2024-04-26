package controller;
import enums.EmployeePosition;
import model.Employee;
import model.Admin;
import repository.FileType;
import repository.Repository;

/**
 * @author Yue Hang
 * @version 6.0
 * @since 2024-4-6
 */
public class UserController {
	
	/**
	 * Takes in all login details from user and checks if it matches the details in our database
	 * @param username Input username from user
	 * @param password Input password from user
	 * @param employeePosition Input position from user
	 * @return {@code true} if login details match, otherwise {@code true}
	 */
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
	
	/**
	 * Change the password of employee
	 * @param emp Employee object
	 * @param password Password
	 * @param confirmPassword Confirm Password
	 * @return
	 */
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
	
	/**
	 * Change the password of admin 
	 * @param admin Admin object
	 * @param password Password
	 * @param confirmPassword Confirm Password
	 * @return
	 */
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
