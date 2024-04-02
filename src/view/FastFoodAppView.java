package view;

import java.util.*;

import helper.Helper;

public class FastFoodAppView extends MainView{

	protected CustomerView customerView;
	
	protected StaffLoginView staffLoginView;
	
	public FastFoodAppView() {
		super();
		customerView = new CustomerView();
		staffLoginView = new StaffLoginView();
	}
	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View");
        System.out.println("Who you like to cotinue as?");
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
					customerView.viewApp();
					break;
				case 2:
					staffLoginView.viewApp();
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid input! Please try again.");
					break;
			}
		} while (choice != 3);
		
	}
	
}
