package controller;
import model. *;
import repository. *;

public class BranchController {
	public static void initializeDummyBranchInfo() {
       addBranch("NTU", "North Spine Plaza", 8);
       addBranch("JE", "Jurong East", 11);
       addBranch("JP", "Jurong Point", 15);
       
       AdminController.addStaffAccount();
       addMenuItem()
    }
	
	public static boolean addBranch(String name, String location, int staffQuota) {
        Branch branch = new Branch(name, location, staffQuota);
        Repository.BRANCH.put(name, branch);
        Repository.persistData(FileType.BRANCH);
        return true;
	}
}
