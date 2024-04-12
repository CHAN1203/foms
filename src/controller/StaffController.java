package controller;
import helper.Helper;
import model.MenuItems;
import repository.Repository;
import repository.FileType;
import repository.Repository;
import repository.FileType;
import enums. *;
import model. *;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class StaffController {

	public static void displayNewOrder(int orderID) {// retrieve hashmap (order) printf
		
	}
	
	public static void viewParticularOrderDetails(int orderID) {//retrieve the order of a specific OrderID print
		
		Order order = getOrderByID(orderID);
		
		// if there are no orders with the given Order ID
		
		if (order == null) {
			nullOrderView();
		}
		
		// order found, print order details
		
		else {
			particularOrderView(order);
		}
	}
	
	public static void processOrder(int orderID) {// change from Preparing to Ready to Pickup
		
    	Order order = getOrderByID(orderID);
    	
    	// if order does not exist
    	
    	if (order == null) {
    		System.out.println("Order does not exist!");
    	}
    	
    	// order exists
    	
    	else {
    		order.setStatus(OrderStatus.READYFORPICKUP);
    	}	}
	
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
    /////////////////////////////////////
    
    public static void nullOrderView() {
    	System.out.println("Order does not exist!");
    }
    
    public static void particularOrderView(Order order) {
    	System.out.println("Order ID: " + order.getOrderID());
    	System.out.println("Status: " + order.getStatus());
    	System.out.println();
    	System.out.println("Items Ordered			Quantity");
    	System.out.println("--------------------------------");
    	
    	// initialize the variable keySet for readibility
    	Set<String> keySet = order.getItemsInOrder().keySet();
    	
    	// Iterate through the HashMap and print each key value pair, read as follows "for each items in the set of keys in the HashMap"
    	for (String items: keySet) {
    		
    		Integer quantity = order.getItemsInOrder().get(items);
    		
    		System.out.println(items + "		" + quantity);
    	}
    }
    
    public static Order getOrderByID(int orderID){
    	
    	if (Repository.ORDER.containsKey(orderID)) {
    		Order searchedOrder = Repository.ORDER.get(orderID);
    		return searchedOrder;
    	}    	
    	
    	// Order not found
    	else { 
    		return null;
    	}
    }
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
