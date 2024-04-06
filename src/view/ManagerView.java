package view;
import helper.Helper;
import controller.ManagerController;
import repository.FileType;
import enums. *;

public class ManagerView extends MainView{
	public void printActions() {
		System.out.println("What would you like to do ?");
        System.out.println("(1) Add menu items");
        System.out.println("(2) Remove menu items");
        System.out.println("(3) Update menu items");
        System.out.println("(4) Display new order");
        System.out.println("(5) View Order Details");
		System.out.println("(6) Process Order");
		System.out.println("(7) Quit");
	}
	
	public void viewApp() {// change breadcrumbs // how to accept enum arguments?
		int opt = -1; 
		String name = "";
        String description = "";
        double price = 0;
        do {
            printActions();
            opt = Helper.readInt(1, 7);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Add menu items");
                    System.out.println("Enter name of item to be added:\r");
                    name = Helper.readString();
                    System.out.printf("Enter description of %s:\n\r", name);
                    description = Helper.readString();
                    System.out.printf("Enter price of %s:\n\r", name);
                    price = Helper.readDouble();
                    addMenuItem(name, description, price, FoodCategory);
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
                    updateMenuItem(name, description, price, foodCategory);
                    break;
               
                case 4:// do we want to just copy paste staffView? or do we want to extend StaffView instead?
                    break;
                case 5:
                    break;
                case 6:
                	
                case 8:
                	break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            if (opt != 7) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 7);
	}
	
	private void addMenuItem(String name, String description, double price, FoodCategory foodCategory){

        if (ManagerController.addMenuItem(name, description, price, foodCategory)){
            System.out.printf("\"%s\" added to menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to menu FAILED (\"%s\" is already in the menu)\n", name);
        }
    }
	
	private void removeMenuItem(String name){
        if (ManagerController.removeMenuItem(name)){
            System.out.printf("\"%s\" removed from menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from menu FAILED (\"%s\" NOT FOUND in order\\ removal quantity > current quantity)\n", name);
        }
    }
	
	private void updateMenuItem(String name, String description, double price, FoodCategory foodCategory) {
        if (ManagerController.updateMenuItem(name, description, price, foodCategory)) {
            System.out.printf("%s updated in menu SUCCESSFULLY\n", name);
        } else {
            System.out.printf("Update menu FAILED (\"%s\" NOT FOUND in menu)\n", name);
        }
    }
}
