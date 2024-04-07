package view;

import helper.Helper;

public class CategoryView extends MainView{
	
	public CategoryView() {
		super();
	}
	
	@Override
	public void printActions() {
		printBreadCrumbs("Fast Food App View > Customer View > Category View");
		System.out.println("Which food category would you like to browse today?");
		System.out.println("1. Set Meal");
		System.out.println("2. Burger");
		System.out.println("3. Side");
		System.out.println("4. Drink");
	}

	@Override
	public void viewApp() {
		printActions();
		int opt;
		do {
			opt = Helper.readInt();
			switch (opt) {
			case 1:
				SetMealMenuView.viewApp(); // should we create a separate view for all of these categories? 
				break;
			case 2:
				BurgerMenuView.viewApp();
				break;
			case 3:
				SideMenuView.viewApp();
				break;
			case 4:
				DrinkMenuView.viewApp();
				break;
			case 5:
				System.out.println("Quit");;
			
			}
		} while (opt != 5);
		
	}
}