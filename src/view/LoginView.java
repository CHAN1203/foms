package view;

import enums.EmployeePosition;
import controller.UserController;
import helper.Helper;
import repository.Repository;
import view.MenuView;
import view.CustomerView;
import model.Branch;
import model.Employee;

public class LoginView extends MainView {
	
	AdminView adminView = new AdminView();
	ManagerView managerView = new ManagerView();
	StaffView staffView = new StaffView();

	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View > Login View");x
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
		String username;
		String password;
		
		System.out.println("\nUsername:");
		username = Helper.readString();
		System.out.println("\nPassword:");
		password = Helper.readString();
		
		
		boolean loginSuccess = UserController.authenticate(username, password);
		if (loginSuccess) {
			System.out.println("Login successful, welcome " + username);
			
			Branch branch = Repository.BRANCH.get(username); // get the current branch object
			
			if(employeePosition == EmployeePosition.ADMIN) {
				adminView.viewApp();
			}
			else if(employeePosition == EmployeePosition.MANAGER) {
				managerView.viewApp(branch);
			}
			else {
				staffView.viewApp(branch);
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
