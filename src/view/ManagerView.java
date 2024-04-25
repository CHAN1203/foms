package view;

import helper.Helper;
import model.MenuItem;
import model.Order;
import repository.Repository;

import java.util.Map;
import java.util.Map.Entry;

import controller.MenuController;
import controller.OrderController;
import controller.StaffController;
import enums. *;

public class ManagerView extends StaffView{
	String branch;
	
	public ManagerView(String branch) {
		super(branch);
		this.branch = branch;
	}
	
	public void printActions() {
		printBreadCrumbs("Fast Food App View > Manager View");
		System.out.println("What would you like to do ?");
        System.out.println("(1) Add menu item");
        System.out.println("(2) Remove menu item");
        System.out.println("(3) Update menu item");
        System.out.println("(4) Print all menu items");
        System.out.println("(5) Display processing order");
        System.out.println("(6) View Order Details");
		System.out.println("(7) Process Order");
		System.out.println("(8) Back");
	}
	
	public void viewApp() {
		int opt = -1; 
		String name;
        String description;
        double price;
        String foodCategory;
        FoodAvailability foodAvailability;
        do {
            printActions();
            opt = Helper.readInt(1, 7);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Manager View > Add menu item");
                    System.out.println("Enter name of item to be added:");
                    name = Helper.readString();
                    System.out.println("Enter description of " + name + ":");
                    description = Helper.readString();
                    System.out.println("Enter price of " + name + ":");
                    price = Helper.readDouble();
                    System.out.println("Enter food category of " + name + ":");
                    foodCategory = Helper.readString();
                    foodAvailability = promptFoodAvailability(name);
                    addMenuItem(name, description, price, foodCategory, foodAvailability);
                    break;
                
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Manager View > Remove menu item");
                    System.out.println("Enter name of item to be removed:");
                    name = Helper.readString();
                    removeMenuItem(name);
                    break;
                
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Manager View > Update menu item");
                    System.out.println("Enter name of item to be updated:");
                    name = Helper.readString();
                    System.out.println("Enter description of " + name + ":");
                    description = Helper.readString();
                    System.out.println("Enter price of " + name + ":");
                    price = Helper.readDouble();
                    System.out.println("Enter food category of " + name + ":");
                    foodCategory = Helper.readString();
                    foodAvailability = promptFoodAvailability(name);
                    updateMenuItem(name, description, price, foodCategory, foodAvailability);
                    break;
                    
                case 4:
                	Helper.clearScreen();
                	printBreadCrumbs("Fast Food App View > Manager View > Print All Menu Items");
                	MenuController.printAllMenuItems(this.branch);
                	break;
                case 5:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Manager View > Display Processing Order");
                	StaffController.displayProcessingOrders(this.branch);
                	break;
                case 6:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Manager View > View Order Details");
                	System.out.println("Select order to view details:");
					int choice = promptSelectOrderId(this.branch);
					if(choice == 0) {
						continue;
					}
					else {
						StaffController.viewParticularOrderDetails(this.branch, choice);
					}
                    break;
                case 7:
                    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Manager View > Process Order");
                	System.out.println("Select order to process:");
					int selection = promptSelectOrderId(this.branch);
					if(selection == 0) {
						continue;
					}
					else {
						StaffController.updateOrderStatus(this.branch, selection);
					}
                case 8:
                	break;
            }
            if (opt != 8) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 8);
	}
	
	private void addMenuItem(String name, String description, double price, String foodCategory, FoodAvailability foodAvailability){
        if (MenuController.addMenuItem(this.branch, name, foodCategory, description, price, foodAvailability)){
            System.out.printf("\"%s\" added to menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to menu FAILED (\"%s\" is already in the menu)\n", name);
        }
    }
	
	private void removeMenuItem(String name){
        if (MenuController.removeMenuItem(this.branch, name)){
            System.out.printf("\"%s\" removed from menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from menu FAILED (\"%s\" NOT FOUND in order\\ removal quantity > current quantity)\n", name);
        }
    }
	
	private void updateMenuItem(String name, String description, double price, String foodCategory, FoodAvailability foodAvailability) {
		if (MenuController.updateMenuItem(this.branch , name, description, price, foodCategory, foodAvailability)){
            System.out.printf("%s updated in menu SUCCESSFULLY\n", name);
        } else {
            System.out.printf("Update menu FAILED (\"%s\" NOT FOUND in menu)\n", name);
        }
    }
	
	private FoodAvailability promptFoodAvailability(String name) {
		int opt = -1;
		System.out.println("Choose availability of " + name + ":");
		System.out.println("(1)" + FoodAvailability.AVAILABLE);
		System.out.println("(2)" + FoodAvailability.UNAVAILABLE);
		opt = Helper.readInt(1,2);
		if (opt == 1) {
			return FoodAvailability.AVAILABLE;
		}
		else {
			return FoodAvailability.UNAVAILABLE;
		}
	}
}
