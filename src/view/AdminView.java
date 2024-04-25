package view;
import model.Employee;
import controller.AdminController;
import controller.BranchController;
import enums.EmployeePosition;
import helper.Helper;
import repository.Repository;

public class AdminView extends MainView{
	ManageStaffAccountView manageStaffAccountView = new ManageStaffAccountView();
	ManageBranchView manageBranchView = new ManageBranchView();
	ManagePaymentView managePaymentView = new ManagePaymentView();
	DisplayStaffView displayStaffView = new DisplayStaffView();
	
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
            opt = Helper.readInt(1,8);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    manageStaffAccountView.viewApp();
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    displayStaffView.viewApp();
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
                	break;
                case 7:// think about how to settle open close branch
                	Helper.clearScreen();
                	manageBranchView.viewApp();
                	break;                	
                default:
                    System.out.println("Invalid option");
                    break;
            }
            if (opt != 8) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 8);
	}
	
	private boolean promptPromoteStaff() {
        Helper.clearScreen();
        System.out.println("Enter the staff login Id that you want to promote: ");
        String loginId = Helper.readString();
        
        if (AdminController.searchStaffById(loginId).size() == 0) {
            System.out.println("Staff not found!");
            return false;
        }
        for(Employee employee:AdminController.searchStaffById(loginId)) {
        	if(employee.getPosition() == EmployeePosition.MANAGER) {
        		System.out.println("Manager cannot be promoted anymore!");
        		return false;
        	}
            AdminController.promoteStaff(loginId, 2, EmployeePosition.MANAGER);
            System.out.println(employee.getName() + " has been promoted to " + employee.getPosition());
            return true;
        }
        return false;
    }
	
	
	//loop through branch hash map to print all branch
	private void printBranchMenu() {
		int i = 1;
        for(String branch : Repository.BRANCH.keySet()) {
        	System.out.println("(" + i + ") " + branch);
			i++;
        }
    }
	
	private boolean promptTransferStaff() {
		int opt = -1;
		System.out.println("Enter the staff's login ID that you want to transfer:");
		String loginId = Helper.readString();
		if (AdminController.searchStaffById(loginId).size() == 0) {
            System.out.println("Staff not found!");
            return false;
        }
		System.out.println("Select the staff's new branch:");
		printBranchMenu();
        opt = Helper.readInt();
        String branch = BranchController.promptBranch(opt);
        
        if (Repository.BRANCH.keySet().contains(branch)) {
        	AdminController.transferStaff(loginId, branch);
        	Repository.BRANCH.get(branch).addNumberOfStaff(); //add number of staff when transfer
            System.out.println("Staff has been transferred to branch " + branch);
            return true;
        } else {
            System.out.println("Branch "+ branch + " does not exist.");
            return false;
        }
	}
	
}
