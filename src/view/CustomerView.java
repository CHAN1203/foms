package view;

import helper.Helper;
import model.*;
import repository.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import controller.*;

public class CustomerView extends MainView{
	
	BranchView branchView = new BranchView();
	PaymentView paymentView = new PaymentView();
	public CustomerView() {
		super();
	}

	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View > Customer View");
		System.out.println("What would you like to do ?");
        System.out.println("(1) Place an order");
        System.out.println("(2) Remove an order");
        System.out.println("(3) Enter remarks");
        System.out.println("(4) Print orders");
        System.out.println("(5) View Shopping Cart");
        System.out.println("(6) Checkout");
        System.out.println("(7) Check order status");
        System.out.println("(8) Exit App");
	}

	@Override
	public void viewApp() {
		int size = Repository.BRANCH.size(); 
		int chosenBranch;
        do { // error handling to ensure user keys in a valid option for number of branches
        	branchView.viewApp();
        	chosenBranch = Helper.readInt();
	        if(chosenBranch < size && chosenBranch > 0) {
	        	break;
	        }
	        else {
	        	System.out.println("Invalid option please try again.");
	        }
	        
        }while(chosenBranch > size || chosenBranch <= 0);
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
				else {
					System.out.println("Successfully created order");
				}
				break;
			case 2:
				Helper.clearScreen();
                printBreadCrumbs("Hotel App View > Order View > Remove an order for OrderId" + orderId);
				if(!removeOrder(orderId, branch)) {
					System.out.println("Remove order unsuccessful");
				}
				else {
					System.out.println("Successfully removed order");
				}
			case 3:
				Helper.clearScreen();
				printBreadCrumbs("Hotel App View > Order View > Enter remarks for OrderId" + orderId);
				System.out.println("Enter remarks for your order");
				String remarks = Helper.readString();
				if(!OrderController.setRemarks(remarks, orderId, branch)) {
					System.out.println("Remarks entered unsuccessful");
				}
				else {
					System.out.println("Successfully entered remarks");
				}
				break;
			case 4:
				Helper.clearScreen();
				printBreadCrumbs("Hotel App View > Customer View > Print all orders");
				OrderController.printOrderDetails(orderId, branch);
				break;
			case 5:
				Helper.clearScreen();
				shoppingCart(orderId, branch);
			case 6:
				checkout(orderId, branch);
				break;
			case 7:
				printBreadCrumbs("Hotel App View > Customer View > Check order status");
				checkOrderStatus(orderId, branch);
				break;
			case 8:
				break;
			default:
				System.out.println("Invalid input");
			}
			if (opt != 8) {
				Helper.pressAnyKeyToContinue();
			}
		} while (opt != 8);
		
		
	}
	
	 private boolean createOrder(String orderId, String branch) {
	        String itemName;
	        int itemAmount;
	        int opt; 
	        
	        Helper.clearScreen();
	        printBreadCrumbs("Hotel App View > Order View > Create order for OrderId " + orderId);
	        String category = promptSelectCategory(branch);
	        HashMap<String, MenuItems> filteredMenu = MenuController.filterMenuItemsByCategory(Repository.BRANCH.get(branch).getMenuItems(), category);
	        
	        int size = filteredMenu.size(); 
	        do {// error handling to ensure user keys in valid option for certain food in a specific category
	        	System.out.println("Select the food to order");
		        MenuController.printMenuByFoodCategory(branch, category);
		        opt = Helper.readInt();
		        if(opt < size && opt > 0) {
		        	break;
		        }
		        else {
		        	System.out.println("Invalid option please try again.");
		        }
		        
	        }while(opt > size || opt <= 0);
	        
	        System.out.println("Enter number of quantity");
	        itemAmount = Helper.readInt();
	        itemName = promptFoodOption(filteredMenu, opt);
	        addOrderItem(itemName, orderId, itemAmount, branch);
	        
	        return true;
	 }
	 
	 private boolean removeOrder(String orderId, String branch) {
		 	String itemName;
	        int itemAmount;
	        int opt; 
	        	        
	        OrderController.printOrderDetails(orderId, branch);
	        
	        int size = Repository.BRANCH.get(branch).getOrders().get(orderId).getCurrentOrders().size();
	        do {// error handling to ensure user selects a valid option of food to remove from their order
	        	System.out.println("Select which food to remove");
		        opt = Helper.readInt();
		        if(opt < size && opt > 0) {
		        	break;
		        }
		        else {
		        	System.out.println("Invalid option please try again.");
		        }
		        
	        }while(opt > size || opt <= 0);
	        
	        System.out.println("Enter number of quantity");
	        itemAmount = Helper.readInt();
	        
	        itemName = promptRemoveFood(Repository.BRANCH.get(branch).getOrders().get(orderId).getCurrentOrders(), opt);
	        removeOrderItem(itemName, orderId, itemAmount, branch);
	        
	        return true;
	 }

	 private void shoppingCart(String orderId, String branch) {
		 printShoppingCart(orderId, branch);
		 int opt;
		 do {
			 opt = Helper.readInt();
			 switch (opt) {
			 
			 	case 1:
			 		if(!createOrder(orderId, branch)) {
						System.out.println("Create order unsuccessful");
					}
					else {
						System.out.println("Successfully created order");
					}
					break;
			 	case 2:
			 		Helper.clearScreen();
	                printBreadCrumbs("Hotel App View > Order View > Remove an order for OrderId" + orderId);
					if(!removeOrder(orderId, branch)) {
						System.out.println("Remove order unsuccessful");
					}
					else {
						System.out.println("Successfully removed order");
					}
			 	case 3:
			 		Helper.clearScreen();
					printBreadCrumbs("Hotel App View > Order View > Enter remarks for OrderId" + orderId);
					System.out.println("Enter remarks for your order");
					String remarks = Helper.readString();
					if(!OrderController.setRemarks(remarks, orderId, branch)) {
						System.out.println("Remarks entered unsuccessful");
					}
					else {
						System.out.println("Successfully entered remarks");
					}
					break;
			 	case 4:
			 		Helper.clearScreen();
			 		printBreadCrumbs("Hotel App View > Order View > Checkout view for OrderId" + orderId);
			 		checkout(orderId, branch);
			 }
		 }while(opt != 4);
	 }
	 
	 private void printShoppingCart(String orderId, String branch) {
		 System.out.println("Shopping Cart: ");
		 OrderController.printOrderDetails(orderId, branch);
		 System.out.println();
		 System.out.println("What would you like to do?");
		 System.out.println("(1) Add an order");
		 System.out.println("(2) Remove an order");
		 System.out.println("(3) Enter remarks");
		 System.out.println("(4) Checkout");
	 }
	 
	 
	 private String promptSelectCategory(String branch) {
		 int categoryChoice;
		 int size = Repository.BRANCH.get(branch).getFoodCategoryList().size();
	        do {// error handling to ensure users selects a valid category from our total number of category
	        	System.out.println("Please select a food category to view: ");
	        	MenuController.printFoodCategory(branch);
		        categoryChoice = Helper.readInt();
		        if(categoryChoice < size && categoryChoice > 0) {
		        	break;
		        }
		        else {
		        	System.out.println("Invalid option please try again.");
		        }
		        
	        }while(categoryChoice > size || categoryChoice <= 0);
		 return Repository.BRANCH.get(branch).getFoodCategoryList().get(categoryChoice -1);
	 }
	 
	private void checkOrderStatus(String orderId, String branch) {
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
	
	private void removeOrderItem(String name, String orderId, int amount, String branch) {
		if(OrderController.removeOrderItem(name, orderId, amount, branch)) {
			System.out.printf("\"%s\" removed from order SUCCESSFULLY\n", name);
		}
		else {
			System.out.printf("Removal from order FAILED (\"%s\" NOT FOUND in order\\ removal quantity > current quantity)\n", name);
		}
	}
	
	private String promptBranch(int opt) {
		Iterator<Map.Entry<String, Branch>> iteratedBranch = Repository.BRANCH.entrySet().iterator();
		int i = 0;
		for(i=0; i<opt; i++) {
			iteratedBranch.next();
		}
		Map.Entry<String, Branch> SelectedBranch = iteratedBranch.next();
		Branch chosenBranch = SelectedBranch.getValue();
		String branch = chosenBranch.getName();
		return branch;
	}
	
	private String promptFoodOption(HashMap<String, MenuItems> menuItems, int opt) {
		Iterator<Entry<String, MenuItems>> iteratedFood= menuItems.entrySet().iterator();
		int i = 0;
		for(i=0; i<opt; i++) {
			iteratedFood.next();
		}
		Entry<String, MenuItems> SelectedFood = iteratedFood.next();
		MenuItems chosenFood = SelectedFood.getValue();
		String itemName = chosenFood.getName();
		return itemName;
	}
	
	private String promptRemoveFood(HashMap<MenuItems, Integer> currentOrders, int opt) {
		Iterator<Entry<MenuItems, Integer>> iteratedRemove = currentOrders.entrySet().iterator();
		int i = 0;
		for(i=0; i<opt; i++) {
			iteratedRemove.next();
		}
		Entry<MenuItems, Integer> toBeRemovedFood = iteratedRemove.next();
		MenuItems chosenFoodToBeRemoved = toBeRemovedFood.getKey();
		String itemName = chosenFoodToBeRemoved.getName();
		return itemName;
	}
	
	private void checkout(String orderId, String branch) {
		System.out.println("Shopping cart: ");
		OrderController.printOrderDetails(orderId, branch);
		paymentView.viewApp();
	}
	
}
