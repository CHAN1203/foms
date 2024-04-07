package view;
import helper.Helper;

public class StaffView extends MainView{
	//constructor
	public StaffView() {
	}
	
	public void printActions() {
		System.out.println("1. Display New Order");
		System.out.println("2. View Order Details");
		System.out.println("3. Process Order");
		System.out.println("4. Quit");
		
	}
	
	public void viewApp() {
		int opt = -1;
		do {
			printActions();
			opt = Helper.readInt(1,6);
			switch(opt) {
				case 1:
					displayNewOrder();
				case 2:
					System.out.println("which order do you want to know the details? Please enter an order ID");
					int orderId = Helper.readInt();
					viewOrderDetails(orderId);
				case 3:
					processOrder(orderId);
			}
		}while(opt != 6);
	}
	
	private void displayNewOrder() {
		
	}
	
	
}


