package view;

import enums.EmployeePosition;
import controller.UserController;
import helper.Helper;
import repository.Repository;
/**
 * LoginView provides the view for user to choose Staff Position 
 * which calls to {@link AdminView}, {@link ManagerView} and {@link StaffView}
 * 
 * @author Jacky, Kee Qing
 * @version 1.0
 * @since 2024-04-01
 */
public class LoginView extends MainView {
	/**
	 * Constructing required View Classes
	 */
	AdminView adminView = new AdminView();
	ManagerView managerView = new ManagerView();
	StaffView staffView = new StaffView();
	/**
	 * View Actions for LoginView
	 */
	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View > Login View");
		System.out.println("\nChoose employee type:");
		System.out.println("1. Admin");
		System.out.println("2. Manager");
		System.out.println("3. Staff");
		System.out.println("4. Back");
	}
	/**
	 * View Application for LoginView that uses {@link UserController} to authenticate login 
	 */
	@Override
	public void viewApp() {
		printActions();
		
		int empPos;
		EmployeePosition employeePosition = null;
		
		do {
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
			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}while(empPos != 4);
		
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
				managerView.viewApp();
			}
			else {
				String branch = Repository.EMPLOYEE.get(loginId).getBranch();
				staffView.viewApp(branch);
			}
		} 
		
		else {
			System.out.println("Invalid username or password");
		}
	}
}
