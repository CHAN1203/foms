package controller;
import helper.Helper;
import repository.Repository;
import enums. *;
import model. *;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Yue Hang
 * @version 4.0
 * @since 2024-04-01
 */
public class StaffController {

	/**
	 * Display all orders that are of the status PROCESSING in this branch
	 * @param branchName Branch Name
	 * @return {@code true} if there exist orders within the branch, {@code false} Otherwise
	 */
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

	/**
	 * Takes in order ID and displays the order details to user
	 * @param branchName Name of Branch
	 * @param opt Option input from user
	 */
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
	
	/**
	 * Checks if order exists, if exist, update the order status
	 * @param branchName Name of Branch
	 * @param choice Choice input from user
	 */
	public static void updateOrderStatus(String branchName, int choice) {// change from Preparing to Ready to Pickup
				
    	Order order = OrderController.promptOrders(branchName, choice);
    	
    	// if order does not exist
    	
    	if (order == null) {
    		Helper.clearScreen();
    		System.out.println("Order does not exist!");
    	}
    	
    	// order exists
    	
    	else {
    		if(order.getStatus()==OrderStatus.PROCESSING){
    			order.setStatus(OrderStatus.READYFORPICKUP);
    			Helper.clearScreen();
        		System.out.println("Order is ready to pickup!");
        		scheduleDeletion(order.getOrderId(), branchName, 1*60*1000, ()->{
        			OrderController.deleteOrder(order.getOrderId(), branchName);
        		});	
    		} else if(order.getStatus() == OrderStatus.READYFORPICKUP || order.getStatus() == OrderStatus.COMPLETED) {
    			System.out.println("Order cannot be further processed!");
    			return;
    		}
    	}	
	}
	
	/**
	 * Schedule time for the order to be deleted after being picked up
	 * @param orderId Order ID
	 * @param branchName Name of Branch
	 * @param delayInMillis Time delay in milliseconds
	 * @param task Task given
	 */
	public static void scheduleDeletion(String orderId, String branchName, long delayInMillis, Runnable task) {
		Timer timer = Repository.BRANCH.get(branchName).getOrders().get(orderId).getTimer();
		
		timer.schedule(new TimerTask(){
			public void run() {
				task.run();
			}
		}, delayInMillis);
	}
	
	/**
	 * Cancel the timer on the order
	 * @param orderId Order ID
	 * @param branchName Name of Branch
	 */
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
    
    /**
     * Takes in orderID, returns the order object if it exists
     * @param branchName Name of Branch
     * @param orderID Order ID
     * @return the Order object if the orderID exists, else, return NULL
     */
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
    
    /**
     * Returns all the orders in the branch in the form of a hashmap
     * @param branchName Name of Branch
     * @return returns the hashmap containing all the orders in the branch
     */
    public static HashMap<String, Order> getOrdersInBranch(String branchName){ // return orders in the specified branch
    	Branch branch = Repository.BRANCH.get(branchName);
    	HashMap<String, Order> ordersInBranch = branch.getOrders(); // create a hashmap with all the orders in the current branch <OrderID (string), Order (object)>
    	return ordersInBranch;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

