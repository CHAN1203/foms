package view;

import model.Order;
import repository.Repository;
import controller.OrderController;
import helper.Helper;
/**
 * ReceiptView provides the view of the receipt and details of the {@link Order}
 * 
 * @author Jacky
 * @version 1.0
 * @since 2024-04-08
 */
public class ReceiptView extends MainView{
	/**
	 * View Actions for ReceiptView
	 */
	@Override
	protected void printActions() {
		System.out.println("(1) Yes");
		System.out.println("(2) Back");
	}
	/**
	 * View Application for ReceiptView
	 * @param orderId orderId of the order
	 * @param branch branch name of the branch that the customer is currently in
	 */
	public void viewApp(String orderId, String branch) {
		printBreadCrumbs("Fast Food App View > Customer View > Checkout > Payment Confirmation");
		Order currentOrder = Repository.BRANCH.get(branch).getOrders().get(orderId);
		System.out.println("Your total bill is: " + currentOrder.getTotalBill());
		System.out.println("Confirm payment? ");
		printActions();
		int opt = -1;
		do {
			opt = Helper.readInt();
			switch(opt) {
			case 1:
				OrderController.confirmOrder();
				break;
			case 2:
				return;
			default:
				System.out.println("Invalid option. Please Try Again");
			}
			
			
		} while(opt<1 || opt>2);
	}
	/**
	 * Inherited View Application method from parent class
	 */
	@Override
	public void viewApp() {
	}
}
