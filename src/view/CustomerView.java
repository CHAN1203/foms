package view;

import helper.Helper;
import model.Branch;
import model.MenuItems;
import repository.Repository;
import view. *;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import controller.*;

public class CustomerView extends MainView{// choose branch first
	
	BranchView branchView = new BranchView();
	
	public CustomerView() {
		super();
	}

	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View > Customer View");
		System.out.println("What would you like to do ?");
        System.out.println("(1) Place an order");
        System.out.println("(2) Remove an order");
        System.out.println("(3) Edit your order");
        System.out.println("(4) Print orders");
        System.out.println("(5) View Shopping Cart"); // have 3 options, edit order, remove order, checkout
        System.out.println("(6) Checkout");// paymentview
        System.out.println("(7) Check order status");
        System.out.println("(8) Exit App");
	}

	@Override
	public void viewApp() {
		branchView.viewApp();
		int chosenBranch;
		chosenBranch = Helper.readInt();
		String branch = promptBranch(chosenBranch);
		String orderId = OrderController.createOrder(branch);
		printActions();
		int opt;
		do {
			opt = Helper.readInt(1,4);
			switch (opt) {
			case 1:
				if(!createOrder(orderId, branch)) {
					System.out.println("Create order unsuccessful");
				}
				break;
			case 2:
				Helper.clearScreen();
                printBreadCrumbs("Hotel App View > Order View > Remove an order for OrderId" + orderId + " > Remove menu items");
				if(!removeOrder(orderId, branch)) {
					System.out.println("Remove order unsuccessful");
				}


				
			case 2:
				Helper.clearScreen();
				printBreadCrumbs("Hotel App View > Customer View > Check order status");
				checkOrderStatus(branch);
				break;
			case 3:
				Helper.clearScreen();
				printBreadCrumbs("Hotel App View > Customer View > Print all orders");
				OrderController.printAllOrders(branch);
				break;
			case 4:
				System.out.println("Exiting App...");
				System.exit(0);
			default:
				System.out.println("Invalid input");
			}
			if (opt != 4) {
				Helper.pressAnyKeyToContinue();
			}
		} while (opt != 4);
		
		
	}
	
	 private boolean createOrder(String orderId, String branch) {
	        String itemName;
	        int itemAmount;
	        int opt; 
	        
	        Helper.clearScreen();
	        printBreadCrumbs("Hotel App View > Order View > Create order for OrderId " + orderId);
	        String category = promptSelectCategory(branch);
	        
	        System.out.println("Select the food to order");
	        MenuController.printMenuByCategory(branch, category);
	        opt = Helper.readInt();
	        System.out.println("Enter number of quantity");
	        itemAmount = Helper.readInt();
	        
	        HashMap<String, MenuItems> filteredMenu = MenuController.filterMenuItemsByCategory(Repository.BRANCH.get(branch).getMenuItems(), category);
	        itemName = promptFoodOption(filteredMenu, opt);
	        addOrderItem(itemName, orderId, itemAmount, branch);
	        
	        return true;
	 }
	 
	 public boolean removeOrder(String orderId, String branch) {
		 	String itemName;
	        int itemAmount;
	        int opt; 
	        
	        OrderController.printOrderDetails(orderId, branch);
	        
	        
	        System.out.println("Select the food to order");
	        MenuController.printMenuByCategory(branch, category);
	        opt = Helper.readInt();
	        System.out.println("Enter number of quantity");
	        itemAmount = Helper.readInt();
	        
	        HashMap<String, MenuItems> filteredMenu = MenuController.filterMenuItemsByCategory(Repository.BRANCH.get(branch).getMenuItems(), category);
	        itemName = promptFoodOption(filteredMenu, opt);
	        removeOrderItem(itemName, orderId, itemAmount, branch);
	        
	        return true;
//       System.out.println("Enter item to be removed:\r");
//       itemName = Helper.readString();
//       System.out.println("Enter amount to be removed:\r");
//       itemAmount = Helper.readInt();
//       removeOrderItem(itemName, orderId, itemAmount);
	 }
	            //printCreateOrderMenu();
//	            option = Helper.readInt(1, 6);
//	            switch (option) {
//	                case 1:
//	                    Helper.clearScreen();
//	                    printBreadCrumbs("Hotel App View > Order View > Create an order for OrderId " + orderId + " > Print food category" );
//	                    String category = promptSelectCategory(branch); 
//	                    MenuController.printMenuByCategory(branch, category);
//	                    break;
//	                case 2:
//	                    Helper.clearScreen();
//	                    printBreadCrumbs("Hotel App View > Order View > Create an order for OrderId " +orderId + " > Add menu items");
//	                    promptAddMenuItem();
////	                    System.out.println("Enter item to be added:\r");
////	                    itemName = Helper.readString();
////	                    System.out.println("Enter amount to be added:\r");
////	                    itemAmount = Helper.readInt();
//	                    addOrderItem(itemName, orderId, itemAmount);
//	                    break;
//	                case 3:
//	                    Helper.clearScreen();
//	                    printBreadCrumbs("Hotel App View > Order View > Create an order for Room " + roomId + " > Remove menu items");
//	                    System.out.println("Enter item to be removed:\r");
//	                    itemName = Helper.readString();
//	                    System.out.println("Enter amount to be removed:\r");
//	                    itemAmount = Helper.readInt();
//	                    removeOrderItem(itemName, orderId, itemAmount);
//	                    break;
//	                case 4:
//	                    Helper.clearScreen();
//	                    printBreadCrumbs("Hotel App View > Order View > Create an order for Room " + roomId + " > Print order");
//	                    OrderManager.printOrderDetails(orderId);
//	                    break;
//	                case 5:
//	                    Helper.clearScreen();
//	                    printBreadCrumbs("Hotel App View > Order View > Create an order for Room " + roomId + " > Enter remarks");
//	                    System.out.println("Enter remarks:\r");
//	                    String remarks = Helper.readString();
//	                    OrderManager.setRemarks(remarks, orderId);
//	                    System.out.println("Remarks given");
//	                    break;
//	                case 6:
//	                    Helper.clearScreen();
//	                    printBreadCrumbs("Hotel App View > Order View > Create an order for Room " + roomId + " > Checkout");
//	                    confirmOrder(orderId);
//	                    CartView.viewApp();
//	                    break;
//	                default:
//	                    System.out.println("Invalid option");
//	                    break;
//	            }
//	            if (option != 6){
//	                Helper.pressAnyKeyToContinue();
//	            }
//	        } while (option != 6);
//	        return true;
	 
	 private String promptSelectCategory(String branch) {
		 System.out.println("Please select a food category to view: ");
		 MenuController.printFoodCategory();
		 int categoryChoice = Helper.readInt();
		 return Repository.BRANCH.get(branch).getFoodCategoryList().get(categoryChoice -1);
	 }
	 
	 private void printCreateOrderMenu() {
	        System.out.println("Please enter an option (1-6)");
	        System.out.println("(1) Print menu");
	        System.out.println("(2) Add menu items");
	        System.out.println("(3) Remove menu items");
	        System.out.println("(4) Print order");
	        System.out.println("(5) Enter remarks");
	        System.out.println("(6) Checkout");
	    }
	
	public void checkOrderStatus(String orderId, String branch) {
		if(!OrderController.validateOrderId(orderId, branch)) {
			System.out.println("Order ID not found");
		}
		else {
			System.out.println(OrderController.checkOrderStatus(orderId, branch));
		}
	}
	
	private void addOrderItem(String name, String orderId, int amount, String branch){
        if (OrderController.addOrderItem(name, orderId, amount, branch)){
            System.out.printf("\"%s\" added to order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to order FAILED (\"%s\" NOT FOUND in menu)\n", name);
        };
    }
	
	private void removeOrderItem(String name, String orderId, int amount) {
		if(OrderController.removeOrderItem(name, orderId, amount, orderId)) {
			System.out.printf("\"%s\" removed from order SUCCESSFULLY\n", name);
		}
		else {
			System.out.printf("Removal from order FAILED (\"%s\" NOT FOUND in order\\ removal quantity > current quantity)\n", name);
		}
	}
	
	public String promptBranch(int opt) {
		Iterator<Map.Entry<String, Branch>> iterator = Repository.BRANCH.entrySet().iterator();
		int i = 0;
		for(i=0; i<opt; i++) {
			iterator.next();
		}
		Map.Entry<String, Branch> iteratedBranch = iterator.next();
		Branch chosenBranch = iteratedBranch.getValue();
		String branch = chosenBranch.getName();
		return branch;
	}
	
	public String promptFoodOption(HashMap<String, MenuItems> menuItems, int opt) {
		Iterator<Entry<String, MenuItems>> iterator = menuItems.entrySet().iterator();
		int i = 0;
		for(i=0; i<opt; i++) {
			iterator.next();
		}
		Entry<String, MenuItems> iteratedBranch = iterator.next();
		MenuItems chosenMenuItem = iteratedBranch.getValue();
		String itemName = chosenMenuItem.getName();
		return itemName;
	}
}
