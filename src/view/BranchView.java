package view;

public class BranchView extends MainView {

	public BranchView() { 
		super();
	}
	
	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View > Branch View");
		System.out.println("Select Branch");
        //Serialization required
	}

	@Override
	public void viewApp() {
		// TODO Auto-generated method stub
		printActions();
		
	}

}
