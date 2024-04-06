package controller;
import helper.Helper;
import model.MenuItems;
import repository.Repository;
import repository.FileType;

public class StaffController {

	public static void displayNewOrder(int orderId) {
		
	}
	
	public static void viewOrderDetails(int orderId) {
		
	}
	
	public static void processOrder(int orderId) {
		
	}
	
	/**
     * Prints the menu items in the menu with details of each menu item.
     */
    public static void printMenu() {
        for (MenuItems menuItem :Repository.MENU_ITEMS.values()) {
            System.out.println("Item name: " + menuItem.getName());
            System.out.println("Description: " + menuItem.getDescription());
            System.out.println(String.format("Price: $%.2f", menuItem.getPrice()));
        }
    }
}
