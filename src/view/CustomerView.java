package view;

import helper.Helper;
import repository.*;
import controller.*;
import enums.OrderStatus;

public class CustomerView extends MainView{
	
	BranchView branchView = new BranchView();
	OrderView orderView;
	PaymentView paymentView = new PaymentView();
	
	public CustomerView() {
		super();
	}

	@Override
	protected void printActions() {
		System.out.println("What would you like to do ?");
		System.out.println("(1) New order");
		System.out.println("(2) Check order status");
		System.out.println("(3) Back");
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
			} while (opt < 1 && opt > 2);
		}
	}
	
}
	
	