package view;

import enums.EmployeePosition;
import controller.UserController;
import helper.Helper;
import repository.Repository;


public class LoginView extends MainView {
	
	AdminView adminView = new AdminView();
	ManagerView managerView;
	StaffView staffView;

	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View > Login View");
		System.out.println("\nChoose employee type:");
		System.out.println("1. Admin");
		System.out.println("2. Manager");
		System.out.println("3. Staff");
	}

	@Override
	public void viewApp() {
		printActions();
		
		int empPos;
		EmployeePosition employeePosition = null;
		
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
		String loginId;
		String password;
		
		System.out.println("\nLogin ID:");
		loginId = Helper.readString();
		System.out.println("\nPassword:");
		password = Helper.readString();
		
		
		boolean loginSuccess = UserController.authenticate(loginId, password, employeePosition);
		if (loginSuccess) {
			System.out.println("Login successful, welcome " +loginId);
			
			if(employeePosition == EmployeePosition.ADMIN) {
				adminView.viewApp();
			}
			else if(employeePosition == EmployeePosition.MANAGER) {
				String branch = Repository.EMPLOYEE.get(loginId).getBranch();
				managerView = new ManagerView(branch);
				managerView.viewApp();
			}
			else {
				String branch = Repository.EMPLOYEE.get(loginId).getBranch();
				staffView = new StaffView(branch);
				staffView.viewApp();
			}
		} 
		
		else {
			System.out.println("Invalid username or password");
		}
	}
}
