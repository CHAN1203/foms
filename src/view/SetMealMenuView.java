package view;

import helper.Helper;

public class SetMealMenuView extends MainView {
	
	public SetMealMenuView() {
		super();
	}
	
	@Override
	public void printActions() {
		System.out.println("Which food would you like to order?"); 
		System.out.println("1. 3PC Set Meal"); //how do we access a certain branch, and only print foods from that branch?
	}

	@Override
	public void viewApp() {
		printActions();
		int opt;
		do {
			opt = Helper.readInt();
			switch (opt) {
			case 1:
				OrderManager.placeOrder(1); //need to implement placeOrder method
				break;
			case 5:
				System.out.println("Quit");
			}
		} while (opt != 5);
		
	}
}
