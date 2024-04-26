package view;

import java.util.Map;

import helper.Helper;
import model. *;
import repository.Repository;
/**
 * BranchView provides the view that takes user input for selection of branch
 * 
 * @author Hong Sheng
 * @version 1.0
 * @since 2024-04-08
 */
public class BranchView extends MainView {
	/**
	 * Default constructor of the BranchView
	 */
	public BranchView() { 
		super();
	}
	/**
	 * View Actions of the BranchView
	 */
	@Override
	protected void printActions() {
		Helper.clearScreen();
		printBreadCrumbs("Fast Food App View > Customer View > Branch View");
		System.out.println("Please select the branch you would like to order from: ");
		printBranchNames();
	}
	/**
	 * View Application of BranchView. <p>
	 */
	@Override
	public void viewApp() {
		// TODO Auto-generated method stub
		printActions();
	}
	/**
	 * Uses for loop to access the Branch HashMap and prints out the branch names
	 */
	private void printBranchNames() {
		int i = 1;
		for(Map.Entry<String,Branch> entry : Repository.BRANCH.entrySet()) {
			String branchName = entry.getKey();
			System.out.println("(" + i + ") " + branchName);
			i++;
		}
	}
}
