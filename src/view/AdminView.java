package view;

import controller.AdminController;
import helper.Helper;

public class AdminView extends MainView{
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
	
	public void viewApp() {
		int opt = -1;
		do {
            printActions();
            opt = Helper.readInt(1,7);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Add menu items");
                    ManageStaffAccount.viewApp();
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
                	AdminController.promoteStaff();
                    break;
                case 5:
                	Helper.clearScreen();
                	AdminController.transferStaff();
                    break;
                case 6:
                	Helper.clearScreen();
                	ManagePaymentMethod.viewApp();
                	
                case 7:
                	Helper.clearScreen();
                	
                	break;
                case 8:
                	Helper.clearScreen();
                	
                default:
                    System.out.println("Invalid option");
                    break;
            }
            if (opt != 7) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 7);
	}
	
	
	
}
