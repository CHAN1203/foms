package controller;

import java.util.Iterator;
import java.util.Map;
import model.Branch;
import repository.Repository;

/** BranchController is a controller class that acts as a "middleman"
 * between the view classes - {@link CustomerView} , -{@link AdminView}, -{@link DisplayStaffView}
 * 
 * @author Hong Sheng
 * @version 1.0
 * @since 2024-04-18
 */

public class BranchController {
	
	/**
	 * Uses an iterator to iterate through the Branch HashMap and returns a branch object
	 * @param Opt is the option of branch that user selected
	 * @return Name of branch chosen
	 */
	public static String promptBranch(int opt) {
		Iterator<Map.Entry<String, Branch>> iteratedBranch = Repository.BRANCH.entrySet().iterator();
		int i = 1;
		for(i = 1; i<opt; i++) {
			iteratedBranch.next();
		}
		Map.Entry<String, Branch> SelectedBranch = iteratedBranch.next();
		Branch chosenBranch = SelectedBranch.getValue();
		String branch = chosenBranch.getName();
		return branch;
	}
}
