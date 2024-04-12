package view;

import controller.AdminController;
import enums.EmployeePosition;
import helper.Helper;
import repository.Repository;

public class AdminView extends MainView{
	ManageStaffAccountView manageStaffAccountView = new ManageStaffAccountView();
	ManageBranchView manageBranchView = new ManageBranchView();
	ManagePaymentView managePaymentView = new ManagePaymentView();
	
	public void printActions() {
		Helper.clearScreen();
		//!!need to change the headline
        printBreadCrumbs("Food Ordering App View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Add/Remove/Update Staff Account");
        System.out.println("(2) Display Staff List");
        System.out.println("(3) Assign Managers");
        System.out.println("(4) Promote Staff");
        System.out.println("(5) Transfer Staff");
        System.out.println("(6) Add/Remove Payment Method");
        System.out.println("(7) Open/Close Branch");
        System.out.println("(8) Exit");
	}
	
	public void viewApp() { // change breadcrumbs //use class methods() first, then inside those methods(), call controller methods.
		int opt = -1; 
		do { 
            printActions();
            opt = Helper.readInt(1,7);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Add menu items");
                    manageStaffAccountView.viewApp();
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    AdminController.displayStaffList();
                    break;
                case 3:
                    Helper.clearScreen();
                    AdminController.assignManager();
                    break;
                case 4:
                	Helper.clearScreen();
                	printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                	promptPromoteStaff();
                    break;
                case 5:
                	Helper.clearScreen();
                	promptTransferStaff();
                    break;
                case 6:
                	Helper.clearScreen();
                	managePaymentView.viewApp();
                	
                case 7:// think about how to settle open close branch
                	Helper.clearScreen();
                	manageBranchView.viewApp();
                	break;
                case 8:
                	System.exit(0);
                	Helper.clearScreen();
                	
                default:
                    System.out.println("Invalid option");
                    break;
            }
            if (opt != 8) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 8);
	}
	
	
	private void printRoleMenu() {
        System.out.println("Please enter the staff's role after promotion(1-3)");
        System.out.println("(1) Admin");
        System.out.println("(2) Manager");
        System.out.println("(3) Staff");
    }
    
    private EmployeePosition promptRole() {
        printRoleMenu();
        int choice = Helper.readInt(1, 3);
        if (choice < 1 || choice > 3) {
            return null;
        } else {
            switch (choice) {
                case 1:
                    return EmployeePosition.ADMIN;
                case 2:
                    return EmployeePosition.MANAGER;
                case 3:
                	return EmployeePosition.STAFF;
                default:
                    break;
            }
        }
        return null;
    };
	
	private boolean promptPromoteStaff() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Guest View > Update a Guest Detail");
        System.out.println("Enter the staff login Id that you want to promote: ");
        String loginId = Helper.readString();
        
        if (AdminController.searchStaffById(loginId).size() == 0) {
            System.out.println("Staff not found!");
            return false;
        }
        EmployeePosition position = promptRole();
        int opt = -1;
        opt = Helper.readInt(1, 3);
        switch (opt) {
            case 1:
                AdminController.promoteStaff(loginId, 2, position);
                return true;
            case 2:
            	AdminController.promoteStaff(loginId, 2, position);
                return true;
            case 3:
            	AdminController.promoteStaff(loginId, 2, position);
                return true;
            default:
                break;
        }
        return false;
    }
	
	
	//loop through branch hash map to print all branch
	private void printBranchMenu() {
        for(String branch : Repository.BRANCHES) {
        	System.out.println(branch);
        }
    }
	
	private boolean promptTransferStaff() {
		System.out.println("Enter the staff's login ID that you want to promote:");
		String loginId = Helper.readString();
		if (AdminController.searchStaffById(loginId).size() == 0) {
            System.out.println("Staff not found!");
            return false;
        }
		System.out.println("Enter the staff's new branch:");
		printBranchMenu();
         
        String userInput = Helper.readString().trim().toUpperCase();
        
        if (Repository.BRANCHES.contains(userInput)) {
        	AdminController.transferStaff(loginId, userInput);
            System.out.println("Staff has been transferred to branch " + userInput);
            return true;
        } else {
            System.out.println("Branch "+ userInput + " does not exist.");
            return false;
        }
	}
	
}
