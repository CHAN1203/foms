package view;
import controller.AdminController;
import helper.Helper;

public class ManageStaffAccountView extends MainView{
	public void printActions() {
		Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Menu View");// change breadcrumbs
        System.out.println("What would you like to do ?");
        System.out.println("(1) Add new staff");
        System.out.println("(2) Remove staff");
        System.out.println("(3) Update staff");
        System.out.println("(4) Exit");
	}
	
	public void viewApp() {// change case 2 and 3
        int opt = -1;
        String name = "";
        String description = "";
        double price = 0;
        do {
            printActions();
            opt = Helper.readInt(1, 4);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Add menu items");
                    AdminController.addStaffAccount();
                    break;
                case 2: // Remove Staff
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    AdminController.removeStaffAccount();
                    break;
                case 3:// 
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Update menu items");
                    AdminController.updateStaffAccount();
                    break;
                case 4:
                	System.exit(0);
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
	
}
