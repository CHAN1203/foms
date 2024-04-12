package view;

import helper.Helper;
import view.CustomerView;
import view.MenuView;

public class CustomerView extends MainView{// choose branch first
	
	MenuView menuView = new MenuView();
	
	public CustomerView() {
		super();
	}

	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View > Customer View");
		System.out.println("What would you like to do ?");
        System.out.println("(1) Place a new order");//under NTU branch
        System.out.println("(2) Check order status");
        System.out.println("(3) Exit App");
	}

	@Override
	public void viewApp() {
		printActions();
		int opt;
		do {
			opt = Helper.readInt();
			switch (opt) {
			case 1:
				placeOrder();
				break;
			case 2:
				checkOrderStatus();
				
				break;
			case 3:
				System.out.println("Exiting App...");
				System.exit(0);
			
			}
		} while (opt != 3);
		
		
	}
	
	public void placeOrder() {
		menuView.viewApp();
	}
	
	public void checkOrderStatus() {
		if(OrderManager.checkOrderStatus(orderID)) {
			
		}
		else {
			System.out.println("Order does not exist");
		}//how to implement orderID and how do we retrieve? Repository.Orders.get(OrderID)?
	}
	
	
}
