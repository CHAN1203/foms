package view;
import java.util.Map;
import model. *;
import repository.Repository;

public class BranchView extends MainView {

	public BranchView() { 
		super();
	}
	
	@Override
	protected void printActions() {
		printBreadCrumbs("Fast Food App View > Branch View");
		System.out.println("Please select the branch you would like to order from: ");
		printBranchNames();
		
		
        //Serialization required
	}

	@Override
	public void viewApp() {
		// TODO Auto-generated method stub
		printActions();
	}
	
	public void printBranchNames() {
		int i = 1;
		for(Map.Entry<String,Branch> entry : Repository.BRANCH.entrySet()) {
			String branchName = entry.getKey();
			System.out.println("(" + i + ") " + branchName);
			i++;
		}
	}
}
