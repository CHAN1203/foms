package view;
import repository.Repository;
import controller.AdminController;
import helper.Helper;


/**
 * ManagePaymentView provides the view to take user input which calls {@link AdminController} to manage {@link PaymentMethods}.
 * @author Chan Kee Qing
 * @version 1.0
 * @since 2022-04-03
 */
public class ManagePaymentView extends MainView{
	/**
     * View Actions of the ManagePaymentView.
     */
    @Override
	public void printActions() {
		Helper.clearScreen();
        printBreadCrumbs("Fast Food App View > Login View > Admin View > Manage Payment View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Add new payment method");
        System.out.println("(2) Remove payment method");
        System.out.println("(3) Quit");
	}
    /**
     * View Application of the ManagePaymentView. <p>
     */
    @Override
	public void viewApp() {
		int opt = -1;
		do {
			printActions();
			opt = Helper.readInt(1,3);
			switch(opt) {
				case 1:
					printBreadCrumbs("Fast Food App View > Login View > Admin View > Manage Payment View > Add Payment Method View");
					promptAddPaymentMethod();
					break;
				case 2:
					printBreadCrumbs("Fast Food App View > Login View > Admin View > Manage Payment View > Remove Payment Method View")
					promptRemovePaymentMethod();
					break;
				case 3:
					break;
			}
		}while(opt != 3);
	}
    /**
	 * function to prompt to add new payment method
	 * @return {@code true} if add new payment method is successful. Otherwise, {@code false}.
	 */
	private boolean promptAddPaymentMethod() {
		System.out.print("Enter a new payment method: ");
		String newPaymentMethod = Helper.readString();
		if(AdminController.addPaymentMethod(newPaymentMethod)) return true;
		return false;
    }
	/**
	 * function to prompt to remove payment method
	 * @return {@code true} if add new payment method is successful. Otherwise, {@code false}.
	 */
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
