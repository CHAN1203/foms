package view;

import helper.Helper;
import repository.*;
import controller.*;
import enums.OrderStatus;
/**
 * CustomerView provides the view to take user input for order related process
 * which calls {@link OrderView}
 * @author Hong Sheng, Jacky
 * @version 1.0
 * @since 2024-04-08
 */
public class CustomerView extends MainView{
	/**
	 * Constructing of required View Classes
	 */
	BranchView branchView = new BranchView();
	OrderView orderView;
	PaymentView paymentView = new PaymentView();
	/**
	 * Default constructor of the CustomerView
	 */
	public CustomerView() {
		super();
	}
	/**
	 * View Actions of the CustomerView
	 */
	@Override
	protected void printActions() {
		System.out.println("What would you like to do ?");
		System.out.println("(1) New order");
		System.out.println("(2) Check order status");
		System.out.println("(3) Back");
	}
	/**
	 * View Application of CustomerView which calls {@link BranchView}
	 */
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
		String orderId;
		int opt = -1;
		do {
			printBreadCrumbs("Fast Food App View > Customer View > " + branch);
			printActions();
			opt = Helper.readInt(1,3);
			switch (opt) {
			case 1:
				orderId = OrderController.createOrder(branch);
				orderView = new OrderView(branch, orderId);
				orderView.viewApp();
				break;
			case 2:
				printBreadCrumbs("Fast Food App View > Customer View > " + branch + "  Check order status");
				System.out.println("Enter order ID:");
				orderId = Helper.readString();
				checkOrderStatus(orderId, branch);
				Helper.pressAnyKeyToContinue();
				break;
			case 3:
				break;
			}
		} while (opt != 3);
		
		
	}
	/**
	 * The function that checks order status of a particular OrderID through {@link OrderController}
	 * and prints out further actions
	 * @param orderId The orderId used to access the particular order
	 * @param branch The branch name of the customer
	 */
	private void checkOrderStatus(String orderId, String branch) {
		if(!OrderController.validateOrderId(orderId, branch)) {
			System.out.println("Order ID not found");
			return;
		}
		OrderStatus status = OrderController.checkOrderStatus(orderId, branch);
		System.out.println(OrderController.checkOrderStatus(orderId, branch));
		if (status.equals(OrderStatus.READYFORPICKUP)) {
			System.out.println("Do you want to pick up?");
			int opt = -1;
			
			do {
				System.out.println("(1) Yes");
				System.out.println("(2) Back");
				opt = Helper.readInt(1,2);
				switch (opt) {
				case 1:
					OrderController.updateStatus(OrderStatus.COMPLETED, orderId, branch);
					StaffController.cancelTimer(orderId, branch);
					break;
				case 2:
					break;
				}
			} while (opt != 2);
		}
	}
	
}
	
	