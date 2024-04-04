package view;

import enums.EmployeePosition;
import controller.UserManager;
import helper.Helper;
import repository.Repository;
import view.CategoryView;
import view.CustomerView;
import model.Employee;

public class LoginView extends MainView {

	CategoryView categoryView = new CategoryView();// do we need to create an instance everytime? or should we just make the methods static?
	CustomerView customerView = new CustomerView();
	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View");
        System.out.println("Who you like to login as?");
		System.out.println("1. Customer Login");
		System.out.println("2. Staff Login");
		System.out.println("3. Exit App");
	}

	@Override
	public void viewApp() {
		printActions();
		int opt;
		do {
			opt = Helper.readInt();
			switch (opt) {
			case 1:
				customerView.viewApp();
				break;
			case 2:
				loginEmployee();
				break;
			}

			
		} while (opt != 3);
		
	}
	
	private void loginEmployee() {
		// optional can clear screen
		System.out.println("\nChoose employee type:");
		System.out.println("1. Admin");
		System.out.println("2. Manager");
		System.out.println("3. Staff");
		
		int empPos;
		EmployeePosition employeePosition;
		
		empPos = Helper.readInt();
		
		switch (empPos) {
		case 1:
			employeePosition = EmployeePosition.ADMIN;
			break;
		case 2:
			employeePosition = EmployeePosition.MANAGER;
			break;
		
		case 3:
			employeePosition = EmployeePosition.STAFF;
			break;
		}
		
		String username;
		String password;
		
		System.out.println("\nUsername:");
		username = Helper.readString();
		System.out.println("\nPassword:");
		password = Helper.readString();
		
		
		boolean loginSuccess = UserManager.authenticate(username, password);
		if (loginSuccess) {
			System.out.println("Login successful, welcome " + username);
			if(employeePosition == EmployeePosition.ADMIN) {
				AdminView.viewApp();
			}
			else if(employeePosition == EmployeePosition.MANAGER) {
				ManagerView.viewApp();
			}
			else {
				StaffView.viewApp();
			}
			// goto next view, use employeePosition to decide 
			// if (employeePosition == EmployeePosition.ADMIN) {
			// 		AdminView.viewApp()
			// }
		} else {
			System.out.println("Invalid username or password");
		}
	}

}
