package view;
import helper.Helper;
import controller. *;

public class StaffView extends MainView{
	//constructor
	public StaffView() {
	}
	
	public void printActions() {
		System.out.println("1. Display New Order");
		System.out.println("2. View Particular Order Details");
		System.out.println("3. Process Order");
		System.out.println("4. Quit");
		
	}
	
	public void viewApp() {// OrderController / 2x StaffController + ManagerController ASK ASK ASK
		int opt = -1;
		do {
			printActions();
			opt = Helper.readInt(1,6);
			switch(opt) {
				case 1:
					StaffController.displayProcessingOrders(branchName);
					break;
				case 2:
					System.out.println("which order do you want to know the details? Please enter an order ID");
					int orderId = Helper.readInt();
					StaffController.viewParticularOrderDetails(orderId);
					break;
				case 3:
					System.out.println("which order do you want to process?");
					int orderID = Helper.readInt();
					StaffController.processOrder(orderId);
					break;
			}
		}while(opt != 6);
	}
}


