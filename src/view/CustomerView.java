package view;

import helper.Helper;
import model.*;
import repository.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
        System.out.println("(4) Remove remarks");
        System.out.println("(5) Print orders");
        System.out.println("(6) View Shopping Cart");
        System.out.println("(7) Checkout");
        System.out.println("(8) Check order status");
        System.out.println("(9) Back");
	}

	@Override
	public void viewApp() {
		int size = Repository.BRANCH.size(); 
		int chosenBranch = -1;
        do { // error handling to ensure user keys in a valid option for number of branches
        	branchView.viewApp();
        	System.out.println("(" + (size+1) + ") Back" );
        	chosenBranch = Helper.readInt();
	        if(chosenBranch <= size && chosenBranch > 0) {
	        	break;
	        }
	        else if (chosenBranch == size+1){
	        	return;
	        }
	        else {
	        	System.out.println("Invalid option. Please try again.");
	        }
	        
        }while(chosenBranch > size || chosenBranch <= 0);
		String branch = BranchController.promptBranch(chosenBranch);
		String orderId = OrderController.createOrder(branch);
		int opt = -1;
		do {
			printActions();
			opt = Helper.readInt(1,9);
			switch (opt) {
			case 1:
				createOrder(orderId, branch);
				Helper.pressAnyKeyToContinue();
				break;
			case 2:
                printBreadCrumbs("Fast Food App View > Order View > Remove an order for OrderId" + orderId);
                removeOrder(orderId, branch);
				Helper.pressAnyKeyToContinue();
				break;
			case 3:
				//Helper.clearScreen();
				printBreadCrumbs("Fast Food App View > Order View > Enter remarks for OrderId" + orderId);
				System.out.println("Enter remarks for your order");
				String remarks = Helper.readString();
				setRemarks(remarks,orderId,branch);
				Helper.pressAnyKeyToContinue();
				break;
			case 4:
				printBreadCrumbs("Fast Food App View > Order View > Remove remarks for OrderId" + orderId);
				OrderController.printOrderDetails(orderId, branch);
				removeRemarks(orderId,branch);
				Helper.pressAnyKeyToContinue();
			case 5:
				//Helper.clearScreen();
				printBreadCrumbs("Fast Food App View > Customer View > Print all orders");
				OrderController.printOrderDetails(orderId, branch);
				Helper.pressAnyKeyToContinue();
				break;
			case 6:
				//Helper.clearScreen();
				printBreadCrumbs("Fast Food App View > Order View > Shopping Cart View");
				shoppingCart(orderId, branch);
				Helper.pressAnyKeyToContinue();
				break;
			case 7:
				printBreadCrumbs("Fast Food App View > Order View > Check Out View");
				checkout(orderId, branch);
				Helper.pressAnyKeyToContinue();
				break;
			case 8:
				printBreadCrumbs("Fast Food App View > Customer View > Check order status");
				checkOrderStatus(orderId, branch);
				Helper.pressAnyKeyToContinue();
				break;
			case 9:
				break;
			}
		} while (opt != 9);
		
		
	}
	
	 private void createOrder(String orderId, String branch) {
	        String itemName;
	        int itemAmount;
	        int opt = -1; 
	        
	        //Helper.clearScreen();
	        printBreadCrumbs("Fast Food App View > Order View > Create order for OrderId " + orderId);
	        String category = promptSelectCategory(branch);
	        HashMap<String, MenuItem> filteredMenu = MenuController.filterMenuItemsByCategory(Repository.BRANCH.get(branch).getMenuItems(), category);
	        
	        int size = filteredMenu.size(); 
	        do {// error handling to ensure user keys in valid option for certain food in a specific category
		        MenuController.printMenuByFoodCategory(branch, category);
		        System.out.println("Select the food to order:");
		        opt = Helper.readInt();
		        if(opt <= size && opt > 0) {
		        	break;
		        }
		        else {
		        	System.out.println("Invalid option. Please try again.");
		        }
		        
	        }while(opt > size || opt <= 0);
	        
	        System.out.println("Enter number of quantity:");
	        itemAmount = Helper.readInt();
	        itemName = promptFoodOption(filteredMenu, opt);
	        addOrderItem(itemName, orderId, itemAmount, branch);
	 }
	 
	 private void removeOrder(String orderId, String branch) {
		 	String itemName;
	        int itemAmount;
	        int opt = -1; 
	        	        
	        OrderController.printOrderDetails(orderId, branch);
	        
	        int size = Repository.BRANCH.get(branch).getOrders().get(orderId).getCurrentOrders().size();
	        do {// error handling to ensure user selects a valid option of food to remove from their order
	        	System.out.println("Select which food to remove: ");
		        opt = Helper.readInt();
		        if(opt < size && opt > 0) {
		        	break;
		        }
		        else {
		        	System.out.println("Invalid option. Please try again.");
		        }
		        
	        }while(opt > size || opt <= 0);
	        
	        System.out.println("Enter number of quantity:");
	        itemAmount = Helper.readInt();
	        
	        itemName = promptRemoveFood(Repository.BRANCH.get(branch).getOrders().get(orderId).getCurrentOrders(), opt);
	        removeOrderItem(itemName, orderId, itemAmount, branch);
	        
	 }

	 private void shoppingCart(String orderId, String branch) {
		 int opt = -1;
		 do {
			 printShoppingCart(orderId, branch);
			 opt = Helper.readInt(1,6);
			 switch (opt) {
			 
			 	case 1:
					createOrder(orderId, branch);
					Helper.pressAnyKeyToContinue();
					break;
			 	case 2:
			 		printBreadCrumbs("Fast Food App View > Order View > Remove an order for OrderId" + orderId);
	                removeOrder(orderId, branch);
					Helper.pressAnyKeyToContinue();
					break;
			 	case 3:
			 		//Helper.clearScreen();
			 		printBreadCrumbs("Fast Food App View > Order View > Enter remarks for OrderId" + orderId);
					System.out.println("Enter remarks for your order");
					String remarks = Helper.readString();
					setRemarks(remarks,orderId,branch);
					Helper.pressAnyKeyToContinue();
					break;
			 	case 4:
			 		printBreadCrumbs("Fast Food App View > Order View > Remove remarks for OrderId" + orderId);
					OrderController.printOrderDetails(orderId, branch);
					if(!removeRemarks(orderId, branch)) {
						System.out.println("Remarks removal unsuccessful");
					} else {
						System.out.println("Successfully removed remarks");
					}
			 	case 5:
			 		//Helper.clearScreen();
			 		printBreadCrumbs("Fast Food App View > Order View > Checkout view for OrderId" + orderId);
			 		checkout(orderId, branch);
			 		break;
			 	case 6:
			 		return;
			 }
		 } while(opt != 6);
	 }
	 
	 private void printShoppingCart(String orderId, String branch) {
		 System.out.println("Shopping Cart: ");
		 OrderController.printOrderDetails(orderId, branch);
		 System.out.println();
		 System.out.println("What would you like to do?");
		 System.out.println("(1) Add an order");
		 System.out.println("(2) Remove an order");
		 System.out.println("(3) Enter remarks");
		 System.out.println("(4) Remove remarks");
		 System.out.println("(5) Checkout");
		 System.out.println("(6) Back");
	 }
	 
	 
	 private String promptSelectCategory(String branch) {
		 int categoryChoice;
		 int size = Repository.BRANCH.get(branch).getFoodCategoryList().size();
		 System.out.println(size);
	        do {// error handling to ensure users selects a valid category from our total number of category
	        	System.out.println("Please select a food category to view: ");
	        	MenuController.printFoodCategory(branch);
		        categoryChoice = Helper.readInt();
		        if(categoryChoice <= size && categoryChoice > 0) {
		        	break;
		        }
		        else {
		        	System.out.println("Invalid option. Please try again.");
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
	
	private String promptFoodOption(HashMap<String, MenuItem> menuItems, int opt) {
		Iterator<Entry<String, MenuItem>> iteratedFood= menuItems.entrySet().iterator();
		int i = 1;
		for(i=1; i<opt; i++) {
			iteratedFood.next();
		}
		Entry<String, MenuItem> SelectedFood = iteratedFood.next();
		MenuItem chosenFood = SelectedFood.getValue();
		String itemName = chosenFood.getName();
		return itemName;
	}
	
	private String promptRemoveFood(HashMap<MenuItem, Integer> currentOrders, int opt) {
		Iterator<Entry<MenuItem, Integer>> iteratedRemove = currentOrders.entrySet().iterator();
		int i = 1;
		for(i=1; i<opt; i++) {
			iteratedRemove.next();
		}
		Entry<MenuItem, Integer> toBeRemovedFood = iteratedRemove.next();
		MenuItem chosenFoodToBeRemoved = toBeRemovedFood.getKey();
		String itemName = chosenFoodToBeRemoved.getName();
		return itemName;
	}
	
	private void checkout(String orderId, String branch) {
		System.out.println("Shopping cart: ");
		OrderController.printOrderDetails(orderId, branch);
		paymentView.viewApp();
	}
	
	private void removeRemarks(String orderId, String branch) {
		int opt = -1;
		int size = Repository.BRANCH.get(branch).getOrders().get(orderId).getRemarks().size();
		do {
			System.out.println("Select which remarks you would like to remove");
			opt = Helper.readInt();
			if(opt<=0 || opt>size) {
				System.out.println("Invalid option. Please try again");
			}
		}while(opt<=0 || opt>size);
		if(promptRemoveRemarks(orderId, branch, opt)) {
			System.out.println("Successfully removed remarks!");
		}
		else {
			System.out.println("Remarks removal unsuccessful");
		}
		System.out.println();
		OrderController.printOrderDetails(orderId, branch);
	}
	
	private boolean promptRemoveRemarks(String orderId, String branch, int opt) {
		List<String> remarksList = Repository.BRANCH.get(branch).getOrders().get(orderId).getRemarks();
		if(remarksList.get(0).equals("No Remarks")) {
			return false;
		}
		else {
			remarksList.remove(opt -1);
		}
		
		if(remarksList.size() == 0) {
			remarksList.add("No Remarks");
		}
		return true;
	}
	
	private void setRemarks(String remarks, String orderId, String branch) {
		if(!OrderController.addRemarks(remarks, orderId, branch)) {
			System.out.println("Remarks entered unsuccessful");
		}
		else {
			System.out.println("Successfully entered remarks");
		}
	}
}
