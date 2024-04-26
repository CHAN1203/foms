package view;

import controller.AdminController;
import controller.BranchController;
import helper.Helper;
import repository.Repository;

/**
 * ManageBranchView provides the view to take user input which calls {@link AdminController} to manage {@link Branch}.
 * @author Chan Kee Qing
 * @version 1.0
 * @since 2024-04-15
 */
public class ManageBranchView extends MainView{
	/**
     * View Actions of the ManageBranchView.
     */
    @Override
	public void printActions() {
		Helper.clearScreen();
        printBreadCrumbs("Fast Food App View > Login View > Admin View > Manage Branch View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Open new Branch");
        System.out.println("(2) Close existing Branch");
        System.out.println("(3) Exit");
	}
    /**
     * View Application of the ManageBranchView. <p>
     */
    @Override
	public void viewApp() {
        int opt = -1;
        do {
            printActions();
            opt = Helper.readInt(1, 3);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Login View > Admin View > Manage Branch View > Open Branch View");
                    promptOpenBranch();
                    break;
                case 2: 
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    promptCloseBranch();
                    break;
                case 3:
                	break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            if (opt != 3) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 3);
	}
	
	
	/**
	 * function to prompt to open a branch
	 * @return {@code true} if open branch is successful. Otherwise, {@code false}.
	 */
    private boolean promptOpenBranch() {
    	 System.out.println("Enter new branch name: ");
         String newBranch = Helper.readString();
         System.out.println("Enter new branch name location: ");
         String location = Helper.readString();
         System.out.println("Enter the staff quota: ");
         int staffQuota = Helper.readInt();
         //new branch has only 1 manager 
    	if(AdminController.openBranch(newBranch, location, staffQuota, 0)) return true;
    	return false;
    }
    
    /**
	 * function to prompt to close a branch
	 * @return {@code true} if close branch is successful. Otherwise, {@code false}.
	 */
    private boolean promptCloseBranch() {
    	System.out.println("Choose a branch to close:");
    	printBranchMenu();
    	int opt = -1;
    	opt = Helper.readInt();
    	String branchToClose = BranchController.promptBranch(opt);
 
    	if(AdminController.closeBranch(branchToClose)) return true;
    	return false;
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
}
