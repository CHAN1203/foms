package view;

public class CustomerView extends MainView{
	
	public CustomerView() {
		super();
	}

	@Override
	protected void printAction() {
		printBreadCrumbs("Fast Food App View > Customer View");
		System.out.println("What would you like to do ?");
        System.out.println("(1) Create a Guest");
        System.out.println("(2) Update a Guest detail");
        System.out.println("(3) Remove a Guest");
        System.out.println("(4) Search a Guest");
        System.out.println("(5) Print all Guests");
        System.out.println("(6) Exit Guest View");
	}

	@Override
	public void viewApp() {
		// TODO Auto-generated method stub
		
	}
	
	
}
