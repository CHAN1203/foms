package view;
import controller.AdminController;
import controller.BranchController;
import enums. *;
import helper.Helper;
import repository.Repository;


/**
 * ManageStaffAccountView provides the view to take user input which calls {@link AdminController} to manage {@link Branch} and {@link Employee}.
 * @author Chan Kee Qing
 * @version 1.0
 * @since 2022-04-13
 */
public class ManageStaffAccountView extends MainView{
	
	/**
     * View Actions of the ManageStaffAccountView.
     */
    @Override
	public void printActions() {
		Helper.clearScreen();
        printBreadCrumbs("Food Ordering and Management App View > Manage Staff Account View");// change breadcrumbs
        System.out.println("What would you like to do ?");
        System.out.println("(1) Add new staff");
        System.out.println("(2) Remove staff");
        System.out.println("(3) Update staff");
        System.out.println("(4) Exit");
	}
	
    /**
     * View Application of the ManageStaffAccountView. <p>
     */
    @Override
	public void viewApp() {
        int opt = -1;
        do {
            printActions();
            opt = Helper.readInt(1, 4);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Food Ordering and Management App View > Manage Staff Account View > Add staff account");
                    promptAddStaffAccount();
                    break;
                case 2: 
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    promptRemoveStaffAccount();
                    break;
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Update menu items");
                    promptUpdateStaff();
                    break;
                case 4:
                	break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            if (opt != 4) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 4);
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
	 * function to prompt to add staff account
	 * @return {@code true} if add add staff method is successful. Otherwise, {@code false}.
	 */
	private boolean promptAddStaffAccount() {
		System.out.println("Enter staff loginId:");
		String loginId = Helper.readString();
		System.out.println("Enter staff name:");
        String name = Helper.readString();
        String password = "password";
        System.out.println("Enter the branch which the staff is in:");
        printBranchMenu();
        int opt = -1;
        opt = Helper.readInt();
        String branch = BranchController.promptBranch(opt); 
        EmployeePosition position = promptRole();
        
        if( position == null) {
        	System.out.println("The position is null! Add staff unsuccessful!");
        	return false;
        }
        
        EmployeeGender gender = promptGender();
        if(gender == null) return false;
        
        System.out.println("Enter the staff's age");
        int age = Helper.readInt();
        
        if(Repository.BRANCH.get(branch).getNumberOfEmployee() < Repository.BRANCH.get(branch).getstaffQuota()) {
        	if(position == EmployeePosition.MANAGER) {
            	if(Repository.BRANCH.get(branch).getNumberOfManager() < Repository.BRANCH.get(branch).getManagerQuota()) {
                	AdminController.addStaffAccount(name, password, branch, position, gender, age, loginId);
                	return true;
        		}else {
        			System.out.println("Manager Quota Exceeded. Add Manager Unsucessful!");
                	return false;
        		}
            }
        	AdminController.addStaffAccount(name, password, branch, position, gender, age, loginId);
        	return true;
        }else {
        	System.out.println("Staff Quota Exceeded. Add Staff Unsucessful!");
        	return false;
        }
	}

	
	/**
	 * function to print gender menu
	 */
	private void printGenderMenu() {
        System.out.println("Please enter the staff's gender (1-2)");
        System.out.println("(1) Male");
        System.out.println("(2) Female");
    }
	
	
	/**
	 * function to prompt to ask for gender 
	 * @return a gender enum
	 */
	private EmployeeGender promptGender() {
        printGenderMenu();
        int choice = Helper.readInt(1, 2);
        if (choice != 1 && choice != 2) {
            return null;
        } else {
            switch (choice) {
                case 1:
                    return EmployeeGender.MALE;
                case 2:
                    return EmployeeGender.FEMALE;
                default:
                    break;
            }
        }
        return null;
    };
    
    
    /**
	 * function to print role menu
	 */
    private void printRoleMenu() {
        System.out.println("Please enter the staff's role (1-3)");
        System.out.println("(1) Manager");
        System.out.println("(2) Staff");
    }
    
    /**
	 * function to prompt to ask for employee position 
	 * @return a employee position enum
	 */
    private EmployeePosition promptRole() {
        printRoleMenu();
        int choice = Helper.readInt(1, 3);
        if (choice < 1 || choice > 3) {
            return null;
        } else {
            switch (choice) {
                case 1:
                    return EmployeePosition.MANAGER;
                case 2:
                    return EmployeePosition.STAFF;
                default:
                    break;
            }
        }
        return null;
    };
    
    
    /**
	 * function to prompt to remove staff account 
	 * @return {@code true} if remove staff account is successful. Otherwise, {@code false}.
	 */
    private boolean promptRemoveStaffAccount() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Guest View > Remove a staff");
        System.out.println("Enter the login id of the staff that you want to remove: ");
        String loginId = Helper.readString();
        if (!AdminController.removeStaffAccount(loginId)) {
            System.out.println("Employee not found!");
            return false;
        }
        return true;
    }
    
    
    /**
	 * function to prompt to update staff account 
	 * @return {@code true} if update staff account is successful. Otherwise, {@code false}.
	 */
    private boolean promptUpdateStaff() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Guest View > Update a Guest Detail");
        System.out.println("Enter the staff login Id that you want to update: ");
        String loginId = Helper.readString();
        if (AdminController.searchStaffById(loginId).size() == 0) {
            System.out.println("Staff not found!");
            return false;
        }
        printUpdateStaffMenu();
        int opt = -1;
        opt = Helper.readInt(1, 5);
        switch (opt) {
            case 1:
                System.out.println("Please enter the staff's new name:");
                String name = Helper.readString();
                AdminController.updateStaffAccount(loginId, name, 1);
                return true;
            case 2:
            	EmployeeGender gender = promptGender();
                if (gender == null) {
                    return false;
                }
                AdminController.updateStaffAccount(loginId, 2, gender);
                return true;
            case 3:
                System.out.println("Please enter the staff's new age:");
                int age = Helper.readInt();
                AdminController.updateStaffAccount(loginId, 3, age);
                return true;
            default:
                break;
        }
        return false;
    }
    
    /**
     * function to print update staff menu
     */
    private void printUpdateStaffMenu() {
        System.out.println("Please choose the information that you want to update (1-3)");
        System.out.println("(1) Name");
        System.out.println("(2) Gender");
        System.out.println("(3) Age");
    }
	
}
