package view;
import helper.Helper;
import model.Order;
import repository.Repository;

import java.util.Map;

import controller. *;

public class StaffView extends MainView{
	String branchName;
	
	//constructor
	public StaffView(String branchName) {
		this.branchName = branchName;
	}
	
	public void printActions() {
		printBreadCrumbs("Fast Food App View > Staff View");
		System.out.println("(1) Display processing order");
		System.out.println("(2) View particular Order Details");
		System.out.println("(3) Process order");
		System.out.println("(4) Back");
	}
	

	@Override
	public void viewApp() {// OrderController / 2x StaffController + ManagerController ASK ASK ASK
		int opt = -1;
		do {
			printActions();
			opt = Helper.readInt(1,4);
			
			switch(opt) {
				case 1:
				    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Staff View > Display Processing Order");
					StaffController.displayProcessingOrders(this.branchName);
					break;
				case 2:
					Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Staff View > View Order Details");
					System.out.println("Select order to view details:");
					int choice = promptSelectOrderId(this.branchName);
					if(choice == 0) {
						continue;
					}
					else {
						StaffController.viewParticularOrderDetails(this.branchName, choice);
					}
					break;
				case 3:
					Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Staff View > Process Order");
					System.out.println("Select order to process:");
					int selection = promptSelectOrderId(this.branchName);
					if(selection == 0) {
						continue;
					}
					else {
						StaffController.updateOrderStatus(this.branchName, selection);
					}
					break;
			}
		} while(opt != 4);
	}

	
	public static int promptSelectOrderId(String branchName) {
		int i = 1;
		int opt;
		int size = Repository.BRANCH.get(branchName).getOrders().size();
		if(size == 0) {
			System.out.println("No availabe orders");
			return 0;
		}
		
		do {
			for(Map.Entry<String,Order> entry : Repository.BRANCH.get(branchName).getOrders().entrySet()) {
				String orderId = entry.getKey();
				System.out.println("(" + i + ") " + orderId);
				i++;
			}
			opt = Helper.readInt();
		}while(opt<0 || opt>size);
		return opt;
	}
}


