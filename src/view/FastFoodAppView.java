package view;
import helper.Helper;
/**
 * FastFoodAppView provides the view to take user input that determines their identity
 * which calls {@link CustomerView} and {@link LoginView} 
 * @author Jacky, Hong Sheng
 * @version 1.0
 * @since 2024-04-01
 */
public class FastFoodAppView extends MainView{
	/**
	 * Constructing of required View Classes
	 */
	LoginView loginView = new LoginView();
	CustomerView customerView = new CustomerView();
	/**
	 * Default constructor of FastFoodAppView
	 */
	public FastFoodAppView() {
		super();
	}
	/**
	 * View Actions of FastFoodAppView
	 */
	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View");
        System.out.println("Who would you like to continue as?");
        System.out.println("(1) Customer");
        System.out.println("(2) Staff");
        System.out.println("(3) Quit Fast Food App");
	}
	/**
	 * View Application of FastFoodAppView which calls to {@link CustomerView} and {@link loginView} 
	 */
	@Override
	public void viewApp() {
		int choice;
		do {
			printActions();
			choice = Helper.readInt();
			switch(choice) {
				case 1:
					customerView.viewApp();
					break;
				case 2:
					loginView.viewApp();
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Invalid input! Please try again.");
					break;
			}
		} while (choice != 3);
		
	}
	
}
