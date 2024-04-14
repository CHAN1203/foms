package view;
import helper.Helper;

public class FastFoodAppView extends MainView{

	protected BranchView branchView;
	protected LoginView loginView;
	
	public FastFoodAppView() {
		super();
		branchView = new BranchView();
		loginView = new LoginView();
	}
	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View");
        System.out.println("Who would you like to continue as?");
        System.out.println("(1) Customer");
        System.out.println("(2) Staff");
        System.out.println("(3) Quit Fast Food App");
	}

	@Override
	public void viewApp() {
		int choice;
		do {
			printActions();
			choice = Helper.readInt();
			switch(choice) {
				case 1:
					branchView.viewApp();
					break;
				case 2:
					loginView.viewApp();
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Invalid input! Please try again.");
					break;
			}
		} while (choice != 3);
		
	}
	
}
