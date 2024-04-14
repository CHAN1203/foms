package view;

import controller.AdminController;
import helper.Helper;
import repository.Repository;

public class ManageBranchView extends MainView{
	public void printActions() {
		Helper.clearScreen();
        printBreadCrumbs("Food Ordering and Management App View > Manage Staff Account View");// change breadcrumbs
        System.out.println("What would you like to do ?");
        System.out.println("(1) Open new Branch");
        System.out.println("(2) Close existing Branch");
        System.out.println("(3) Exit");
	}
	
	public void viewApp() {// change case 2 and 3
        int opt = -1;
        do {
            printActions();
            opt = Helper.readInt(1, 3);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Food Ordering and Management App View > Manage Staff Account View > Add staff account");
                    promptOpenBranch();
                    break;
                case 2: // Remove Staff
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    promptCloseBranch();
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
	
	
	//open branch
    private boolean promptOpenBranch() {
    	 System.out.println("Enter new branch name: ");
         String newBranch = Helper.readString();
         System.out.println("Enter new branch name location: ");
         String location = Helper.readString();
         //new branch has only 1 manager 
    	if(AdminController.openBranch(newBranch, location, 1, 0)) return true;
    	return false;
    }
    
    private boolean promptCloseBranch() {
    	System.out.println("Enter the name of the branch to close:");
    	for (String branch : Repository.BRANCHES) {
            System.out.println(branch);
        }
    	
        String branchToClose = Helper.readString();
   	if(AdminController.closeBranch(branchToClose)) return true;
   	return false;
   }
    
}
