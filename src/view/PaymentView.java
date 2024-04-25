package view;

import helper.Helper;
import repository.Repository;

public class PaymentView extends MainView{

	ReceiptView receiptView = new ReceiptView();
	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View > Customer View > Payment View");
		System.out.println("Choose payment method:");
		int num = 1;
		for (String paymentMethod: Repository.PAYMENT_METHODS) {
			System.out.println("(" + (num++) + ") " + paymentMethod);			
		}
		System.out.println((num++) + ". Back to Customer View");
	}

	public void viewApp(String orderId, String branch) {
		 int opt = -1;
		 int size = Repository.PAYMENT_METHODS.size()+1;
		 do {
	    	 printActions();
	         opt = Helper.readInt();
	         
	         if(opt < size && opt > 0) {
	        	 break;
	        }
		 
		 } while (opt > size || opt <= 0);
		 
		 if (opt < size && opt > 0) {
			 receiptView.viewApp(orderId, branch);
		 }

		
	}

	@Override
	public void viewApp() {
		// TODO Auto-generated method stub
		
	}

}
