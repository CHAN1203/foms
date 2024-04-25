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

	public static boolean displayProcessingOrders(String branchName) {// display all orders that are in the state PROCESSING
		
		HashMap <String, Order> ordersInBranch = getOrdersInBranch(branchName);
		
		if (ordersInBranch.size() == 0) {
			Helper.clearScreen();
			System.out.println("No processing order right now.");
			return false;
		}
		System.out.println("size is not 0");
		
		for (Map.Entry<String,Order> entry : ordersInBranch.entrySet()) {
			System.out.println("Something");// iterate through each order object in the branch
			Order currentOrder = entry.getValue();
			if (currentOrder.getStatus() == OrderStatus.PROCESSING) {
			
				OrderController.printOrderDetails(currentOrder.getOrderId(), branchName);
			}
		}
		return true;
	}

	public static void viewParticularOrderDetails(String branchName, int orderID) { //retrieve the order of a specific OrderID print
		
		Order order = getOrderByID(branchName, orderID);
		
		// if there are no orders with the given Order ID
		
		if (order == null) {
			Helper.clearScreen();
			System.out.println("Order does not exist!");
		}
		
		// order found, print order details
		
		else {
			particularOrderView(order); // method written below
		}
	}
	
    public static void particularOrderView(Order order) {
    	Helper.clearScreen();
    	System.out.println("Order ID: " + order.getOrderId());
    	System.out.println("Date time: " + order.getDateTime());
    	System.out.println("Total bill: " + order.getTotalBill());
    	System.out.println("Remarks: " + order.getRemarks());
    	System.out.println("Status: " + order.getStatus());
    	System.out.println();
    	System.out.println("Items Ordered			Quantity");
    	System.out.println("--------------------------------");
    	
    	// initialize the variable keySet for readibility
    	HashMap<MenuItem, Integer> itemsInOrder = order.getCurrentOrders();
    	Set<MenuItem> keySet = itemsInOrder.keySet();
    	Set<String> itemNames = null;
    	
    	for (MenuItem menuItem: keySet) { // add the item names to the set of names
    		itemNames.add(menuItem.getName());
    	}
    	
    	// Iterate through the HashMap and print each key value pair, read as follows "for each items in the set of keys in the HashMap"
    	for (String items: itemNames) {
    		
    		Integer quantity = order.getCurrentOrders().get(items);
    		
    		System.out.println(items + "		" + quantity);
    	}
    }
	
	public static void processOrder(String branchName, int orderID) {// change from Preparing to Ready to Pickup
				
    	Order order = getOrderByID(branchName, orderID);
    	
    	// if order does not exist
    	
    	if (order == null) {
    		Helper.clearScreen();
    		System.out.println("Order does not exist!");
    	}
    	
    	// order exists
    	
    	else {
    		order.setStatus(OrderStatus.READYFORPICKUP);
    		Helper.clearScreen();
    		System.out.println("Order is ready to pickup!");
    	}	
	}
	
	/**
     * Prints the menu items in the menu with details of each menu item.
     */
    public static void printMenu(String branchName) {
    	
    	HashMap<String, MenuItem> menuItemsInBranch = Repository.BRANCH.get(branchName).getMenuItems();
    	
    	// iterate through each item in the menu and print out the details
        for (MenuItem menuItem : menuItemsInBranch.values()) { 
            System.out.println("Item name: " + menuItem.getName());
            System.out.println("Description: " + menuItem.getDescription());
            System.out.println(String.format("Price: $%.2f", menuItem.getPrice()));
        }
    }
    
    public static Order getOrderByID(String branchName, int orderID){
        	
    	HashMap<String, Order> ordersInBranch = getOrdersInBranch(branchName);
    	
    	if (ordersInBranch.containsKey(orderID)) {
    		Order order = ordersInBranch.get(orderID); // get the order object from ID
    		return order;
    	}
    	
    	else {
    		return null;
    	}
    	
    }
    
    public static HashMap<String, Order> getOrdersInBranch(String branchName){ // return orders in the specified branch
    	Branch branch = Repository.BRANCH.get(branchName);
    	HashMap<String, Order> ordersInBranch = branch.getOrders(); // create a hashmap with all the orders in the current branch <OrderID (string), Order (object)>
    	return ordersInBranch;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

