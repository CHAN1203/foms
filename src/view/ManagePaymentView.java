package view;
import repository.Repository;
import controller.AdminController;
import helper.Helper;

public class ManagePaymentView extends MainView{
	public void printActions() {
		Helper.clearScreen();
		//!!need to change the headline
        printBreadCrumbs("Food Ordering App View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Add new payment method");
        System.out.println("(2) Remove payment method");
        System.out.println("(3) Quit");
	}
	
	public void viewApp() {
		int opt = -1;
		do {
			printActions();
			opt = Helper.readInt(1,3);
			switch(opt) {
				case 1:
					promptAddPaymentMethod();
					break;
				case 2:
					promptRemovePaymentMethod();
					break;
			}
		}while(opt != 3);
	}
	
	private boolean promptAddPaymentMethod() {
		System.out.print("Enter a new payment method: ");
		String newPaymentMethod = Helper.readString();
		if(AdminController.addPaymentMethod(newPaymentMethod)) return true;
		return false;
    }
	
	private boolean promptRemovePaymentMethod() {
		System.out.println("Enter an existing payment method to remove: ");
	   	System.out.println("Existing payment methods:");
        for (String method : Repository.PAYMENT_METHODS) {
            System.out.println(method);
        }
	    String paymentMethodToRemove = Helper.readString();
	   	if(AdminController.removePaymentMethod(paymentMethodToRemove)) return true;
	   	return false;
	   }
}
