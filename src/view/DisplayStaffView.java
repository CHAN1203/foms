package view;

import controller.AdminController;
import controller.BranchController;
import helper.Helper;
import repository.Repository;
import enums.*;
/**
 * DisplayStaffView provides the view to take user input which calls {@link AdminController} to display staff list according to the filter requirements.
 * 
 * @author Chan Kee Qing
 * @version 1.0
 * @since 2022-04-24
 */
public class DisplayStaffView extends MainView{
	/**
     * View Actions of the DisplayStaffView.
     */
    @Override
	public void printActions() {
		Helper.clearScreen();
        printBreadCrumbs("Fast Food App View > Login View > Admin View > Display Staff View");
        System.out.println("Select a filter: ");
        System.out.println("(1) Branch");
        System.out.println("(2) Role");
        System.out.println("(3) Gender");
        System.out.println("(4) Age");
        System.out.println("(5) Back");
	}
    /**
     * View Application of the DisplayStaffView. <p>
     */
    @Override
	public void viewApp() {
        int opt = -1;
        do {
            printActions();
            opt = Helper.readInt(1, 5);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View >  Admin View > Display Staff View > Display Staff by Branch View");
                    promptDisplayStaffByBranch();
                    Helper.pressAnyKeyToContinue();
                    break;
                case 2: 
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Admin View > Display Staff View > Display Staff by Role View");
                    promptDisplayStaffByRole();
                    Helper.pressAnyKeyToContinue();
                    break;
                case 3: 
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Admin View > Display Staff View > Display Staff by Gender View");
                    promptDisplayStaffByGender();
                    Helper.pressAnyKeyToContinue();
                    break;
                case 4: 
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Admin View > Display Staff View > Display Staff by Age View");
                    promptDisplayStaffByAge();
                    Helper.pressAnyKeyToContinue();
                    break;
                case 5:
                	Helper.pressAnyKeyToContinue();
                	break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (opt != 5);
	}
    /**
     * function to print branch menu
     */
	private void printBranchMenu() {
		int i = 1;
        for(String branch : Repository.BRANCH.keySet()) {
        	System.out.println("(" + i + ") " + branch);
			i++;
        }
    }
	/**
	 * function to prompt to display staff by branch
	 * @return {@code true} if successfully display the staff list. Otherwise, {@code false}.
	 */
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
    /**
     * function to print role menu
     */
    private void printRoleMenu() {
        System.out.println("(1) MANAGER ");
        System.out.println("(2) STAFF ");
    }
    /**
	 * function to prompt to display staff by role
	 * @return {@code true} if successfully display the staff list. Otherwise, {@code false}.
	 */
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
    /**
     * function to print gender menu
     */
    private void printGenderMenu() {
		int i = 1;
        for(EmployeeGender gender : EmployeeGender.values()) {
        	System.out.println("(" + i + ") " + gender);
			i++;
        }
    }
    /**
	 * function to prompt to display staff by gender
	 * @return {@code true} if successfully display the staff list. Otherwise, {@code false}.
	 */
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
    /**
	 * function to prompt to display staff by age
	 * @return {@code true} if successfully display the staff list. Otherwise, {@code false}.
	 */
    private boolean promptDisplayStaffByAge() {
		System.out.println("Enter the age: ");
		int age = Helper.readInt();
		return AdminController.displayStaffListByAge(age);
    }
}
