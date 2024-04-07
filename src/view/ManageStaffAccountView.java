package view;
import controller.AdminController;
import helper.Helper;

public class ManageStaffAccountView extends MainView{
	public void printActions() {
		Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Menu View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Add new staff");
        System.out.println("(2) Remove staff");
        System.out.println("(3) Update staff");
        System.out.println("(4) Exit");
	}
	
	public void viewApp() {
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
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    System.out.println("Enter name of item to be removed:\r");
                    name = Helper.readString();
                    removeMenuItem(name);
                    break;
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Update menu items");
                    System.out.println("Enter name of item to be updated:\r");
                    name = Helper.readString();
                    System.out.printf("Enter description of %s:\n\r", name);
                    description = Helper.readString();
                    System.out.printf("Enter price of %s:\n\r", name);
                    price = Helper.readDouble();
                    updateMenuItem(name, description, price);
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
	
}
