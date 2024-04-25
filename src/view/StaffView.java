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
	/**
	 * Default constructor of StaffView
	 */
	public StaffView() {
	}
	/**
	 * View Actions for StaffView
	 */
	public void printActions() {
		System.out.println("1. Display New Order");
		System.out.println("2. View Particular Order Details");
		System.out.println("3. Process Order");
		System.out.println("4. Quit");
	}
	/**
	 * View Application of StaffView
	 * @param branch branch name of the branch that the staff is currently in
	 */
	public void viewApp(String branch) {
		int opt = -1;
		do {
			printActions();
			opt = Helper.readInt(1,4);
			
			switch(opt) {
				case 1:
					StaffController.displayProcessingOrders(branch);
					break;
				case 2:
					System.out.println("which order do you want to know the details? Please select the orderId");
					int choice = promptSelectOrderId(branch);
					if(choice == 0) {
						continue;
					}
					else {
						StaffController.viewParticularOrderDetails(branch, choice);
					}
					break;
				case 3:
					System.out.println("which order do you want to process?");
					int selection = promptSelectOrderId(branch);
					if(selection == 0) {
						continue;
					}
					else {
						StaffController.updateOrderStatus(branch, selection);
					}
					break;
			}
		} while(opt != 4);
	}
	/**
	 * Inherited View Application from parent class
	 */
	@Override 
	public void viewApp() {
	}
	/**
	 * The function that prompts a staff to select orderId 
	 * @param branch branch name of the branch that the customer is currently in
	 * @return
	 */
	public static int promptSelectOrderId(String branch) {
		int i = 1;
		int opt;
		int size = Repository.BRANCH.get(branch).getOrders().size();
		if(size == 0) {
			System.out.println("No availabe orders");
			return 0;
		}
		
		do {
			for(Map.Entry<String,Order> entry : Repository.BRANCH.get(branch).getOrders().entrySet()) {
				String orderId = entry.getKey();
				System.out.println("(" + i + ") " + orderId);
				i++;
			}
			opt = Helper.readInt();
		}while(opt<0 || opt>size);
		return opt;
	}
}


