package view;

import helper.Helper;
import repository.Repository;

public class PaymentView extends MainView{

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
		 
		 } while (opt > size);
		 
		 if (opt < size && opt > 0) {
			 //print receipt here or create ReceiptView?
		 }

		
	}

}
