package view;

import controller.AdminController;
import controller.BranchController;
import helper.Helper;
import repository.Repository;
import enums.*;

public class DisplayStaffView extends MainView{
	public void printActions() {
		Helper.clearScreen();
        printBreadCrumbs("Food Ordering and Management App View > Manage Staff Account View");// change breadcrumbs
        System.out.println("Select a filter: ");
        System.out.println("(1) branch");
        System.out.println("(2) role");
        System.out.println("(3) gender");
        System.out.println("(4) age");
        System.out.println("(5) exit");
	}
	
	public void viewApp() {// change case 2 and 3
        int opt = -1;
        do {
            printActions();
            opt = Helper.readInt(1, 5);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Food Ordering and Management App View > Manage Staff Account View > Add staff account");
                    promptDisplayStaffByBranch();
                    break;
                case 2: 
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    promptDisplayStaffByRole();
                    break;
                case 3: 
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    promptDisplayStaffByGender();
                    break;
                case 4: 
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    promptDisplayStaffByAge();
                    break;
                case 5:
                	Helper.clearScreen();
                	break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (opt != 5);
	}
	
	
	private void printBranchMenu() {
		int i = 1;
        for(String branch : Repository.BRANCH.keySet()) {
        	System.out.println("(" + i + ") " + branch);
			i++;
        }
    }
	
	
    private boolean promptDisplayStaffByBranch() {
    	 System.out.println("Enter the branch name: ");
    	 printBranchMenu();
    	 int opt = -1;
    	 opt = Helper.readInt();
         String branch = BranchController.promptBranch(opt);
         
         if (Repository.BRANCH.keySet().contains(branch)) {
         	AdminController.displayStaffListByBranch(branch);
             return true;
         } else {
             System.out.println("Branch "+ branch + " does not exist.");
             return false;
         }
    }
    
    private void printRoleMenu() {
        System.out.println("(1) MANAGER ");
        System.out.println("(2) STAFF ");
    }
    
    private boolean promptDisplayStaffByRole() {
		System.out.println("Enter the role name: ");
		printRoleMenu();
		int opt = -1;
		opt = Helper.readInt();
		switch(opt) {
			case 1:
				AdminController.displayStaffListByRole(EmployeePosition.MANAGER);
				return true;
			case 2:
				AdminController.displayStaffListByRole(EmployeePosition.STAFF);
				return true;
			default:
                System.out.println("Invalid option");
                return false;
		}
    }
    
    private void printGenderMenu() {
		int i = 1;
        for(EmployeeGender gender : EmployeeGender.values()) {
        	System.out.println("(" + i + ") " + gender);
			i++;
        }
    }
    
    private boolean promptDisplayStaffByGender() {
		System.out.println("Enter the gender: ");
		printGenderMenu();
		int opt = -1;
		opt = Helper.readInt();
		switch(opt) {
			case 1:
				AdminController.displayStaffListByGender(EmployeeGender.MALE);
				return true;
			case 2:
				AdminController.displayStaffListByGender(EmployeeGender.FEMALE);
				return true;
			default:
                System.out.println("Invalid option");
                return false;
		}
    }
    
    private boolean promptDisplayStaffByAge() {
		System.out.println("Enter the age: ");
		int age = Helper.readInt();
		return AdminController.displayStaffListByAge(age);
    }
}
