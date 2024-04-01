package view;

import java.util.Scanner;

public class StaffLoginView extends MainView{
	
	public StaffLoginView() {
		super();
	}
	
	@Override
	protected void printAction() {
		// TODO Auto-generated method stub
		printBreadCrumbs("Fast Food App View > Login View");
	}

	@Override
	public void viewApp() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		printAction();
		
		
	}
	
}
	

