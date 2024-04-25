package view;
import helper.Helper;
import model.Order;
import repository.Repository;
import java.util.Map;
import controller. *;
/**
 * StaffView provides the view for a Staff
 * 
 * @author Yue Hang, Shermin, Hong Sheng
 * @version 1.0
 * @since 2024-04-04
 */
public class StaffView extends MainView{

	String branch;
	/**
	 * Default constructor of StaffView
	 */
	public StaffView(String branch) {
		this.branch = branch;
	}

	/**
	 * View Actions for StaffView
	 */
	public void printActions() {
		printBreadCrumbs("Fast Food App View > Staff View");
		System.out.println("(1) Display processing order");
		System.out.println("(2) View particular Order Details");
		System.out.println("(3) Process order");
		System.out.println("(4) Back");
	}
	
	/**
	 * View Application of StaffView
	 * @param branch branch name of the branch that the staff is currently in
	 */
	@Override
	public void viewApp() {
		int opt = -1;
		do {
			printActions();
			opt = Helper.readInt(1,4);
			
			switch(opt) {
				case 1:
				    Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Staff View > Display Processing Order");
					StaffController.displayProcessingOrders(this.branch);
					break;
				case 2:
					Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Staff View > View Order Details");
					System.out.println("Select order to view details:");
					int choice = promptSelectOrderId(this.branch);
					if(choice == 0) {
						continue;
					}
					else {
						StaffController.viewParticularOrderDetails(this.branch, choice);
					}
					break;
				case 3:
					Helper.clearScreen();
                    printBreadCrumbs("Fast Food App View > Staff View > Process Order");
					System.out.println("Select order to process:");
					int selection = promptSelectOrderId(this.branch);
					if(selection == 0) {
						continue;
					}
					else {
						StaffController.updateOrderStatus(this.branch, selection);
					}
					break;
			}
		} while(opt != 4);
	}


	/**
	 * The function that prompts a staff to select orderId 
	 * @param branch branch name of the branch that the customer is currently in
	 * @return
	 */
	public static int promptSelectOrderId(String branch) {
		int opt;
		int size = Repository.BRANCH.get(branch).getOrders().size();
		if(size == 0) {
			System.out.println("No availabe orders");
			return 0;
		}
		
		do {
			for(Map.Entry<String,Order> entry : Repository.BRANCH.get(branch).getOrders().entrySet()) {
				int i = 1;
				String orderId = entry.getKey();
				Order orders = entry.getValue();
				System.out.println("(" + i + ") " + orderId + "    Status: " + orders.getStatus() + "    Dine in option: " + orders.getOption());
				i++;
			}
			opt = Helper.readInt();
			if(opt<=0 || opt>size) {
				System.out.println("Invalid option. Please try again");
			}
		}while(opt<=0 || opt>size);
		return opt;
	}
}


