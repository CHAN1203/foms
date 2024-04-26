package view;

import helper.Helper;
import repository.Repository;
/**
 * PaymentView provides the view for customers to make payment
 * 
 * @author Jacky, Hong Sheng
 * @version 1.0
 * @sincec 2024-04-06
 */
public class PaymentView extends MainView{
	/**
	 * orderId of PaymentView
	 */
	private String orderId; 
	/**
	 * branch of PaymentView
	 */
	private String branch;
	/**
	 * Constructing the required View Class
	 */
	private ReceiptView receiptView;
	/**
	 * Constructor of PaymentView
	 * @param orderId orderId of this object
	 * @param branch branch of this object
	 */
	public PaymentView(String orderId, String branch) {
		this.orderId = orderId;
		this.branch = branch;
	}
	/**
	 * View Actions for PaymentView
	 */
	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View > Customer View > Order View for OrderId " + orderId + " > Checkout View > Payment View for OrderId " + orderId);
		System.out.println("Choose payment method:");
		int num = 1;
		for (String paymentMethod: Repository.PAYMENT_METHODS) {
			System.out.println("(" + (num++) + ") " + paymentMethod);			
		}
		System.out.println("("+ (num++) +")" + " Back to Customer View");
	}

	/**
	 * View Application for PaymentView
	 * @param orderId orderId of the order
	 * @param branch branch name of the branch that the customer is currently in
	 */
	@Override
	public void viewApp() {
		 int opt = -1;
		 int size = Repository.PAYMENT_METHODS.size()+1;
		 do {
	    	 printActions();
	         opt = Helper.readInt();
	         
	         if(opt < size && opt > 0) {
	        	 break;
	        }
	         else {
	        	 System.out.println("Invalid option. Please try again.");
	         }
		 
		 } while (opt > size || opt <= 0);
		 
		 if (opt < size && opt > 0) {
			 receiptView = new ReceiptView(this.orderId, this.branch);
			 receiptView.viewApp();
		 }
	}
}
