package controller;
import helper.Helper;
import repository.Repository;
import repository.FileType;
import enums.EmployeePosition;
import model. *;

public class AdminController {
	

	public static void addStaffAccount(String name, String password, String branch, EmployeePosition employeePosition) {
		Employee emp = new Employee(name, password, branch, employeePosition);
		Repository.BRANCH.get(emp.getBranch()).getEmployee().put(emp.getName(),emp);
	
	public static void updateStaffAccount() {

	}
	
	public static void removeStaffAccount() {
		
	}
	
	public static void displayStaffList() {
		
	}
	
	public static void assignManager() {
		
	}
	
	public static void promoteStaff() {
		
	}
	
	public static void transferStaff() {
		
	}
	
	public static void addPaymentMethod() {
		
	}
	
	public static void removePaymentMethod() {
		
	}
	
	public static void openBranch() {
		
	}
	
	public static void closeBranch() {
		
	}
	
}
