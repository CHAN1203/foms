package controller;
import helper.Helper;
import repository.Repository;
import enums. *;
import model. *;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class StaffController {

	public static boolean displayProcessingOrders(String branchName) {// display all orders that are in the state PROCESSING
		
		HashMap <String, Order> ordersInBranch = getOrdersInBranch(branchName);
		
		if (ordersInBranch.size() == 0) {
			Helper.clearScreen();
			System.out.println("No processing order right now.");
			return false;
		}
		
		for (Map.Entry<String,Order> entry : ordersInBranch.entrySet()) {
			Order currentOrder = entry.getValue();
			if (currentOrder.getStatus() == OrderStatus.PROCESSING) {
			
				OrderController.printOrderDetails(currentOrder.getOrderId(), branchName);
			}
		}
		return true;
	}

	public static void viewParticularOrderDetails(String branchName, int opt) { //retrieve the order of a specific OrderID print
		
		Order order = OrderController.promptOrders(branchName, opt);
		
		// if there are no orders with the given Order ID
		
		if (order == null) {
			Helper.clearScreen();
			System.out.println("Order does not exist!");
		}
		
		// order found, print order details
		
		else {
			OrderController.printOrderDetails(order.getOrderId(), branchName);
		}
	}
	
	public static void updateOrderStatus(String branchName, int choice) {// change from Preparing to Ready to Pickup
				
    	Order order = OrderController.promptOrders(branchName, choice);
    	
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
    		scheduleDeletion(order.getOrderId(), branchName, 1*60*1000, ()->{
    			OrderController.deleteOrder(order.getOrderId(), branchName);
    		});
    	}	
	}
	
	public static void scheduleDeletion(String orderId, String branchName, long delayInMillis, Runnable task) {
		Timer timer = Repository.BRANCH.get(branchName).getOrders().get(orderId).getTimer();
		
		timer.schedule(new TimerTask(){
			public void run() {
				task.run();
			}
		}, delayInMillis);
	}
	
	public static void cancelTimer(String orderId, String branchName) {
		Timer timer = Repository.BRANCH.get(branchName).getOrders().get(orderId).getTimer();
		
		timer.cancel();
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
    
    public static Order getOrderByID(String branchName, String orderID){
        	
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

