package controller;
import helper.Helper;
import repository.Repository;
import repository.FileType;
import repository.Repository;
import repository.FileType;
import enums. *;
import model. *;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StaffController {

	public static void displayProcessingOrders(String branchName) {// display all orders that are in the state PROCESSING
		
		HashMap <Integer, Order> ordersInBranch = getOrdersInBranch(branchName);
		
		for (Order order: ordersInBranch.values()) {
			if (order.getStatus() == OrderStatus.PROCESSING) {
				
				System.out.println("Order ID: " + order.getOrderID());
				System.out.println("------------------------------------------------------");
				System.out.println("Item Name                             Quantity");
				System.out.println("------------------------------------------------------");

				Set <String> itemNames = order.getItemsInOrder().keySet(); // get the item names in the order

				for (String itemName : itemNames) {
					Integer quantity = order.getItemsInOrder().get(itemName); // use hashmap to get quantity (value) from key (itemName)
					System.out.println(itemName + "                          " + quantity);
				}
				
			}
		}
	}
	
	public static void viewParticularOrderDetails(String branchName, int orderID) {//retrieve the order of a specific OrderID print
		
		Order order = getOrderByID(branchName, orderID);
		
		// if there are no orders with the given Order ID
		
		if (order == null) {
			System.out.println("Order does not exist!");
		}
		
		// order found, print order details
		
		else {
			particularOrderView(order); // method written below
		}
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
	
	public static void processOrder(String branchName, int orderID) {// change from Preparing to Ready to Pickup
				
    	Order order = getOrderByID(branchName, orderID);
    	
    	// if order does not exist
    	
    	if (order == null) {
    		System.out.println("Order does not exist!");
    	}
    	
    	// order exists
    	
    	else {
    		order.setStatus(OrderStatus.READYFORPICKUP);
    	}	
	}
	
	/**
     * Prints the menu items in the menu with details of each menu item.
     */
    public static void printMenu(String branchName) {
    	
    	HashMap<String, MenuItems> menuItemsInBranch = Repository.BRANCH.get(branchName).getMenuItems();
    	
    	// iterate through each item in the menu and print out the details
        for (MenuItems menuItem : menuItemsInBranch.values()) { 
            System.out.println("Item name: " + menuItem.getName());
            System.out.println("Description: " + menuItem.getDescription());
            System.out.println(String.format("Price: $%.2f", menuItem.getPrice()));
        }
    }
    
    public static Order getOrderByID(String branchName, int orderID){
        	
    	HashMap<Integer, Order> ordersInBranch = getOrdersInBranch(branchName);
    	
    	if (ordersInBranch.containsKey(orderID)) {
    		Order order = ordersInBranch.get(orderID); // get the order object from ID
    		return order;
    	}
    	
    	else {
    		return null;
    	}
    	
    }
    
    public static HashMap<Integer, Order> getOrdersInBranch(String branchName){ // return orders in the specified branch
    	Branch branch = Repository.BRANCH.get(branchName);
    	HashMap<Integer, Order> ordersInBranch = branch.getOrders(); // create a hashmap with all the orders in the current branch
    	return ordersInBranch;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
