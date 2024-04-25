package view;

import helper.Helper;

public class DisplayStaffView extends MainView {
	public void printActions() {
		Helper.clearScreen();
        printBreadCrumbs("Food Ordering and Management App View > Manage Staff Account View");// change breadcrumbs
        System.out.println("What would you like to do ?");
        System.out.println("(1) Add new staff");
        System.out.println("(2) Remove staff");
        System.out.println("(3) Update staff");
        System.out.println("(4) Exit");
	}
	
	public void viewApp() {// change case 2 and 3
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
                case 2: // Remove Staff
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    promptRemoveStaffAccount();
                    break;
                case 3:// 
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Update menu items");
                    promptUpdateStaff();
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
