package controller;

import java.util.Iterator;
import java.util.Map;

import model.Branch;
import repository.Repository;

public class BranchController {
	
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
