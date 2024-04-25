package view;
import helper.Helper;
import model.Branch;
import model.Order;
import repository.Repository;

import java.util.Map;

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
	
	public void viewApp(String branchName) {// OrderController / 2x StaffController + ManagerController ASK ASK ASK
		int opt = -1;
		do {
			printActions();
			opt = Helper.readInt(1,4);
			
			switch(opt) {
				case 1:
					StaffController.displayProcessingOrders(branchName);
					break;
				case 2:
					System.out.println("which order do you want to know the details? Please select the orderId");
					int choice = promptSelectOrderId(branchName);
					if(choice == 0) {
						continue;
					}
					else {
						StaffController.viewParticularOrderDetails(branchName, choice);
					}
					break;
				case 3:
					System.out.println("which order do you want to process?");
					int selection = promptSelectOrderId(branchName);
					if(selection == 0) {
						continue;
					}
					else {
						StaffController.updateOrderStatus(branchName, selection);
					}
					break;
			}
		} while(opt != 4);
	}

	@Override // useless in this case
	public void viewApp() {
		// TODO Auto-generated method stub
		
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


