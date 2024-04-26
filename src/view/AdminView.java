package view;
import model.Admin;
import model.Employee;
import controller.AdminController;
import controller.BranchController;
import controller.UserController;
import enums.EmployeeGender;
import enums.EmployeePosition;
import helper.Helper;
import repository.Repository;

/**
 * AdminView provides the view to take user input which calls {@link AdminController} to manage {@link Branch} and {@link Employee}.
 * @author Chan Kee Qing
 * @version 1.0
 * @since 2022-04-02
 */
public class AdminView extends MainView{
	
	/**
	 * initialize objects to call their view app
	 */
	private ManageStaffAccountView manageStaffAccountView = new ManageStaffAccountView();
	private ManageBranchView manageBranchView = new ManageBranchView();
	private ManagePaymentView managePaymentView = new ManagePaymentView();
	private DisplayStaffView displayStaffView = new DisplayStaffView();
	/**
     * View Actions of the AdminView.
     */
    @Override
	public void printActions() {
		Helper.clearScreen();
        printBreadCrumbs("Fast Food App View > Login View > Admin View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Add/Remove/Update Staff Account");
        System.out.println("(2) Display Staff List");
        System.out.println("(3) Assign Managers");
        System.out.println("(4) Promote Staff");
        System.out.println("(5) Transfer Staff");
        System.out.println("(6) Add/Remove Payment Method");
        System.out.println("(7) Open/Close Branch");
        System.out.println("(8) Change password");
        System.out.println("(9) Exit");
	}
    /**
     * View Application of the AdminView. <p>
     * see {@link AdminController} for more {@link Employee} management details.
     */
    @Override
	public void viewApp() { 
		int opt = -1; 
		do { 
            printActions();
            opt = Helper.readInt(1,9);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    manageStaffAccountView.viewApp();
                    break;
                case 2:
                    Helper.clearScreen();
                    displayStaffView.viewApp();
                    break;
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Admin View > Assign Manager View");
                    promptAssignManager();
                    break;
                case 4:
                	Helper.clearScreen();
                	printBreadCrumbs("Fast Food App View > Login View > Admin View > Promote Staff View");
                	promptPromoteStaff();
                    break;
                case 5:
                	Helper.clearScreen();
                	printBreadCrumbs("Fast Food App View > Login View > Admin View > Transfer Staff View");
                	promptTransferStaff();
                    break;
                case 6:
                	Helper.clearScreen();
                	managePaymentView.viewApp();
                	break;
                case 7:
                	Helper.clearScreen();
                	manageBranchView.viewApp();
                	break;  
                case 8:
                	printBreadCrumbs("Fast Food App View > Login View > Admin View > Password Change View");
                	promptChangePassword();
                	break;
                case 9:
                	break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            if (opt != 9) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 9);
	}
    /**
     * function to prompt to promote staff
     * @return {@code true} if successfully promote a staff. Otherwise, {@code false}.
     */
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
        	}else if(Repository.BRANCH.get(employee.getBranch()).getNumberOfManager() < Repository.BRANCH.get(employee.getBranch()).getManagerQuota()) {
        		AdminController.promoteStaff(loginId, 2, EmployeePosition.MANAGER);
                System.out.println(employee.getName() + " has been promoted to " + employee.getPosition());
                return true;
        	}
            System.out.println("Manager Quota Exceeded. Promote Manager Unsucessful!");
        	return false;
        }
        return false;
    }
	/**
	 * function to loop through branch hash map to print branch menu
	 */
	private void printBranchMenu() {
		int i = 1;
        for(String branch : Repository.BRANCH.keySet()) {
        	System.out.println("(" + i + ") " + branch);
			i++;
        }
    }
	/**
	 * function to prompt to transfer staff
	 * @return {@code true} if successfully transfer a staff. Otherwise, {@code false}.
	 */
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
        	//return true only when transfer is successful
        	if(AdminController.transferStaff(loginId, branch)) {
        		Repository.BRANCH.get(branch).addNumberOfStaff(); //add number of staff when transfer
                System.out.println("Staff has been transferred to branch " + branch);
                return true;
        	}
        	return false;
        } else {
            System.out.println("Branch "+ branch + " does not exist.");
            return false;
        }
	}
	/**
	 * function to prompt to assign a manager
	 * @return {@code true} if successfully assign a manager. Otherwise, {@code false}.
	 */
	private boolean promptAssignManager() {
		System.out.println("Enter manager loginId:");
		String loginId = Helper.readString();
		System.out.println("Enter manager name:");
        String name = Helper.readString();
        String password = "password";
        System.out.println("Enter the branch which the staff is in:");
        printBranchMenu();
        int opt = -1;
        opt = Helper.readInt();
        String branch = BranchController.promptBranch(opt); 
        
        EmployeeGender gender = promptGender();
        if(gender == null) return false;
        
        System.out.println("Enter the staff's age");
        int age = Helper.readInt();
        
        if(Repository.BRANCH.get(branch).getNumberOfEmployee() < Repository.BRANCH.get(branch).getstaffQuota()) {
        	if(Repository.BRANCH.get(branch).getNumberOfManager() < Repository.BRANCH.get(branch).getManagerQuota()) {
            	AdminController.assignManager(name, password, branch, EmployeePosition.MANAGER, gender, age, loginId);
            	return true;
    		}else {
    			System.out.println("Manager Quota Exceeded. Add Manager Unsucessful!");
            	return false;
    		}
        }else {
        	System.out.println("Staff Quota Exceeded. Add Manager Unsucessful!");
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
     * The function to prompt an Admin to change password through {@link UserController}
     */
    private void promptChangePassword() {
		System.out.println("Verify your loginID: ");
		String loginId = Helper.readString();
		System.out.println("Verify your password: ");
		String password = Helper.readString();
		
		Admin admin = Repository.ADMIN.get(loginId);
		
		if( admin != null && admin.getPassword().equals(password)) {
			System.out.println("Verification successful");
			System.out.println();
			System.out.println("Enter new password: ");
			String newPassword = Helper.readString();
			System.out.println("Re-enter new password: ");
			String confirmPassword = Helper.readString();
			if(UserController.changePassword(admin, newPassword, confirmPassword)) {
				System.out.println("Password changed successfully!");
				return;
			}
			else {
				System.out.println("Password does not match");
				return;
			}
			
		}
		else {
			System.out.println("Verification failed");
			return;
		}
	}
}
