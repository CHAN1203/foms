package controller;
import helper.Helper;
import model.MenuItems;
import repository.Repository;
import repository.FileType;

public class StaffController {

	public static void displayNewOrder(int orderId) {// retrieve hashmap (order) printf
		
	}
	
	public static void viewParticularOrderDetails(int orderId) {//retrieve the order of a specific OrderID print
		
	}
	
	public static void processOrder(int orderId) {// change from Preparing to Ready to Pickup
		//(Model Order should include:1)  Order ID = Customer ID, 2) multiple food name + quantity, 3) enum orderStatus (add this enum in the enum package),  
		//
		
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
